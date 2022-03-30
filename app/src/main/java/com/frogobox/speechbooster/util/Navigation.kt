package com.frogobox.speechbooster.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.frogobox.speechbooster.util.ConstHelper.Extra.EXTRA_OPTION
import com.frogobox.speechbooster.util.ConstHelper.TypeData.TYPE_BOOLEAN
import com.frogobox.speechbooster.util.ConstHelper.TypeData.TYPE_FLOAT
import com.frogobox.speechbooster.util.ConstHelper.TypeData.TYPE_INT
import com.frogobox.speechbooster.util.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.ConstHelper.TypeData.TYPE_STRING
import com.google.gson.Gson

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

object Navigation {

    lateinit var bundle: Any

    object BundleHelper {

        fun <T> createBaseBundle(typeKey: String, extraKey: String, data: T): Bundle {
            val extraBundle = Bundle()
            when {
                typeKey == TYPE_INT -> {
                    extraBundle.putInt(extraKey, data as Int)
                }
                typeKey == TYPE_STRING -> {
                    extraBundle.putString(extraKey, data as String)
                }
                typeKey == TYPE_FLOAT -> {
                    extraBundle.putFloat(extraKey, data as Float)
                }
                typeKey == TYPE_OBJECT -> {
                    val extraData = Gson().toJson(data)
                    extraBundle.putString(extraKey, extraData)
                }
                typeKey == TYPE_BOOLEAN -> {
                    extraBundle.putBoolean(extraKey, data as Boolean)
                }
            }
            return extraBundle
        }

        inline fun <reified T> getBaseBundle(
            activity: Activity,
            typeKey: String,
            extraKey: String
        ): T {
            when {
                typeKey == TYPE_INT -> {
                    bundle = activity.intent.extras?.getInt(extraKey)!!
                }
                typeKey == TYPE_STRING -> {
                    bundle = activity.intent.extras?.getString(extraKey)!!
                }
                typeKey == TYPE_FLOAT -> {
                    bundle = activity.intent.extras?.getFloat(extraKey)!!
                }
                typeKey == TYPE_OBJECT -> {
                    val extraBundle = activity.intent.extras?.getString(extraKey)
                    bundle = Gson().fromJson(extraBundle, T::class.java)!!
                }
                typeKey == TYPE_BOOLEAN -> {
                    bundle = activity.intent.extras?.getBoolean(extraKey)!!
                }
            }
            return bundle as T
        }

        fun createOptionBundle(tag: Int): Bundle {
            val extraBundle = Bundle()
            extraBundle.putInt(EXTRA_OPTION, tag)
            return extraBundle
        }

        fun getOptionBundle(activity: Activity): Int {
            return activity.intent.extras?.getInt(EXTRA_OPTION)!!
        }

    }

    fun navigatorImplicit(
        context: Context, activityPackage: String, className: String,
        extras: Bundle? = null, clearStack: Boolean = false, option: Bundle? = null
    ) {
        val intent = Intent()
        Log.d("Activity Package", activityPackage)
        Log.d("className", className)
        try {
            extras?.let { intent.setClassName(activityPackage, className).putExtras(it) }
            option?.let { intent.setClassName(activityPackage, className).putExtras(it) }

            if (clearStack) {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            context.startActivity(intent)

        } catch (e: Exception) {
            Toast.makeText(context, "Activity not found", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

}