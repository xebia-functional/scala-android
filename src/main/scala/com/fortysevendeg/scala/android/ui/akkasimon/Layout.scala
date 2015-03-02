package com.fortysevendeg.scala.android.ui.akkasimon

import akka.actor.ActorSystem
import android.graphics.Color
import android.support.v4.app.{Fragment, FragmentManager}
import android.widget.{FrameLayout, Button, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.FragmentExtras._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.akkasimon.Styles._
import com.fortysevendeg.scala.android.ui.akkasimon.actors.ComputerActor.NewGame
import com.fortysevendeg.scala.android.ui.akkasimon.fragments.{ColorFragment, ComputerFragment}
import com.fortysevendeg.scala.android.ui.akkasimon.util.FragmentEnum._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid._

import scala.language.postfixOps

trait Layout extends ToolbarLayout with IdGeneration {

  var btnStart = slot[Button]

  var optionsScreenLayout = slot[LinearLayout]

  var gameScreenLayout = slot[LinearLayout]

  var rounds = slot[TextView]

  def layout
            (implicit appContext: AppContext,
             context: ActivityContext,
             managerContext: FragmentManagerContext[Fragment, FragmentManager]) = getUi(
    l[LinearLayout](
      toolBarLayout <~ tbTitle(R.string.title_akka_simon),
      l[FrameLayout](
        f[ComputerFragment].pass("name" -> COMPUTER.toLower).framed(Id.computer, COMPUTER.toLower),
        optionsScreen,
        gameScreen
      ) <~ contentStyle
    ) <~ rootStyle
  )

  def optionsScreen(implicit appContext: AppContext,
                    context: ActivityContext,
                    managerContext: FragmentManagerContext[Fragment, FragmentManager]) = {
    l[LinearLayout](
      w[Button] <~ buttonsStyle <~ On.click(Ui {
        findFragmentById[ComputerFragment](Id.computer) map (_.actor.get ! NewGame)
      } ~ goToGame())
    ) <~ optionsContentStyle <~ wire(optionsScreenLayout)
  }

  def gameScreen(implicit appContext: AppContext,
                 context: ActivityContext,
                 managerContext: FragmentManagerContext[Fragment, FragmentManager]) = {
    l[LinearLayout](
      w[TextView] <~ roundsStyle <~ wire(rounds),
      l[LinearLayout](
        l[LinearLayout](
          f[ColorFragment]
            .pass("color" -> Color.GREEN, "name" -> GREEN.toLower)
            .framed(Id.green, GREEN.toLower) <~ rowStyle,
          f[ColorFragment]
            .pass("color" -> Color.RED, "name" -> RED.toLower)
            .framed(Id.red, RED.toLower) <~ rowStyle
        ) <~ columnStyle,
        l[LinearLayout](
          f[ColorFragment]
            .pass("color" -> Color.BLUE, "name" -> BLUE.toLower)
            .framed(Id.blue, BLUE.toLower) <~ rowStyle,
          f[ColorFragment]
            .pass("color" -> Color.YELLOW, "name" -> YELLOW.toLower)
            .framed(Id.yellow, YELLOW.toLower) <~ rowStyle
        ) <~ columnStyle
      ) <~ simonContainerStyle
    ) <~ gameContentStyle <~ wire(gameScreenLayout)
  }

  def goToGame() = {
    (optionsScreenLayout <~ vGone) ~ (gameScreenLayout <~ vVisible)
  }

  def goToOptions() = {
    (optionsScreenLayout <~ vVisible) ~ (gameScreenLayout <~ vGone)
  }

}