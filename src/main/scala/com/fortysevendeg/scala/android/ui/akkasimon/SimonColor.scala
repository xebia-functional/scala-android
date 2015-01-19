package com.fortysevendeg.scala.android.ui.akkasimon

object SimonColor {
  sealed trait SimonColor

  case object GREEN extends SimonColor
  case object RED extends SimonColor
  case object BLUE extends SimonColor
  case object YELLOW extends SimonColor

  implicit class RichSimonColor(sc: SimonColor) {
    def toLower = sc.toString.toLowerCase
  }
}