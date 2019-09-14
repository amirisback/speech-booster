package com.frogobox.speechbooster.viewmodel

import android.app.Application
import com.frogobox.speechbooster.base.util.BaseViewModel
import com.frogobox.speechbooster.source.FrogoDataRepository

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 04/09/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.viewmodel
 *
 */
class FavoriteScriptDetailViewModel (
    application: Application,
    private val frogoDataRepository: FrogoDataRepository
) : BaseViewModel(application) {

}