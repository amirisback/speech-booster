package com.frogobox.speechbooster.viewmodel

import android.app.Application
import com.frogobox.speechbooster.base.BaseViewModel
import com.frogobox.speechbooster.helper.ConstHelper.Const.DEFAULT_ERROR_MESSAGE
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.view.callback.ScriptEditorViewCallback

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
class ScriptDetailViewModel(
    application: Application,
    private val frogoDataRepository: FrogoDataRepository
) : BaseViewModel(application) {

    fun deleteData(tableId: Int, callback: ScriptEditorViewCallback){
        callback.onShowProgress()
        if (frogoDataRepository.deleteRoomScript(tableId)) {
            callback.onHideProgress()
            callback.onSucces()
        } else {
            callback.onHideProgress()
            callback.onFailed(DEFAULT_ERROR_MESSAGE)
        }
    }

}
