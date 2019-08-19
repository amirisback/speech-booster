package com.frogobox.speechbooster.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.frogobox.speechbooster.helper.ConstHelper.TypeData.TYPE_BOOLEAN
import com.frogobox.speechbooster.helper.ConstHelper.TypeData.TYPE_FLOAT
import com.frogobox.speechbooster.helper.ConstHelper.TypeData.TYPE_INT
import com.frogobox.speechbooster.helper.ConstHelper.TypeData.TYPE_STRING
import com.frogobox.speechbooster.helper.FunHelper

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

object Navigation {

    lateinit var bundle: Any

    object createBundle {

        fun <T> baseCreateBundleObject(extraKey: String, data: T) : Bundle {
            val extraData = FunHelper.ConverterJson.toJson(data)
            val extraBundle = Bundle()
            extraBundle.putString(extraKey, extraData)
            return extraBundle
        }

        fun <T> baseCreateBundle(typeKey: String, extraKey: String, data: T) : Bundle {
            val extraBundle = Bundle()
            if (typeKey.equals(TYPE_INT)) {
                extraBundle.putString(extraKey, data as String)
            } else if (typeKey.equals(TYPE_STRING)) {
                extraBundle.putInt(extraKey, data as Int)
            } else if (typeKey.equals(TYPE_FLOAT)) {
                extraBundle.putFloat(extraKey, data as Float)
            } else if (typeKey.equals(TYPE_BOOLEAN)) {
                extraBundle.putBoolean(extraKey, data as Boolean)
            }
            return extraBundle
        }

    }

    object getBundle {

        inline fun <reified Model> baseGetBundleObject(activity: Activity, extraKey: String) : Model {
            val extraBundle = activity.intent.extras?.getString(extraKey)
            val extraData = FunHelper.ConverterJson.fromJson<Model>(extraBundle)
            return extraData
        }

        fun <T> baseGetBundle(typeKey: String, activity: Activity, extraKey: String) : T {
            if (typeKey.equals(TYPE_INT)) {
                bundle = activity.intent.extras?.getInt(extraKey)!!
            } else if (typeKey.equals(TYPE_STRING)) {
                bundle = activity.intent.extras?.getString(extraKey)!!
            } else if (typeKey.equals(TYPE_FLOAT)) {
                bundle = activity.intent.extras?.getFloat(extraKey)!!
            } else if (typeKey.equals(TYPE_BOOLEAN)) {
                bundle = activity.intent.extras?.getBoolean(extraKey)!!
            }
            return bundle as T
        }

    }

    fun navigatorImplicit(context: Context, activityPackage: String, className: String,
                          extras: Bundle = Bundle(), clearStack: Boolean = false, option: Bundle? = null) {
        val intent = Intent()
        try {
            intent.setClassName(activityPackage, className).putExtras(extras)

            if (clearStack) {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            if (option != null) {
                context.startActivity(intent, option)
            } else {
                context.startActivity(intent)
            }

        } catch (e: Exception) {
            Toast.makeText(context, "Activity not found", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

}