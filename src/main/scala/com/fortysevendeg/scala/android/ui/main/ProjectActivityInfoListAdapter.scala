package com.fortysevendeg.scala.android.ui.main

import java.io.FileNotFoundException

import android.support.v7.widget.RecyclerView
import android.view.View.OnClickListener
import android.view.{View, ViewGroup}
import com.fortysevendeg.macroid.extras.ActionsExtras._
import com.fortysevendeg.macroid.extras.DeviceVersion._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.commons.JsonReads
import macroid.FullDsl._
import macroid.ActivityContextWrapper
import com.fortysevendeg.scala.android.ui.commons.AsyncImageTweaks._
import play.api.libs.json.{JsResultException, Json}

import scala.io.Source
import scala.util.{Failure, Success, Try}

class ProjectActivityInfoListAdapter(listener: RecyclerClickListener)
                                    (implicit context: ActivityContextWrapper)
  extends RecyclerView.Adapter[ViewHolder] with JsonReads {

  import com.fortysevendeg.scala.android.ui.main.APIType._

  val recyclerClickListener = listener

  val activityInfoList: Seq[ProjectActivityInfo] = getActivitiesFromJson("activities.json") match {
    case Success(activitiesList) => activitiesList
    case Failure(exception) => {
      exception match {
        case e: FileNotFoundException => aLongToast(context.application.getString(R.string.json_file_not_found))
        case e: JsResultException => aLongToast(context.application.getString(R.string.malformed_json_file))
        case e => aLongToast(context.application.getString(R.string.unexpected_error) + e.toString)
      }
      Seq.empty
    }
  }

  override def onCreateViewHolder(parentViewGroup: ViewGroup, i: Int): ViewHolder = {
    val adapter = new Adapter
    adapter.layout.setOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = recyclerClickListener.onClick(activityInfoList(v.getTag.asInstanceOf[Int]))
    })

    ViewHolder(adapter)
  }

  override def getItemCount: Int = activityInfoList.size

  override def onBindViewHolder(viewHolder: ViewHolder, position: Int): Unit = {
    val projectActivityInfo = activityInfoList(position)
    val resourceStringFormatArgs = Seq(projectActivityInfo.minApi) map (_.asInstanceOf[Object])
    viewHolder.content.setTag(position)

    val avatarSize = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)

    runUi(
      (viewHolder.title <~ tvText(projectActivityInfo.name)) ~
        (viewHolder.userContent <~ vTag(projectActivityInfo.user.twitter)) ~
        (viewHolder.description <~ tvText(projectActivityInfo.description)) ~
        (viewHolder.avatar <~ roundedImage(projectActivityInfo.user.avatar, R.drawable.placeholder_circle, avatarSize)) ~
        (viewHolder.username <~ tvText(projectActivityInfo.user.name)) ~
        (viewHolder.twitter <~ tvText(projectActivityInfo.user.twitter)) ~
        (viewHolder.api <~ tvText(resGetString(R.string.min_api_required, projectActivityInfo.minApi.toString)) <~
          (projectActivityInfo.apiType match {
            case SUCCESS => vBackground(R.drawable.background_item_api_success)
            case ADVISED => vBackground(R.drawable.background_item_api_advised)
            case _ => vBackground(R.drawable.background_item_api_required)
          })) ~
        (viewHolder.androidLevel <~ (projectActivityInfo.androidLevel match {
          case 1 => tvText(R.string.beginning_level)
          case 2 => tvText(R.string.intermediate_level)
          case 3 => tvText(R.string.advanced_level)
        })) ~
        (viewHolder.scalaLevel <~ (projectActivityInfo.scalaLevel match {
          case 1 => tvText(R.string.beginning_level)
          case 2 => tvText(R.string.intermediate_level)
          case 3 => tvText(R.string.advanced_level)
        }))
    )
  }

  private def getActivitiesFromJson(url: String): Try[Seq[ProjectActivityInfo]] = {
    for {
      stream <- Try(context.application.getAssets.open(url))
      json <- Try(Source.fromInputStream(stream, "UTF-8").mkString)
      activityInfoList <- Try(Json.parse(json).as[Seq[ProjectActivityInfo]])
    } yield activityInfoList
  }

}

trait RecyclerClickListener {
  def onClick(info: ProjectActivityInfo)
}

case class ViewHolder(adapter: Adapter)(implicit context: ActivityContextWrapper)
  extends RecyclerView.ViewHolder(adapter.layout) {

  val content = adapter.layout

  val title = adapter.title

  val description = adapter.description

  val api = adapter.api

  val username = adapter.username

  val twitter = adapter.twitter

  val avatar = adapter.avatar
  
  val scalaLevel = adapter.scalaLevel

  val androidLevel = adapter.androidLevel
  
  val userContent = adapter.userContent

}

case class ProjectActivityInfo(
  name: String,
  description: String,
  className: String,
  minApi: Int,
  targetApi: Int,
  scalaLevel: Int,
  androidLevel: Int,
  user: UserInfo) {

  import com.fortysevendeg.scala.android.ui.main.APIType._

  val apiType: APIType.Value = CurrentVersion match {
    case current if current.version >= targetApi => SUCCESS
    case current if current.version >= minApi => ADVISED
    case _ => REQUIRED
  }
}

case class UserInfo(
  avatar: String,
  name: String,
  twitter: String)

object APIType extends Enumeration {

  type APIType = Value

  val SUCCESS, ADVISED, REQUIRED = Value
}