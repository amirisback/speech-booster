package com.frogobox.speechbooster.di

import androidx.preference.PreferenceManager
import com.frogobox.sdk.util.AppExecutors
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.source.local.FrogoAppDatabase
import com.frogobox.speechbooster.source.local.FrogoLocalDataSource
import com.frogobox.speechbooster.source.remote.FrogoRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
 * Created by faisalamir on 03/08/21
 * speech-booster
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */


val repositoryModule = module {

    single {
        FrogoAppDatabase.getInstance(androidContext()).scriptDao()
    }

    single {
        FrogoAppDatabase.getInstance(androidContext()).videoScriptDao()
    }

    single {
        FrogoAppDatabase.getInstance(androidContext()).favoriteScriptDao()
    }

    single {
        FrogoDataRepository(FrogoRemoteDataSource(androidContext()), get())
    }

    single {
        FrogoLocalDataSource.getInstance(AppExecutors(), get(), get(), get(), get())
    }

    single {
        PreferenceManager.getDefaultSharedPreferences(androidContext())
    }

}