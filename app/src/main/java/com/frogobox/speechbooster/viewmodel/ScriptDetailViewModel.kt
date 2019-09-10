package com.frogobox.speechbooster.viewmodel

import android.app.Application
import com.frogobox.speechbooster.base.BaseViewModel
import com.frogobox.speechbooster.helper.ConstHelper.Const.DEFAULT_ERROR_MESSAGE
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.source.FrogoDataSource
import com.frogobox.speechbooster.util.SingleLiveEvent
import com.frogobox.speechbooster.view.callback.FavoriteEditorViewCallback
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

    val favoriteLive = SingleLiveEvent<FavoriteScript>()
    val isFavorite = SingleLiveEvent<Boolean>()

    fun deleteScriptData(tableId: Int, callback: ScriptEditorViewCallback){
        callback.onShowProgress()
        if (frogoDataRepository.deleteRoomScript(tableId)) {
            callback.onHideProgress()
            callback.onSucces()
        } else {
            callback.onHideProgress()
            callback.onFailed(DEFAULT_ERROR_MESSAGE)
        }
    }

    fun addFavoriteData(data: FavoriteScript, callback: FavoriteEditorViewCallback) {
        callback.onShowProgress()
        if (frogoDataRepository.saveRoomFavoriteScript(data)) {
            callback.onHideProgress()
            callback.onSucces()
        } else {
            callback.onHideProgress()
            callback.onFailed(DEFAULT_ERROR_MESSAGE)
        }
    }

    fun deleteFavoriteData(tableId: Int, callback: FavoriteEditorViewCallback) {
        callback.onShowProgress()
        if (frogoDataRepository.deleteRoomFavoriteScript(tableId)) {
            callback.onHideProgress()
            callback.onSucces()
        } else {
            callback.onHideProgress()
            callback.onFailed(DEFAULT_ERROR_MESSAGE)
        }
    }

    fun getFavoriteData(title : String) {
        frogoDataRepository.getRoomFavoriteScript(object :
            FrogoDataSource.GetRoomDataCallBack<List<FavoriteScript>> {
            override fun onShowProgressDialog() {

            }

            override fun onHideProgressDialog() {

            }

            override fun onSuccess(data: List<FavoriteScript>) {
                val tempFavoriteList = mutableListOf<FavoriteScript>()
                tempFavoriteList.clear()
                tempFavoriteList.addAll(data)

                for (i in tempFavoriteList.indices) {
                    if (tempFavoriteList[i].title.equals(title)) {
                        favoriteLive.value = tempFavoriteList[i]
                        isFavorite.value = true
                    }
                }

            }

            override fun onEmpty() {
                isFavorite.value = false
            }

            override fun onFinish() {

            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {

            }
        })
    }


}
