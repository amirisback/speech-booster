package com.frogobox.speechbooster.source

import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.source.local.FrogoLocalDataSource
import com.frogobox.speechbooster.source.remote.FrogoRemoteDataSource
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
open class FrogoDataRepository(val remoteDataSource: FrogoRemoteDataSource,
                               val localDataSource: FrogoLocalDataSource) : FrogoDataSource {

    override fun saveRoomScript(data: Script) {
        localDataSource.saveRoomScript(data)
    }

    override fun updateRoomScript(data: Script, param: String) {
        localDataSource.updateRoomScript(data, param)
    }

    override fun deleteRoomScript(param: String) {
        localDataSource.deleteRoomScript(param)
    }

    override fun getRoomScript(callback: FrogoDataSource.GetRoomDataCallBack<List<Script>>) {
       return localDataSource.getRoomScript(callback)
    }


    companion object {

        private var INSTANCE: FrogoDataRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         * @param FrogoRemoteDataSource backend data source
         * *
         * @return the [FrogoRepository] instance
         */
        @JvmStatic
        fun getInstance(FrogoRemoteDataSource: FrogoRemoteDataSource, gitsLocalDataSource: FrogoLocalDataSource) =
            INSTANCE ?: synchronized(FrogoDataRepository::class.java) {
                INSTANCE ?: FrogoDataRepository(FrogoRemoteDataSource, gitsLocalDataSource)
                    .also { INSTANCE = it }
            }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
    
}