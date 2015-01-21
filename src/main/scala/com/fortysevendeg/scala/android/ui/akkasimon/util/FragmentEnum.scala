package com.fortysevendeg.scala.android.ui.akkasimon.util

object FragmentEnum {
  sealed trait FragmentEnumType

  case object GREEN extends FragmentEnumType
  case object RED extends FragmentEnumType
  case object BLUE extends FragmentEnumType
  case object YELLOW extends FragmentEnumType

  case object COMPUTER extends FragmentEnumType
  case object ROUND extends FragmentEnumType

  implicit class RichSimonColor(sc: FragmentEnumType) {
    def toLower = sc.toString.toLowerCase
  }
}