package com.frogobox.speechbooster.source.remote

import android.content.Context
import com.frogobox.speechbooster.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.source.FrogoDataSource

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

    override fun saveRoomScript(data: Script): Boolean {
        return noAction()
    }

    override fun updateRoomScript(
        tableId: Int,
        title: String,
        description: String,
        dateTime: String
    ): Boolean {
        return noAction()
    }

    override fun deleteRoomScript(tableId: Int): Boolean {
        return noAction()
    }

    override fun getRoomScript(callback: FrogoDataSource.GetRoomDataCallBack<List<Script>>) {
        noAction()
    }

    override fun nukeRoomScript(): Boolean {
        return noAction()
    }

}