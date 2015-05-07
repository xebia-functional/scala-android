package com.fortysevendeg.scala.android.ui.akkasimon

import android.graphics.Color
import android.support.v4.app.{Fragment, FragmentManager}
import android.widget.{Button, FrameLayout, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.FragmentExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.akkasimon.actors.ComputerActor.NewGame
import com.fortysevendeg.scala.android.ui.akkasimon.fragments.ColorFragment._
import com.fortysevendeg.scala.android.ui.akkasimon.fragments.ComputerFragment._
import com.fortysevendeg.scala.android.ui.akkasimon.fragments.{ColorFragment, ComputerFragment}
import com.fortysevendeg.scala.android.ui.akkasimon.util.FragmentEnum._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid._

import scala.language.postfixOps

trait Layout
  extends ToolbarLayout
  with IdGeneration
  with Styles {

  var btnStart = slot[Button]

  var optionsScreenLayout = slot[LinearLayout]

  var gameScreenLayout = slot[LinearLayout]

  var rounds = slot[TextView]

  var message = slot[TextView]

  def layout
            (implicit context: ActivityContextWrapper,
             managerContext: FragmentManagerContext[Fragment, FragmentManager]) = getUi(
    l[LinearLayout](
      toolBarLayout <~ tbTitle(R.string.simon_title),
      l[FrameLayout](
        f[ComputerFragment].
          pass(nameComputerKey -> COMPUTER.toLower).
          framed(Id.computer, COMPUTER.toLower),
        optionsScreen,
        gameScreen
      ) <~ contentStyle
    ) <~ rootStyle
  )

  def optionsScreen(implicit context: ActivityContextWrapper,
                    managerContext: FragmentManagerContext[Fragment, FragmentManager]) = {
    l[LinearLayout](
      w[TextView] <~ wire(message) <~ messageStyle,
      w[Button] <~ buttonsStyle <~ On.click(Ui {
        findFragmentById[ComputerFragment](Id.computer) map (_.actor.get ! NewGame)
      } ~ goToGame())
    ) <~ optionsContentStyle <~ wire(optionsScreenLayout)
  }

  def gameScreen(implicit context: ActivityContextWrapper,
                 managerContext: FragmentManagerContext[Fragment, FragmentManager]) = {
    l[LinearLayout](
      w[TextView] <~ roundsStyle <~ wire(rounds),
      l[LinearLayout](
        l[LinearLayout](
          f[ColorFragment]
            .pass(colorKey -> Color.GREEN, nameColorKey -> GREEN.toLower)
            .framed(Id.green, GREEN.toLower) <~ rowStyle,
          f[ColorFragment]
            .pass(colorKey -> Color.RED, nameColorKey -> RED.toLower)
            .framed(Id.red, RED.toLower) <~ rowStyle
        ) <~ columnStyle,
        l[LinearLayout](
          f[ColorFragment]
            .pass(colorKey -> Color.BLUE, nameColorKey -> BLUE.toLower)
            .framed(Id.blue, BLUE.toLower) <~ rowStyle,
          f[ColorFragment]
            .pass(colorKey -> Color.YELLOW, nameColorKey -> YELLOW.toLower)
            .framed(Id.yellow, YELLOW.toLower) <~ rowStyle
        ) <~ columnStyle
      ) <~ simonContainerStyle
    ) <~ gameContentStyle <~ wire(gameScreenLayout)
  }

  def goToGame() = (optionsScreenLayout <~ vGone) ~ (gameScreenLayout <~ vVisible)

  def goToOptions(rounds: Int)(implicit context: ContextWrapper) = {
    (optionsScreenLayout <~ vVisible) ~
      (gameScreenLayout <~ vGone) ~
      (message <~ tvText(resGetString(R.string.simon_rounds_message, rounds.toString)))
  }

}