package com.fortysevendeg.scala.android.ui.validateforms

import android.widget._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.fortysevendeg.scala.android.R
import com.fortysevendeg.scala.android.ui.commons.ToolbarLayout
import macroid.FullDsl._
import macroid.{ActivityContext, AppContext}

trait Layout
    extends ToolbarLayout
    with Styles {

  var name = slot[EditText]

  var age = slot[EditText]

  var email = slot[EditText]

  var validationResultLabel = slot[TextView]

  def layout(implicit appContext: AppContext, context: ActivityContext) = {
    getUi(
      l[LinearLayout](
        toolBarLayout <~ tbTitle(R.string.title_validate_form),
        l[ScrollView](
          l[LinearLayout](
            w[TextView] <~ textLabelStyle <~ text(R.string.validate_label_name),
            w[EditText] <~ editTextStyle <~ wire(name),
            w[TextView] <~ textLabelStyle <~ text(R.string.validate_label_email),
            w[EditText] <~ editTextStyle <~ wire(email),
            w[TextView] <~ textLabelStyle <~ text(R.string.validate_label_age),
            w[EditText] <~ editTextStyle <~ wire(age),
            w[TextView] <~ textLabelStyle <~ text(R.string.validate_label_result),
            w[TextView] <~ textLabelStyle <~ text(R.string.validate_result) <~ wire(validationResultLabel)
          ) <~ scrollContentStyle
        ) <~ scrollStyle,
        w[ImageView] <~ lineHorizontalStyle,
        w[Button] <~ saveButtonStyle <~ On.click(validationResultLabel <~ tvText("Hi"))
      ) <~ contentStyle
    )
  }

}
