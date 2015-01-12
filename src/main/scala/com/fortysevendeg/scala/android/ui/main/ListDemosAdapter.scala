package com.fortysevendeg.scala.android.ui.main

import java.io.FileNotFoundException

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.View.OnClickListener
import android.view.{View, ViewGroup}
import com.fortysevendeg.macroid.extras.ExtraActions._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.commons.JsonProtocol._
import macroid.{ActivityContext, AppContext}
import spray.json._

import scala.io.Source
import scala.util.{Failure, Success, Try}

class ListDemosAdapter(listener: RecyclerClickListener)
    (implicit context: ActivityContext, appContext: AppContext)
    extends RecyclerView.Adapter[ViewHolder] {

  val recyclerClickListener = listener

  val activityInfoList: Seq[ProjectActivityInfo] = getActivitiesFromJson("activities.json") match {
    case Success(activitiesList) => activitiesList
    case Failure(exception) => {
      exception match {
        case e: FileNotFoundException => aLongToast(appContext.get.getString(R.string.json_file_not_found))
        case e: DeserializationException => aLongToast(appContext.get.getString(R.string.malformed_json_file))
        case _ => aLongToast(appContext.get.getString(R.string.unexpected_error))
      }
      Seq.empty
    }
  }

  override def onCreateViewHolder(parentViewGroup: ViewGroup, i: Int): ViewHolder = {
    val adapter = new Adapter
    adapter.layout.setOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = recyclerClickListener.onClick(activityInfoList(v.getTag.asInstanceOf[Int]))
    })
    return new ViewHolder(adapter)
  }

  override def getItemCount: Int = activityInfoList.size

  override def onBindViewHolder(viewHolder: ViewHolder, position: Int): Unit = {
    val demoInfo = activityInfoList(position)
    viewHolder.content.setTag(position)
    viewHolder.title.map(_.setText(demoInfo.name))
    viewHolder.description.map(_.setText(demoInfo.description))
    viewHolder.api.map {
      view =>
        val myApi = Build.VERSION.SDK_INT
        view.setText("API %d".format(demoInfo.targetApi))
        if (myApi < demoInfo.minApi) {
          view.setBackgroundResource(R.drawable.background_item_api_required)
        } else {
          view.setBackgroundResource(R.drawable.background_item_api_not_required)
        }
    }

    // TODO We should use the next code in macroid but it doesn't work inside Adapter. Pending study

    //    runUi(Ui {
    //      viewHolder.title <~ tvText(demoInfo.name)
    //      viewHolder.description <~ tvText(demoInfo.description)
    //      viewHolder.api <~ tvText("API %d".format(demoInfo.minApi)) <~
    //          (if (demoInfo.apiRequired) vBackground(R.drawable.background_item_api_required) else vBackground(R.drawable.background_item_api_not_required))
    //    })

  }

  def getActivitiesFromJson(url: String): Try[Seq[ProjectActivityInfo]] = {
    for {
      stream <- Try(appContext.get.getAssets.open(url))
      json <- Try(Source.fromInputStream(stream, "UTF-8").mkString)
      activityInfoList <- Try(json.parseJson.convertTo[Seq[ProjectActivityInfo]])
    } yield activityInfoList
  }
}

trait RecyclerClickListener {
  def onClick(info: ProjectActivityInfo)
}

class ViewHolder(adapter: Adapter)(implicit context: ActivityContext, appContext: AppContext) extends RecyclerView.ViewHolder(adapter.layout) {

  var content = adapter.layout

  var title = adapter.title

  var description = adapter.description

  var api = adapter.api

}

case class ProjectActivityInfo(name: String, description: String, className: String, minApi: Int, targetApi: Int)
