package com.frogobox.speechbooster.mvvm.script

import android.app.Application
import android.util.Log
import com.frogobox.sdk.core.FrogoDate.DATE_EEEE_DD_MM_YYYY
import com.frogobox.sdk.core.FrogoDate.getCurrentDate
import com.frogobox.sdk.core.FrogoLiveEvent
import com.frogobox.sdk.core.FrogoViewModel
import com.frogobox.speechbooster.util.ConstHelper.Const.DEFAULT_ERROR_MESSAGE
import com.frogobox.speechbooster.source.model.FavoriteScript
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.source.FrogoDataSource
import com.frogobox.speechbooster.source.local.LocalDataCallback
import com.frogobox.speechbooster.util.ConstHelper.Const.OPTION_DELETE
import com.frogobox.speechbooster.util.ConstHelper.Const.OPTION_GET

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
) : FrogoViewModel(application) {

    val isFavoriteLive = FrogoLiveEvent<Boolean>()

    fun deleteScriptData(tableId: Int, callback: LocalDataCallback){
        callback.onShowProgress()
        if (frogoDataRepository.deleteRoomScript(tableId)) {
            callback.onHideProgress()
            callback.onSuccesDelete()
        } else {
            callback.onHideProgress()
            callback.onFailed(DEFAULT_ERROR_MESSAGE)
        }
    }

    private fun addFavoriteData(data: FavoriteScript, callback: LocalDataCallback) {
        callback.onShowProgress()
        if (frogoDataRepository.saveRoomFavoriteScript(data)) {
            callback.onHideProgress()
            callback.onSuccesInsert()
            isFavoriteLive.value = true
        } else {
            callback.onHideProgress()
            callback.onFailed(DEFAULT_ERROR_MESSAGE)
        }
    }

    private fun deleteFavoriteData(script_id: String, callback: LocalDataCallback) {
        callback.onShowProgress()
        if (frogoDataRepository.deleteRoomFavoriteScriptId(script_id)) {
            callback.onHideProgress()
            callback.onSuccesInsert()
            isFavoriteLive.value = false
        } else {
            callback.onHideProgress()
            callback.onFailed(DEFAULT_ERROR_MESSAGE)
        }
    }

    private fun executeDelete(favoriteScript: FavoriteScript, option: String){
        Log.d("Data Ini", "Sesusai dengan extra")
        Log.d("Favorite Room ScriptId", favoriteScript.script_id!!)
        Log.d("Favorite Room TableId", favoriteScript.table_id.toString())
        if (option == OPTION_GET){
            isFavoriteLive.value = true
        } else if (option == OPTION_DELETE){
            deleteFavoriteData(favoriteScript.script_id!!, object : LocalDataCallback {
                override fun onShowProgress() {}
                override fun onHideProgress() {}
                override fun onSuccesInsert() {}
                override fun onSuccesDelete() {}
                override fun onFailed(message: String) {}
            })
        }
    }

    fun getFavoriteData(extraScriptId : String, option: String) {
        val paramScript = extraScriptId
        frogoDataRepository.getRoomFavoriteScript(object :
            FrogoDataSource.GetRoomDataCallBack<List<FavoriteScript>> {
            override fun onShowProgressDialog() {}
            override fun onHideProgressDialog() {}
            override fun onSuccess(data: List<FavoriteScript>) {

                val tempFavoriteList = mutableListOf<FavoriteScript>()
                tempFavoriteList.clear()
                tempFavoriteList.addAll(data)

                for (i in tempFavoriteList.indices) {
//                    Log.d("Favorite Room ScriptId "+i, tempFavoriteList[i].script_id!!)
//                    Log.d("Favorite Room TableId "+i, tempFavoriteList[i].table_id.toString())
//                    Log.d("Hasil cek paramScript", paramScript)

                    if (tempFavoriteList[i].script_id!! == paramScript) {
                        executeDelete(tempFavoriteList[i], option)
                        break
                    } else {
                        isFavoriteLive.value = false
                    }

                }

            }

            override fun onEmpty() {
                isFavoriteLive.value = false
                Log.d("Isi Favorite", "KOSONG")
            }

            override fun onFinish() {}
            override fun onFailed(statusCode: Int, errorMessage: String?) {}
        })
    }

    fun deleteFromFavorite(script_id: String){
        getFavoriteData(script_id, OPTION_DELETE)
    }

    fun addToFavorite(title: String, script_id: String, description: String, callback: LocalDataCallback){
        val dateNow = getCurrentDate(DATE_EEEE_DD_MM_YYYY)
        val favoriteScript = FavoriteScript(
            title = title,
            script_id = script_id,
            dateTime = dateNow,
            description = description)
        addFavoriteData(favoriteScript, callback)
    }



}
