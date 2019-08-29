package com.frogobox.speechbooster.navigation

import android.content.Context
import android.os.Bundle
import com.frogobox.speechbooster.navigation.Navigation.navigatorImplicit
import com.frogobox.speechbooster.navigation.Route.Endpoint.RECORD_ACTIVITY
import com.frogobox.speechbooster.navigation.Route.Endpoint.SCRIPT_DETAIL_ACTIVITY
import com.frogobox.speechbooster.navigation.Route.Endpoint.SCRIPT_EDITOR_ACTIVITY
import com.frogobox.speechbooster.navigation.Route.Endpoint.VIDEO_DETAIL_ACTIVITY
import com.frogobox.speechbooster.navigation.Route.Module.BASE_PACKAGE

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
 * com.frogobox.speechbooster.navigation
 *
 */
object Route {

    object Module {
        const val BASE_PACKAGE = "com.frogobox.speechbooster"
    }

    object Endpoint {
        const val SCRIPT_DETAIL_ACTIVITY = "com.frogobox.speechbooster.view.activity.ScriptDetailActivity"
        const val SCRIPT_EDITOR_ACTIVITY = "com.frogobox.speechbooster.view.activity.ScriptEditorActivity"
        const val VIDEO_DETAIL_ACTIVITY = "com.frogobox.speechbooster.view.activity.VideoDetailActivity"
        const val RECORD_ACTIVITY = "com.frogobox.speechbooster.view.activity.RecordActivity"

    }

    object routeImplicit {

        // Detail Activity -------------------------------------------------------------------------
        fun startScriptDetailActivity(context: Context, data: Bundle, option: Bundle){
            navigatorImplicit(context, BASE_PACKAGE, SCRIPT_DETAIL_ACTIVITY, data, option = option)
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
    }

    object routeExplicit {

    }


}