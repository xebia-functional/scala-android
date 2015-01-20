package com.fortysevendeg.scala.android.ui.akkasimon

import akka.actor.ActorSystem
import android.graphics.Color
import android.support.v4.app.{Fragment, FragmentManager}
import android.widget.{Button, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.ExtraFragment._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.akkasimon.Styles._
import com.fortysevendeg.scala.android.ui.akkasimon.actors.ComputerActor.NewGame
import com.fortysevendeg.scala.android.ui.akkasimon.fragments.{ColorFragment, ComputerFragment, RoundFragment}
import com.fortysevendeg.scala.android.ui.akkasimon.util.FragmentEnum._
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid._

import scala.language.postfixOps

trait Layout extends ToolbarLayout with IdGeneration {

  var btnStart = slot[Button]

  def layout(system: ActorSystem)(implicit appContext: AppContext,
      context: ActivityContext,
      managerContext: FragmentManagerContext[Fragment, FragmentManager]) = getUi(
    l[LinearLayout](
      toolBarLayout <~ tbTitle(R.string.title_akka_simon),
      l[LinearLayout](
        f[ComputerFragment].pass("name" -> COMPUTER.toLower).framed(Id.computer, COMPUTER.toLower),
        w[Button] <~ tvText(R.string.simon_start) <~ buttonsStyle <~ On.click(Ui {
          findFragmentById[ComputerFragment](Id.computer) map (_.actor.get ! NewGame)
        }),
        w[TextView] <~ text(R.string.simon_round) <~ buttonsStyle,
        l[LinearLayout](
          f[RoundFragment].pass("name" -> ROUND.toLower).framed(Id.round, ROUND.toLower)
        )
      ) <~ horizontalLinearLayoutStyle,
      l[LinearLayout](
        l[LinearLayout](
          f[ColorFragment]
              .pass("color" -> Color.GREEN, "name" -> GREEN.toLower)
              .framed(Id.green, GREEN.toLower) <~ fragmentStyle,
          f[ColorFragment]
              .pass("color" -> Color.RED, "name" -> RED.toLower)
              .framed(Id.red, RED.toLower) <~ fragmentStyle
        ) <~ columnColorsStyle,
        l[LinearLayout](
          f[ColorFragment]
              .pass("color" -> Color.BLUE, "name" -> BLUE.toLower)
              .framed(Id.blue, BLUE.toLower) <~ fragmentStyle,
          f[ColorFragment]
              .pass("color" -> Color.YELLOW, "name" -> YELLOW.toLower)
              .framed(Id.yellow, YELLOW.toLower) <~ fragmentStyle
        ) <~ columnColorsStyle
      ) <~ colorsContainerStyle
    ) <~ rootStyle
  )
}