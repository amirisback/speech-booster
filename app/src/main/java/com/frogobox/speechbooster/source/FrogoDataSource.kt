package com.frogobox.speechbooster.source

import com.frogobox.speechbooster.base.BaseDataSource
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.model.VideoScript

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 18/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.source
 *
 */
interface FrogoDataSource : BaseDataSource {

    // Room Database -------------------------------------------------------------------------------
    // Save
    fun saveRoomScript(data: Script) : Boolean
    fun saveRoomFavoriteScript(data: FavoriteScript) : Boolean
    fun saveRoomVideoScript(data: VideoScript) : Boolean

    // Get
    fun getRoomScript(callback: GetRoomDataCallBack<List<Script>>)
    fun getRoomFavoriteScript(callback: GetRoomDataCallBack<List<FavoriteScript>>)
    fun getRoomVideoScript(callback: GetRoomDataCallBack<List<VideoScript>>)

    // Update
    fun updateRoomScript(tableId: Int, title: String, description: String, dateTime: String) : Boolean
    fun updateRoomScriptFav(tableId: Int, favorite: Boolean) : Boolean
    fun updateRoomFavoriteScript(tableId: Int, title: String, description: String, dateTime: String) : Boolean
    fun updateRoomVideoScript(tableId: Int, urlVideo: String) : Boolean

    // Delete
    fun deleteRoomScript(tableId: Int) : Boolean
    fun deleteRoomFavoriteScript(tableId: Int) : Boolean
    fun deleteRoomVideoScript(tableId: Int) : Boolean

    // Nuke
    fun nukeRoomScript() : Boolean
    fun nukeRoomFavoriteScript() : Boolean
    fun nukeRoomVideoScript() : Boolean
    // ---------------------------------------------------------------------------------------------

    // Interface Helper ---------------------------------------------------------------------------
    // Save
    interface SaveRoomDataCallBack<T>: BaseDataSource.ResponseCallback<T>
    interface SavePrefCallBack<T>: BaseDataSource.ResponseCallback<T>

    // Get
    interface GetRoomDataCallBack<T> : BaseDataSource.ResponseCallback<T>

    // Update
    interface UpdateRoomDataCallBack<T> : BaseDataSource.ResponseCallback<T>

    // Delete
    interface DeleteRoomDataCallBack<T> : BaseDataSource.ResponseCallback<T>
    // ---------------------------------------------------------------------------------------------

}