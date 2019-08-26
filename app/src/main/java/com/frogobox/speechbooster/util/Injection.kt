package com.frogobox.speechbooster.util

import android.content.Context
import android.preference.PreferenceManager
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.source.dao.ScriptDao
import com.frogobox.speechbooster.source.local.FrogoAppDatabase
import com.frogobox.speechbooster.source.local.FrogoLocalDataSource
import com.frogobox.speechbooster.source.remote.FrogoRemoteDataSource

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
object Injection {

    fun provideGitsRepository(context: Context): FrogoDataRepository {
        val scriptDao: ScriptDao by lazy {
            FrogoAppDatabase.getInstance(context).userDao()
        }



        val appExecutors = AppExecutors()

        return FrogoDataRepository.getInstance(FrogoRemoteDataSource(context),
            FrogoLocalDataSource.getInstance(
                appExecutors,
                PreferenceManager.getDefaultSharedPreferences(context),
                scriptDao))
    }

}