package com.frogobox.speechbooster.util

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.viewmodel.*

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 26/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.util
 *
 */
class ViewModelFactory private constructor(
    private val mApplication: Application,
    private val frogoDataRepository: FrogoDataRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {

                isAssignableFrom(CategoryScriptViewModel::class.java) ->
                    CategoryScriptViewModel(mApplication, frogoDataRepository)
                isAssignableFrom(CategoryViewModel::class.java) ->
                    CategoryViewModel(mApplication, frogoDataRepository)

                isAssignableFrom(FavoriteScriptDetailViewModel::class.java) ->
                    FavoriteScriptDetailViewModel(mApplication, frogoDataRepository)
                isAssignableFrom(FavoriteScriptMainViewModel::class.java) ->
                    FavoriteScriptMainViewModel(mApplication, frogoDataRepository)

                isAssignableFrom(ScriptEditorViewModel::class.java) ->
                    ScriptEditorViewModel(mApplication, frogoDataRepository)
                isAssignableFrom(ScriptMainViewModel::class.java) ->
                    ScriptMainViewModel(mApplication, frogoDataRepository)
                isAssignableFrom(ScriptDetailViewModel::class.java) ->
                    ScriptDetailViewModel(mApplication, frogoDataRepository)

                isAssignableFrom(VideoScriptDetailViewModel::class.java) ->
                    VideoScriptDetailViewModel(mApplication, frogoDataRepository)
                isAssignableFrom(VideoScriptMainViewModel::class.java) ->
                    VideoScriptMainViewModel(mApplication, frogoDataRepository)
                isAssignableFrom(VideoScriptRecordViewModel::class.java) ->
                    VideoScriptRecordViewModel(mApplication, frogoDataRepository)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(mApplication: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(mApplication,
                    Injection.provideGitsRepository(mApplication.applicationContext))
                    .also { INSTANCE = it }
            }
    }
}