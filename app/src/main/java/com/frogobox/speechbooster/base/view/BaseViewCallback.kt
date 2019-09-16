package com.frogobox.speechbooster.base.view

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 02/09/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.base
 *
 */
interface BaseViewCallback {
    fun onShowProgress()
    fun onHideProgress()
    fun onSuccesInsert()
    fun onSuccesDelete()
    fun onFailed(message: String)
}