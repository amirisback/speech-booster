package com.frogobox.speechbooster.source.local

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import com.frogobox.coresdk.FrogoLocalObserver
import com.frogobox.sdk.util.AppExecutors
import com.frogobox.speechbooster.source.FrogoDataSource
import com.frogobox.speechbooster.source.dao.FavoriteScriptDao
import com.frogobox.speechbooster.source.dao.ScriptDao
import com.frogobox.speechbooster.source.dao.VideoScriptDao
import com.frogobox.speechbooster.source.model.FavoriteScript
import com.frogobox.speechbooster.source.model.Script
import com.frogobox.speechbooster.source.model.VideoScript
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

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

    override fun searchRoomFavorite(
        scriptId: String,
        callback: FrogoDataSource.GetRoomDataCallBack<List<FavoriteScript>>
    ) {
        appExecutors.diskIO.execute {
            favoriteScriptDao.searchData(scriptId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : FrogoLocalObserver<List<FavoriteScript>>() {
                    override fun onCallbackSucces(data: List<FavoriteScript>) {
                        callback.onShowProgressDialog()
                        callback.onSuccess(data)
                        if (data.size == 0) {
                            callback.onEmpty()
                        }
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

    override fun saveRoomScript(data: Script): Boolean {
        appExecutors.diskIO.execute {
            scriptDao.insertData(data)
        }
        return true
    }

    override fun getRoomFavoriteScript(callback: FrogoDataSource.GetRoomDataCallBack<List<FavoriteScript>>) {
        appExecutors.diskIO.execute {
            favoriteScriptDao.getAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : FrogoLocalObserver<List<FavoriteScript>>() {
                    override fun onCallbackSucces(data: List<FavoriteScript>) {
                        callback.onShowProgressDialog()
                        callback.onSuccess(data)
                        if (data.size == 0) {
                            callback.onEmpty()
                        }
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
                .subscribe(object : FrogoLocalObserver<List<VideoScript>>() {
                    override fun onCallbackSucces(data: List<VideoScript>) {
                        callback.onShowProgressDialog()
                        callback.onSuccess(data)
                        if (data.size == 0) {
                            callback.onEmpty()
                        }
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

    override fun getRoomScript(callback: FrogoDataSource.GetRoomDataCallBack<List<Script>>) {
        appExecutors.diskIO.execute {
            scriptDao.getAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : FrogoLocalObserver<List<Script>>() {
                    override fun onCallbackSucces(data: List<Script>) {
                        callback.onShowProgressDialog()
                        callback.onSuccess(data)
                        if (data.size == 0) {
                            callback.onEmpty()
                        }
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

    override fun updateRoomScriptFav(tableId: Int, favorite: Boolean): Boolean {
        appExecutors.diskIO.execute {
            scriptDao.updateFavorite(tableId, favorite)
        }
        return true
    }

    override fun deleteRoomFavoriteScript(tableId: Int): Boolean {
        appExecutors.diskIO.execute {
            favoriteScriptDao.deleteDataFromTableId(tableId)
        }
        return true
    }

    override fun deleteRoomFavoriteScriptId(scriptId: String): Boolean {
        appExecutors.diskIO.execute {
            favoriteScriptDao.deleteDataFromScriptId(scriptId)
        }
        return true
    }

    override fun deleteRoomVideoScript(tableId: Int): Boolean {
        appExecutors.diskIO.execute {
            videoScriptDao.deleteData(tableId)
        }
        return true
    }

    override fun deleteRoomScript(tableId: Int): Boolean {
        appExecutors.diskIO.execute {
            scriptDao.deleteData(tableId)
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

    override fun nukeRoomScript(): Boolean {
        appExecutors.diskIO.execute {
            scriptDao.nukeData()
        }
        return true
    }

    private var compositeDisposable: CompositeDisposable? = null

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
