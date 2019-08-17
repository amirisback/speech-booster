package com.frogobox.speechbooster.source

import com.frogobox.speechbooster.source.local.LocalDataSource
import com.frogobox.speechbooster.source.remote.RemoteDataSource

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
class DataRepository(val remoteDataSource: RemoteDataSource, val localDataSource: LocalDataSource) : DataSource {

}