package com.frogobox.speechbooster.mvvm.favorite

import android.app.Application
import com.frogobox.frogosdk.core.FrogoLiveEvent
import com.frogobox.frogosdk.core.FrogoViewModel
import com.frogobox.speechbooster.source.model.FavoriteScript
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
class FavoriteScriptMainViewModel (
    application: Application,
    private val frogoDataRepository: FrogoDataRepository
) : FrogoViewModel(application) {

    var favoriteListLive = FrogoLiveEvent<List<FavoriteScript>>()

    fun getFavoriteData() {
        frogoDataRepository.getRoomFavoriteScript(object :
            FrogoDataSource.GetRoomDataCallBack<List<FavoriteScript>> {
            override fun onShowProgressDialog() {
                eventShowProgress.value = true
            }

            override fun onHideProgressDialog() {
                eventShowProgress.value = false
            }

            override fun onSuccess(data: List<FavoriteScript>) {
                favoriteListLive.value = data
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