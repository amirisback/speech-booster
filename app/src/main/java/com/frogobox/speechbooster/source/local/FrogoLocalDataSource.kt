package com.frogobox.speechbooster.source.local

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import com.frogobox.speechbooster.base.BaseCallback
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.model.VideoScript
import com.frogobox.speechbooster.source.FrogoDataSource
import com.frogobox.speechbooster.source.dao.FavoriteScriptDao
import com.frogobox.speechbooster.source.dao.ScriptDao
import com.frogobox.speechbooster.source.dao.VideoScriptDao
import com.frogobox.speechbooster.util.AppExecutors
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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
 * com.frogobox.publicspeakingbooster.source.local
 *
 */
class FrogoLocalDataSource private constructor(
    private val appExecutors: AppExecutors,
    private val sharedPreferences: SharedPreferences,
    private val scriptDao: ScriptDao,
    private val favoriteScriptDao: FavoriteScriptDao,
    private val videoScriptDao: VideoScriptDao
) : FrogoDataSource {

    override fun updateRoomScriptFav(tableId: Int, favorite: Boolean): Boolean {
        appExecutors.diskIO.execute {
            scriptDao.updateFavorite(tableId, favorite)
        }
        return true
    }

    override fun saveRoomFavoriteScript(data: FavoriteScript): Boolean {
        appExecutors.diskIO.execute {
            favoriteScriptDao.insertData(data)
        }
        return true
    }

    override fun saveRoomVideoScript(data: VideoScript): Boolean {
        appExecutors.diskIO.execute {
            videoScriptDao.insertData(data)
        }
        return true
    }

    override fun getRoomFavoriteScript(callback: FrogoDataSource.GetRoomDataCallBack<List<FavoriteScript>>) {
        appExecutors.diskIO.execute {
            favoriteScriptDao.getAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseCallback<List<FavoriteScript>>() {
                    override fun onCallbackSucces(data: List<FavoriteScript>) {
                        callback.onShowProgressDialog()
                        callback.onSuccess(data)
                        callback.onHideProgressDialog()
                    }

                    override fun onCallbackError(code: Int, errorMessage: String) {
                        callback.onFailed(code, errorMessage)
                    }

                    override fun onAddSubscribe(disposable: Disposable) {
                        addSubscribe(disposable = disposable)
                    }

                    override fun onCompleted() {
                        callback.onHideProgressDialog()
                    }
                })
        }
    }

    override fun getRoomVideoScript(callback: FrogoDataSource.GetRoomDataCallBack<List<VideoScript>>) {
        appExecutors.diskIO.execute {
            videoScriptDao.getAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseCallback<List<VideoScript>>() {
                    override fun onCallbackSucces(data: List<VideoScript>) {
                        callback.onShowProgressDialog()
                        callback.onSuccess(data)
                        callback.onHideProgressDialog()
                    }

                    override fun onCallbackError(code: Int, errorMessage: String) {
                        callback.onFailed(code, errorMessage)
                    }

                    override fun onAddSubscribe(disposable: Disposable) {
                        addSubscribe(disposable = disposable)
                    }

                    override fun onCompleted() {
                        callback.onHideProgressDialog()
                    }
                })
        }
    }

    override fun updateRoomFavoriteScript(
        tableId: Int,
        title: String,
        description: String,
        dateTime: String
    ): Boolean {
        appExecutors.diskIO.execute {
            favoriteScriptDao.updateData(tableId, title, description, dateTime)
        }
        return true
    }

    override fun updateRoomVideoScript(tableId: Int, urlVideo: String): Boolean {
        appExecutors.diskIO.execute {
            videoScriptDao.updateData(tableId, urlVideo)
        }
        return true
    }

    override fun deleteRoomFavoriteScript(tableId: Int): Boolean {
        appExecutors.diskIO.execute {
            favoriteScriptDao.deleteData(tableId)
        }
        return true
    }

    override fun deleteRoomVideoScript(tableId: Int): Boolean {
        appExecutors.diskIO.execute {
            videoScriptDao.deleteData(tableId)
        }
        return true
    }

    override fun nukeRoomFavoriteScript(): Boolean {
        appExecutors.diskIO.execute {
            favoriteScriptDao.nukeData()
        }
        return true
    }

    override fun nukeRoomVideoScript(): Boolean {
        appExecutors.diskIO.execute {
            videoScriptDao.nukeData()
        }
        return true
    }

    override fun saveRoomScript(data: Script): Boolean {
        appExecutors.diskIO.execute {
            scriptDao.insertData(data)
        }
        return true
    }

    override fun updateRoomScript(
        tableId: Int,
        title: String,
        description: String,
        dateTime: String
    ): Boolean {
        appExecutors.diskIO.execute {
            scriptDao.updateData(tableId, title, description, dateTime)
        }
        return true
    }

    override fun deleteRoomScript(tableId: Int): Boolean {
        appExecutors.diskIO.execute {
            scriptDao.deleteData(tableId)
        }
        return true
    }

    override fun nukeRoomScript(): Boolean {
        appExecutors.diskIO.execute {
            scriptDao.nukeData()
        }
        return true
    }

    override fun getRoomScript(callback: FrogoDataSource.GetRoomDataCallBack<List<Script>>) {
        appExecutors.diskIO.execute {
            scriptDao.getAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseCallback<List<Script>>() {
                    override fun onCallbackSucces(data: List<Script>) {
                        callback.onShowProgressDialog()
                        callback.onSuccess(data)
                        callback.onHideProgressDialog()
                    }

                    override fun onCallbackError(code: Int, errorMessage: String) {
                        callback.onFailed(code, errorMessage)
                    }

                    override fun onAddSubscribe(disposable: Disposable) {
                        addSubscribe(disposable = disposable)
                    }

                    override fun onCompleted() {
                        callback.onHideProgressDialog()
                    }
                })
        }
    }


    private
    var compositeDisposable: CompositeDisposable? = null

    fun addSubscribe(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()

            compositeDisposable?.add(disposable)
        }
    }

    /**
     * Clear all subscribers active in app
     */
    private fun clearSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable?.clear()
        }
    }

    companion object {

        private var INSTANCE: FrogoLocalDataSource? = null

        @JvmStatic
        fun getInstance(
            appExecutors: AppExecutors,
            sharedPreferences: SharedPreferences,
            scriptDao: ScriptDao,
            favoriteScriptDao: FavoriteScriptDao,
            videoScriptDao: VideoScriptDao

        ): FrogoLocalDataSource {
            if (INSTANCE == null) {
                synchronized(FrogoLocalDataSource::javaClass) {
                    INSTANCE = FrogoLocalDataSource(
                        appExecutors,
                        sharedPreferences,
                        scriptDao,
                        favoriteScriptDao,
                        videoScriptDao
                    )
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

}
