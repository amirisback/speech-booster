package com.frogobox.speechbooster.helper

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

    object ApiUrl {

    }

    object Date {

        // Format Second
        const val SECOND_MILLIS = 1000
        const val MINUTE_MILLIS = 60 * SECOND_MILLIS
        const val HOUR_MILLIS = 60 * MINUTE_MILLIS
        const val DAY_MILLIS = 24 * HOUR_MILLIS

        // Format Date
        const val DATE_TIME_GLOBAL = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" //
        const val DATE_TIME_STANDARD = "yyyy-MM-dd HH:mm:ss" // 2018-10-02 12:12:12
        const val DATE_ENGLISH_YYYY_MM_DD = "yyyy-MM-dd" // 2018-10-02
        const val DATE_ENGLISH_YYYY_MM_DD_CLEAR = "yyyy MM dd" // 2018 10 02
        const val DATE_DD_MM_YYYY = "dd-MM-yyyy" // 02-10-2018
        const val DATE_DD_MM_YYYY_CLEAR = "dd MM yyyy" // 02-10-2018

        // Format Time
        const val TIME_GENERAL_HH_MM_SS = "HH:mm:ss" // 12:12:12
        const val TIME_GENERAL_HH_MM = "HH:mm" // 12:12

        // Format Day
        const val DAY_WITH_DATE_TIME_ENGLISH = "EEE, MMM dd yyyy HH:mm" // Mon, Aug 12 2018 12:12
        const val DAY_WITH_DATE_TIME_LOCALE = "EEE, dd MMM yyyy HH:mm" // Sen, 12 Agt 2019 12:12
        const val DAY_WITH_DATE_TIME_ENGLISH_FULL = "EEEE, MMMM dd yyyy HH:mm" // Monday, August 12 2018 12:12
        const val DAY_WITH_DATE_TIME_LOCALE_FULL = "EEEE, dd MMMM yyyy HH:mm" // Senin, 12 Agustus 2018 12:12

    }

    object Pref {
        const val PREF_NAME = "SpeechBooster"
    }

    object Extra {
        const val EXTRA_NOTE = "EXTRA_NOTE"
    }

    object Tag {
        const val TAG_DEFAULT_FORM = 7000
    }

    object Const {
        const val JOGET_VAR_ORG = "org"

    }

}