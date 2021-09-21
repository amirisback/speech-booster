/*
 * Created by faisalamir on 19/09/21
 * FrogoRecyclerView
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */

object ProjectSetting {
    // project settings
    const val NAME_APP = "Speech Booster"

    val NAME_APK = NAME_APP.toLowerCase().replace(" ", "-")
    val NAME_DB = NAME_APP.toLowerCase().replace(" ", "_")
    val DB = "\"$NAME_DB.db\""

    const val APP_DOMAIN = "com"
    const val APP_PLAY_CONSOLE = "frogobox"
    const val APP_NAME = "speechbooster"

    const val LIBRARY_NAME = "framework"

    const val VERSION_MAJOR = 2
    const val VERSION_MINOR = 0
    const val VERSION_PATCH = 4

    const val PROJECT_COMPILE_SDK = 31
    const val PROJECT_MIN_SDK = 21
    const val PROJECT_TARGET_SDK = PROJECT_COMPILE_SDK

    const val PROJECT_APP_ID = "$APP_DOMAIN.$APP_PLAY_CONSOLE.$APP_NAME"
    const val PROJECT_LIB_ID = "$APP_DOMAIN.$APP_PLAY_CONSOLE.$LIBRARY_NAME"
    const val PROJECT_VERSION_CODE = (VERSION_MAJOR * 100) + (VERSION_MINOR * 10) + (VERSION_PATCH * 1)
    const val PROJECT_VERSION_NAME = "$VERSION_MAJOR.$VERSION_MINOR.$VERSION_PATCH"

    const val PROJECT_JKS_STORE_FILE = "key-speech-booster.jks"
    const val PROJECT_JKS_STORE_PASS = "cronoclez"
    const val PROJECT_JKS_KEY_ALIAS = "frogobox"
    const val PROJECT_JKS_KEY_PASS = "xeonranger"

}