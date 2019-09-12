package com.frogobox.speechbooster.view.callback

import com.frogobox.speechbooster.base.BaseViewCallback

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 12/09/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.view.callback
 *
 */

interface VideoRecordViewCallback : BaseViewCallback {

    override fun onShowProgress() {}

    override fun onHideProgress() {}

    override fun onSucces() {}

    override fun onFailed(message: String) {}

}