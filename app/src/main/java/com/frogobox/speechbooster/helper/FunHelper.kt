package com.frogobox.speechbooster.helper

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import com.frogobox.speechbooster.BuildConfig
import com.frogobox.speechbooster.helper.ConstHelper.Pref.PREF_NAME
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PublicSpeakingBooster
 * Copyright (C) 16/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.publicspeakingbooster.helper
 *
 */
class FunHelper {

    object ConverterJson {

        fun <T> toJson(model: T) : String? {
            return Gson().toJson(model)
        }

        inline fun <reified T> fromJson(word: String?) : T {
            return Gson().fromJson<T>(word, T::class.java)
        }

    }

    object Func {

        fun noAction() : Boolean {
            return true
        }

        fun isNetworkAvailable(context: Context): Boolean? {
            var isConnected: Boolean? = false // Initial Value
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected) isConnected = true
            return isConnected
        }

        fun showVersion() : String {
            return "Version " + BuildConfig.VERSION_NAME
        }

        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

    }

    object Preference {

        fun getSp(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        }

        object Save {
            fun savePrefFloat(sharedPreferences: SharedPreferences, constPref: String, data: Float) {
                sharedPreferences.edit().putFloat(constPref, data).apply()
            }

            fun savePrefInt(sharedPreferences: SharedPreferences, constPref: String, data: Int) {
                sharedPreferences.edit().putInt(constPref, data).apply()
            }

            fun savePrefString(sharedPreferences: SharedPreferences, constPref: String, data: String) {
                sharedPreferences.edit().putString(constPref, data).apply()
            }

            fun savePrefBoolean(sharedPreferences: SharedPreferences, constPref: String, data: Boolean) {
                sharedPreferences.edit().putBoolean(constPref, data).apply()
            }

            fun savePrefLong(sharedPreferences: SharedPreferences, constPref: String, data: Long) {
                sharedPreferences.edit().putLong(constPref, data).apply()
            }

        }

        object Delete {

            fun deletePref(sharedPreferences: SharedPreferences, constPref: String) {
                sharedPreferences.edit().remove(constPref).apply()
            }

        }

        object Load {

            fun loadPrefFloat(sharedPreferences: SharedPreferences, constPref: String): Float {
                return sharedPreferences.getFloat(constPref, 0f)
            }

            fun loadPrefString(sharedPreferences: SharedPreferences, constPref: String): String {
                return sharedPreferences.getString(constPref, "")!!
            }

            fun loadPrefInt(sharedPreferences: SharedPreferences, constPref: String): Int {
                return sharedPreferences.getInt(constPref, 0)
            }

            fun loadPrefLong(sharedPreferences: SharedPreferences, constPref: String): Long {
                return sharedPreferences.getLong(constPref, 0)
            }

            fun loadPrefBoolean(sharedPreferences: SharedPreferences, constPref: String): Boolean {
                return sharedPreferences.getBoolean(constPref, false)
            }

        }

    }
}