package com.frogobox.speechbooster.mvvm.video

import android.app.Application
import com.frogobox.sdk.core.FrogoViewModel
import com.frogobox.speechbooster.source.model.VideoScript
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.source.local.LocalDataCallback
import com.frogobox.speechbooster.util.ConstHelper.Const.DEFAULT_ERROR_MESSAGE

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
 * com.frogobox.speechbooster.viewmodel
 *
 */
class VideoScriptRecordViewModel  (
    application: Application,
    private val frogoDataRepository: FrogoDataRepository
) : FrogoViewModel(application) {

    fun saveVideoData(data: VideoScript, callback: LocalDataCallback) {
        callback.onShowProgress()
        if (frogoDataRepository.saveRoomVideoScript(data)) {
            callback.onHideProgress()
            callback.onSuccesInsert()
        } else {
            callback.onHideProgress()
            callback.onFailed(DEFAULT_ERROR_MESSAGE)
        }
    }

}