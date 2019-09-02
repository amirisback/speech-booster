package com.frogobox.speechbooster.source.remote

import android.content.Context
import com.frogobox.speechbooster.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.source.FrogoDataSource
import com.frogobox.speechbooster.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PublicSpeakingBooster
 * Copyright (C) 16/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.publicspeakingbooster.source.remote
 *
 */
class FrogoRemoteDataSource(private val context: Context) : FrogoDataSource {

    override fun saveRoomScript(data: Script) {
        noAction()
    }

    override fun updateRoomScript(data: Script, param: String) {
        noAction()
    }

    override fun deleteRoomScript(param: String) {
        noAction()
    }

    override fun getRoomScript(callback: FrogoDataSource.GetRoomDataCallBack<List<Script>>) {
        noAction()
    }


}