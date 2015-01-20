package com.fortysevendeg.scala.android.ui.main

import java.io.FileNotFoundException

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.View.OnClickListener
import android.view.{View, ViewGroup}
import com.fortysevendeg.macroid.extras.ExtraActions._
import com.fortysevendeg.macroid.extras.ExtraResources._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.commons.JsonReads
import macroid.FullDsl._
import macroid.{ActivityContext, AppContext}
import play.api.libs.json.{JsResultException, Json}

import scala.io.Source
import scala.util.{Failure, Success, Try}

class ProjectActivityInfoListAdapter(listener: RecyclerClickListener)
    (implicit context: ActivityContext, appContext: AppContext)
    extends RecyclerView.Adapter[ViewHolder] with JsonReads {

  val recyclerClickListener = listener

  val activityInfoList: Seq[ProjectActivityInfo] = getActivitiesFromJson("activities.json") match {
    case Success(activitiesList) => activitiesList
    case Failure(exception) => {
      exception match {
        case e: FileNotFoundException => aLongToast(appContext.get.getString(R.string.json_file_not_found))
        case e: JsResultException => aLongToast(appContext.get.getString(R.string.malformed_json_file))
        case e => aLongToast(appContext.get.getString(R.string.unexpected_error) + e.toString)
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

    runUi(
      (viewHolder.title <~ tvText(resGetString(projectActivityInfo.name).getOrElse(projectActivityInfo.name))) ~
          (viewHolder.description <~ tvText(resGetString(projectActivityInfo.description).getOrElse(projectActivityInfo.description))) ~
          (viewHolder.api <~ tvText(resGetString("min_api_required",resourceStringFormatArgs:_*).getOrElse("")) <~ setApiBackground(projectActivityInfo))
    )
  }

  private def getActivitiesFromJson(url: String): Try[Seq[ProjectActivityInfo]] = {
    for {
      stream <- Try(appContext.get.getAssets.open(url))
      json <- Try(Source.fromInputStream(stream, "UTF-8").mkString)
      activityInfoList <- Try(Json.parse(json).as[Seq[ProjectActivityInfo]])
    } yield activityInfoList
  }

  private def setApiBackground(projectActivityInfo: ProjectActivityInfo) = {
    if (projectActivityInfo.apiRequired) vBackground(R.drawable.background_item_api_required)
    else vBackground(R.drawable.background_item_api_not_required)
  }
}

trait RecyclerClickListener {
  def onClick(info: ProjectActivityInfo)
}

case class ViewHolder(adapter: Adapter)(implicit context: ActivityContext, appContext: AppContext) extends RecyclerView.ViewHolder(adapter.layout) {

  var content = adapter.layout

  var title = adapter.title

  var description = adapter.description

  var api = adapter.api

}

case class ProjectActivityInfo(name: String, description: String, className: String, minApi: Int, targetApi: Int) {
  val apiRequired = Build.VERSION.SDK_INT < this.minApi
}
