package com.fortysevendeg.scala.android.ui.akkasimon

import akka.actor.ActorSystem
import android.graphics.Color
import android.support.v4.app.{Fragment, FragmentManager}
import android.widget.{Button, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.ExtraFragment._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.akkasimon.actors.SimonColorActor
import com.fortysevendeg.scala.android.ui.akkasimon.actors.SimonComputerActor.NewGame
import com.fortysevendeg.scala.android.ui.akkasimon.fragments.{ComputerFragment, SimonColorFragment}
import com.fortysevendeg.scala.android.ui.akkasimon.util.SimonColor
import SimonColor._
import SimonColorActor.LightOn
import com.fortysevendeg.scala.android.ui.akkasimon.Styles._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid._
import macroid.akkafragments.AkkaFragment

import scala.language.postfixOps
import scala.util.Random

trait Layout extends ToolbarLayout with IdGeneration {

  var btnStart = slot[Button]

  var tvwRound = slot[TextView]

  val random = Random

  def layout(system: ActorSystem)(implicit appContext: AppContext,
      context: ActivityContext,
      managerContext: FragmentManagerContext[Fragment, FragmentManager]) = getUi(
    l[LinearLayout](
      toolBarLayout <~ tbTitle(R.string.title_akka_simon),
      l[LinearLayout](
        f[ComputerFragment].pass("name" -> "computer").framed(Id.computer, Tag.computer),
        w[Button] <~ tvText(R.string.simon_start) <~ buttonsStyle <~ On.click(startButtonHandler),
        w[TextView] <~ text(R.string.simon_round) <~ buttonsStyle,
        w[TextView] <~ wire(tvwRound) <~ text("1") <~ buttonsStyle
      ) <~ horizontalLinearLayoutStyle,
      l[LinearLayout](
        l[LinearLayout](
          f[SimonColorFragment].pass("color" -> Color.GREEN, "name" -> GREEN.toLower).framed(Id.green, Tag.green) <~ vMatchWidth,
          f[SimonColorFragment].pass("color" -> Color.RED, "name" -> RED.toLower).framed(Id.red, Tag.red) <~ vMatchWidth
        ) <~ verticalWrap,
        l[LinearLayout](
          f[SimonColorFragment].pass("color" -> Color.BLUE, "name" -> BLUE.toLower).framed(Id.blue, Tag.blue) <~ vMatchWidth,
          f[SimonColorFragment].pass("color" -> Color.YELLOW, "name" -> YELLOW.toLower).framed(Id.yellow, Tag.yellow) <~ vMatchWidth
        ) <~ verticalWrap
      ) <~ verticalWeigthMatch
    ) <~ rootStyle
  )

  def startButtonHandler(
      implicit appContext: AppContext,
      context: ActivityContext,
      fragmentManager: FragmentManagerContext[Fragment, FragmentManager]) = Ui {
    val fragmentsList = List(Id.green, Id.red, Id.blue, Id.yellow) map findFragment

    val round = tvwRound.get.getText.toString.toInt

    val colorsNum = 0 to round

    val list = colorsNum.toList map { _ =>
      fragmentsList(random.nextInt(4))
    }

    findFragment(Id.computer) map { f: ComputerFragment =>
      f.actor.get ! NewGame(list)
    }

    list.head map { f: SimonColorFragment =>
      f.actor.get ! LightOn(list.tail.toList)
    }
    // TODO: For testing purposes
    tvwRound.get.setText((round + 1).toString)
  }

  def findFragment[T <: AkkaFragment](id: Int)(
      implicit appContext: AppContext,
      context: ActivityContext,
      fragmentManager: FragmentManagerContext[Fragment, FragmentManager]): Option[T] = {
    findFragmentById(id) map (_.asInstanceOf[T])
  }
}