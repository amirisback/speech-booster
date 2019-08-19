package com.frogobox.speechbooster.navigation

import android.content.Context
import android.os.Bundle
import com.frogobox.speechbooster.navigation.Navigation.navigatorImplicit
import com.frogobox.speechbooster.navigation.Route.Endpoint.SCRIPT_DETAIL_ACTIVITY
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
    }

    object routeImplicit {
        fun openScriptDetailActivity(context: Context, data: Bundle){
            navigatorImplicit(context, BASE_PACKAGE, SCRIPT_DETAIL_ACTIVITY, data)
        }
    }

    object routeExplicit {

    }


}