package com.frogobox.speechbooster.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.frogobox.frogosdk.core.FrogoLiveEvent

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 18/08/2019.
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
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var eventShowProgress = FrogoLiveEvent<Boolean>()
    var eventIsEmpty = FrogoLiveEvent<Boolean>()
}