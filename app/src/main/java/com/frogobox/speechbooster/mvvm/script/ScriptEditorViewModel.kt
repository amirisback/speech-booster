package com.frogobox.speechbooster.mvvm.script

import android.app.Application
import com.frogobox.sdk.view.FrogoViewModel
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.source.local.LocalDataCallback
import com.frogobox.speechbooster.source.model.Script
import com.frogobox.speechbooster.util.ConstHelper.Const.DEFAULT_ERROR_MESSAGE

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 26/08/2019.
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
class ScriptEditorViewModel(
    application: Application,
    private val frogoDataRepository: FrogoDataRepository
) : FrogoViewModel(application) {

    fun saveScriptData(data: Script, callback: LocalDataCallback) {
        callback.onShowProgress()
        if (frogoDataRepository.saveRoomScript(data)) {
            callback.onHideProgress()
            callback.onSuccesInsert()
        } else {
            callback.onHideProgress()
            callback.onFailed(DEFAULT_ERROR_MESSAGE)
        }
    }

    fun updateScriptData(tableId: Int, data: Script, callback: LocalDataCallback) {
        callback.onShowProgress()
        val newTitle = data.title!!
        val newDescription = data.description!!
        val newDateTime = data.dateTime!!
        if (frogoDataRepository.updateRoomScript(tableId, newTitle, newDescription, newDateTime)) {
            callback.onHideProgress()
            callback.onSuccesInsert()
        } else {
            callback.onHideProgress()
            callback.onFailed(DEFAULT_ERROR_MESSAGE)
        }
    }

}