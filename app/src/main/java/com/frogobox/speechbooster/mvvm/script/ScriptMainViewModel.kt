package com.frogobox.speechbooster.mvvm.script

import android.app.Application
import com.frogobox.sdk.core.FrogoLiveEvent
import com.frogobox.sdk.core.FrogoViewModel
import com.frogobox.speechbooster.source.model.Script
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.source.FrogoDataSource

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
 * com.frogobox.speechbooster.viewmodel
 *
 */
class ScriptMainViewModel(
    application: Application,
    private val frogoDataRepository: FrogoDataRepository
) : FrogoViewModel(application) {

    var scriptListLive = FrogoLiveEvent<List<Script>>()

    fun getScriptData() {
        frogoDataRepository.getRoomScript(object :
            FrogoDataSource.GetRoomDataCallBack<List<Script>> {
            override fun onShowProgressDialog() {
                eventShowProgress.value = true
            }

            override fun onHideProgressDialog() {
                eventShowProgress.value = false
            }

            override fun onSuccess(data: List<Script>) {
                scriptListLive.value = data
                eventEmptyData.value = false
            }

            override fun onEmpty() {
                eventEmptyData.value = true
            }

            override fun onFinish() {

            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {

            }
        })
    }

}