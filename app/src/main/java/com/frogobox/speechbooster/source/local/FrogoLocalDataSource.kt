package com.frogobox.speechbooster.source.local

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import com.frogobox.speechbooster.base.BaseCallback
import com.frogobox.speechbooster.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.source.FrogoDataSource
import com.frogobox.speechbooster.source.dao.ScriptDao
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
    private val scriptDao: ScriptDao) : FrogoDataSource {

    override fun saveRoomScript(data: Script) {
        appExecutors.diskIO.execute {
            scriptDao.insertData(data)
        }
    }

    override fun updateRoomScript(data: Script, param: String) {
        noAction()
    }

    override fun deleteRoomScript(param: String) {
        noAction()
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
            scriptDao: ScriptDao

        ): FrogoLocalDataSource {
            if (INSTANCE == null) {
                synchronized(FrogoLocalDataSource::javaClass) {
                    INSTANCE = FrogoLocalDataSource(
                        appExecutors,
                        sharedPreferences,
                        scriptDao)
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
