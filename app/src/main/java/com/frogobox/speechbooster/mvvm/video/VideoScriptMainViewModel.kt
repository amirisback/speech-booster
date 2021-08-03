package com.frogobox.speechbooster.mvvm.video

import android.app.Application
import com.frogobox.frogosdk.core.FrogoLiveEvent
import com.frogobox.speechbooster.core.BaseViewModel
import com.frogobox.speechbooster.source.model.VideoScript
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.source.FrogoDataSource

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
class VideoScriptMainViewModel (
    application: Application,
    private val frogoDataRepository: FrogoDataRepository
) : BaseViewModel(application) {

    val videoListLive = FrogoLiveEvent<List<VideoScript>>()

    fun getVideoData() {
        frogoDataRepository.getRoomVideoScript(object :
            FrogoDataSource.GetRoomDataCallBack<List<VideoScript>> {
            override fun onShowProgressDialog() {
                eventShowProgress.value = true
            }

            override fun onHideProgressDialog() {
                eventShowProgress.value = false
            }

            override fun onSuccess(data: List<VideoScript>) {
                videoListLive.value = data
                eventIsEmpty.value = false
            }

            override fun onEmpty() {
                eventIsEmpty.value = true
            }

            override fun onFinish() {

            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {

            }
        })
    }


}