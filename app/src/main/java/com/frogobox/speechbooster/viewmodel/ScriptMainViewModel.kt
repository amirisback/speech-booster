package com.frogobox.speechbooster.viewmodel

import android.app.Application
import com.frogobox.speechbooster.base.BaseViewModel
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.source.FrogoDataSource
import com.frogobox.speechbooster.util.SingleLiveEvent

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
) : BaseViewModel(application) {

    val scriptListLive = SingleLiveEvent<List<Script>>()
    val isEmpty = SingleLiveEvent<Boolean>()

    fun getScriptData() {
        frogoDataRepository.getRoomScript(object :
            FrogoDataSource.GetRoomDataCallBack<List<Script>> {
            override fun onShowProgressDialog() {

            }

            override fun onHideProgressDialog() {

            }

            override fun onSuccess(data: List<Script>) {
                scriptListLive.value = data
                isEmpty.value = false
            }

            override fun onEmpty() {
                isEmpty.value = true
            }

            override fun onFinish() {

            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {

            }
        })
    }

}