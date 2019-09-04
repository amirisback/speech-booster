package com.frogobox.speechbooster.source.remote

import android.content.Context
import com.frogobox.speechbooster.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.model.VideoScript
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
    override fun saveRoomFavoriteScript(data: FavoriteScript): Boolean {
        return noAction()
    }

    override fun saveRoomVideoScript(data: VideoScript): Boolean {
        return noAction()
    }

    override fun getRoomFavoriteScript(callback: FrogoDataSource.GetRoomDataCallBack<List<FavoriteScript>>) {
        noAction()
    }

    override fun getRoomVideoScript(callback: FrogoDataSource.GetRoomDataCallBack<List<VideoScript>>) {
        noAction()
    }

    override fun updateRoomFavoriteScript(
        tableId: Int,
        title: String,
        description: String,
        dateTime: String
    ): Boolean {
        return noAction()
    }

    override fun updateRoomVideoScript(tableId: Int, urlVideo: String): Boolean {
        return noAction()
    }

    override fun deleteRoomFavoriteScript(tableId: Int): Boolean {
        return noAction()
    }

    override fun deleteRoomVideoScript(tableId: Int): Boolean {
        return noAction()
    }

    override fun nukeRoomFavoriteScript(): Boolean {
        return noAction()
    }

    override fun nukeRoomVideoScript(): Boolean {
        return noAction()
    }

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