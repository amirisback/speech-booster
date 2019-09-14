package com.frogobox.speechbooster.viewmodel

import android.app.Application
import com.frogobox.speechbooster.base.util.BaseViewModel
import com.frogobox.speechbooster.model.VideoScript
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.util.helper.ConstHelper.Const.DEFAULT_ERROR_MESSAGE
import com.frogobox.speechbooster.view.callback.VideoRecordViewCallback

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
) : BaseViewModel(application) {

    fun saveVideoData(data: VideoScript, callback: VideoRecordViewCallback) {
        callback.onShowProgress()
        if (frogoDataRepository.saveRoomVideoScript(data)) {
            callback.onHideProgress()
            callback.onSucces()
        } else {
            callback.onHideProgress()
            callback.onFailed(DEFAULT_ERROR_MESSAGE)
        }
    }

}