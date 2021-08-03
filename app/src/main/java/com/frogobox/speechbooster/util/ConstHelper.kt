package com.frogobox.speechbooster.util

import android.Manifest
import android.os.Environment

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
class ConstHelper {

    object RoomDatabase {
        const val DATABASE_NAME = "speechbooster.db"
    }

    object TypeData {
        const val TYPE_INT = "TYPE_INT"
        const val TYPE_STRING = "TYPE_STRING"
        const val TYPE_FLOAT = "TYPE_FLOAT"
        const val TYPE_BOOLEAN = "TYPE_BOOLEAN"
        const val TYPE_OBJECT = "TYPE_OBJECT"
    }

    object Code {
        const val CODE_NAME = 1
        const val CODE_REQUEST_VIDEO_PERMISSIONS = 1
        val CODE_VIDEO_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)

    }

    object Arg {
        const val BASE_ARGUMENTS = "com.frogobox.speechbooster"
        const val ARGUMENTS_SCRIPT = "$BASE_ARGUMENTS.ARGUMENTS_SCRIPT"
        const val ARGUMENTS_EXAMPLE_SCRIPT = "$BASE_ARGUMENTS.ARGUMENTS_EXAMPLE_SCRIPT"
        const val ARGUMENTS_FAVORITE_SCRIPT = "$BASE_ARGUMENTS.ARGUMENTS_FAVORITE_SCRIPT"
    }

    object Pref {
        const val PREF_NAME = "SpeechBooster"
    }

    object Extra {
        const val BASE_EXTRA = "com.frogobox.speechbooster"
        const val EXTRA_OPTION = "$BASE_EXTRA.EXTRA_OPTION"
        const val EXTRA_SCRIPT = "$BASE_EXTRA.EXTRA_SCRIPT"
        const val EXTRA_FAVORITE_SCRIPT = "$BASE_EXTRA.EXTRA_FAVORITE_SCRIPT"
        const val EXTRA_EXAMPLE_SCRIPT = "$BASE_EXTRA.EXTRA_EXAMPLE_SCRIPT"
        const val EXTRA_CATEGORY = "$BASE_EXTRA.EXTRA_CATEGORY"

    }

    object Value {
        const val VALUE_SENSOR_ORIENTATION_DEFAULT_DEGREES = 90
        const val VALUE_SENSOR_ORIENTATION_INVERSE_DEGREES = 270

    }

    object Tag {
        const val TAG_ACTIVITY_EDIT = 300
        const val TAG_ACTIVITY_CREATE = 301
        const val TAG_ACTIVITY_DETAIL = 302

        const val TAG_FROM_ACTIVITY = 801
        const val TAG_FROM_FRAGMENT = 800

        const val TAG_CAMERA_VIDEO = "Camera2VideoFragment"

    }

    object Ext {
        const val MP4 = ".mp4"
        const val PNG = ".png"
    }

    object Dir {
        const val BASE_FILE_NAME = "SPEECH_"
        const val BASE_DIR_NAME = "SpeechBooster"
        val DIR_NAME = "${Environment.DIRECTORY_PICTURES}/$BASE_DIR_NAME"

        val VIDEO_FILE_NAME = "$BASE_FILE_NAME${System.currentTimeMillis()}${Ext.MP4}"

    }

    object Const {

        const val OPTION_GET = "OPTION_GET"
        const val OPTION_DELETE = "OPTION_DELETE"

        const val DEFAULT_NULL = "null"
        const val DEFAULT_ERROR_MESSAGE = "Failed"
        const val FRAGMENT_DIALOG = "dialog"

        const val SPLASH_INTERVAL = 500

    }

}