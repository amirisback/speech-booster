package com.frogobox.speechbooster.view.route

import android.content.Context
import android.os.Bundle
import com.frogobox.speechbooster.util.Navigation.navigatorImplicit
import com.frogobox.speechbooster.view.route.Implicit.Endpoint.ABOUT_US_ACTIVITY
import com.frogobox.speechbooster.view.route.Implicit.Endpoint.CATEGORY_SCRIPT_ACTIVITY
import com.frogobox.speechbooster.view.route.Implicit.Endpoint.RECORD_ACTIVITY
import com.frogobox.speechbooster.view.route.Implicit.Endpoint.SCRIPT_DETAIL_ACTIVITY
import com.frogobox.speechbooster.view.route.Implicit.Endpoint.SCRIPT_EDITOR_ACTIVITY
import com.frogobox.speechbooster.view.route.Implicit.Endpoint.VIDEO_DETAIL_ACTIVITY
import com.frogobox.speechbooster.view.route.Implicit.Module.BASE_ACTIVITY
import com.frogobox.speechbooster.view.route.Implicit.Module.BASE_PACKAGE

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 19/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.view.navigation
 *
 */
object Implicit {

    object Module {
        const val BASE_PACKAGE = "com.frogobox.speechbooster"
        const val BASE_ACTIVITY = "view.ui.activity"

    }

    object Endpoint {
        const val SCRIPT_DETAIL_ACTIVITY = "$BASE_PACKAGE.$BASE_ACTIVITY.ScriptDetailActivity"
        const val SCRIPT_EDITOR_ACTIVITY = "$BASE_PACKAGE.$BASE_ACTIVITY.ScriptEditorActivity"
        const val VIDEO_DETAIL_ACTIVITY = "$BASE_PACKAGE.$BASE_ACTIVITY.VideoDetailActivity"
        const val RECORD_ACTIVITY = "$BASE_PACKAGE.$BASE_ACTIVITY.RecordActivity"
        const val ABOUT_US_ACTIVITY = "$BASE_PACKAGE.$BASE_ACTIVITY.AboutUsActivity"
        const val CATEGORY_SCRIPT_ACTIVITY = "$BASE_PACKAGE.$BASE_ACTIVITY.CategoryScriptActivity"

    }

    object Activity {

        // Detail Activity -------------------------------------------------------------------------
        fun startScriptDetailActivity(context: Context, data: Bundle, option: Bundle) {
            navigatorImplicit(context, BASE_PACKAGE, SCRIPT_DETAIL_ACTIVITY, data, option = option)
        }

        fun startCategoryScriptActivity(context: Context, data: Bundle) {
            navigatorImplicit(context, BASE_PACKAGE, CATEGORY_SCRIPT_ACTIVITY, data)
        }

        fun startVideoDetailActivity(context: Context, data: Bundle) {
            navigatorImplicit(context, BASE_PACKAGE, VIDEO_DETAIL_ACTIVITY, data)
        }
        // -----------------------------------------------------------------------------------------

        fun startScriptEditorActivity(context: Context, data: Bundle?, option: Bundle) {
            navigatorImplicit(context, BASE_PACKAGE, SCRIPT_EDITOR_ACTIVITY, data, option = option)
        }

        fun startRecordActivity(context: Context, data: Bundle) {
            navigatorImplicit(context, BASE_PACKAGE, RECORD_ACTIVITY, data)
        }

        fun startAboutUsActivity(context: Context) {
            navigatorImplicit(context, BASE_PACKAGE, ABOUT_US_ACTIVITY)
        }

    }

}