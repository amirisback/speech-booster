package com.frogobox.speechbooster

import com.frogobox.sdk.FrogoApplication
import com.frogobox.speechbooster.di.repositoryModule
import com.frogobox.speechbooster.di.viewModelModule
import org.koin.core.KoinApplication

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
class SbApplication : FrogoApplication() {

    override fun setupKoinModule(koinApplication: KoinApplication) {
        koinApplication.modules(listOf(repositoryModule, viewModelModule))
    }

}