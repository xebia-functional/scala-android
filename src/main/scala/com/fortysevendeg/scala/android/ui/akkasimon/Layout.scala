package com.fortysevendeg.scala.android.ui.akkasimon

import akka.actor.ActorSystem
import android.graphics.Color
import android.support.v4.app.{Fragment, FragmentManager}
import android.widget.{Button, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.ExtraFragment._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.akkasimon.SimonColor._
import com.fortysevendeg.scala.android.ui.akkasimon.SimonColorActor.LightOn
import com.fortysevendeg.scala.android.ui.akkasimon.Styles._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid._

import scala.language.postfixOps
import scala.util.Random

object Layout {

  val fragmentNamesList = List(
    GREEN.toString.toLowerCase,
    RED.toString.toLowerCase,
    BLUE.toString.toLowerCase,
    YELLOW.toString.toLowerCase
  )

  var fragmentsList: List[Option[SimonColorFragment]] = List.empty[Option[SimonColorFragment]]
}

trait Layout extends ToolbarLayout with IdGeneration {

  import com.fortysevendeg.scala.android.ui.akkasimon.Layout._

  var btnStart = slot[Button]

  var tvwRound = slot[TextView]

  val random = Random

  def layout(system: ActorSystem)(implicit appContext: AppContext,
      context: ActivityContext,
      managerContext: FragmentManagerContext[Fragment, FragmentManager]) = getUi(
    l[LinearLayout](
      toolBarLayout <~ tbTitle(R.string.title_akka_simon),
      l[LinearLayout](
        w[Button] <~ tvText(R.string.simon_start) <~ buttonsStyle <~ On.click {
          fragmentsList = List(Id.green, Id.red, Id.blue, Id.yellow) map findFragment

          val round = tvwRound.get.getText.toString.toInt

          val colorsNum = 0 to round

          val list = colorsNum.toList map { _ =>
            fragmentsList(random.nextInt(4))
          }
          list.head map (_.actor.get ! LightOn(list.tail.toList))

          Ui {
            // For testing purposes
            tvwRound.get.setText((round + 1).toString)
          }
        },
        w[TextView] <~ text(R.string.simon_round) <~ buttonsStyle,
        w[TextView] <~ wire(tvwRound) <~ text("1") <~ buttonsStyle
      ) <~ horizontalLinearLayoutStyle,
//      l[LinearLayout](
      //CONTENEDOR PRINCIPAL: llMatchWeightVertical + llHorizontal

        l[LinearLayout](
          //columna 1: llMatchWeightHorizontal + llHorizontal

            f[SimonColorFragment].pass("color" -> Color.GREEN, "name" -> GREEN.toLower).framed(Id.green, Tag.green),
            f[SimonColorFragment].pass("color" -> Color.RED, "name" -> RED.toLower).framed(Id.red, Tag.red)

        ) <~ horizontalLinearLayoutStyle,

        l[LinearLayout](
          //columna 2: llMatchWeightHorizontal + llHorizontal

            f[SimonColorFragment].pass("color" -> Color.BLUE, "name" -> BLUE.toLower).framed(Id.blue, Tag.blue),
            f[SimonColorFragment].pass("color" -> Color.YELLOW, "name" -> YELLOW.toLower).framed(Id.yellow, Tag.yellow)

        ) <~ horizontalLinearLayoutStyle

//      ) <~ verticalWeigthMatch
    ) <~ rootStyle
  )

  def findFragment(id: Int)(
      implicit appContext: AppContext,
      context: ActivityContext,
      fragmentManager: FragmentManagerContext[Fragment, FragmentManager]): Option[SimonColorFragment] = {
    findFragmentById(id) map (_.asInstanceOf[SimonColorFragment])
  }
}