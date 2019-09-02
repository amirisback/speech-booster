package com.frogobox.speechbooster.source

import com.frogobox.speechbooster.base.BaseDataSource
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.util.SingleLiveEvent

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
    fun saveRoomScript(data: Script)

    // Get
    fun getRoomScript(callback: GetRoomDataCallBack<List<Script>>)

    // Update
    fun updateRoomScript(data: Script, param: String)

    // Delete
    fun deleteRoomScript(param: String)
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