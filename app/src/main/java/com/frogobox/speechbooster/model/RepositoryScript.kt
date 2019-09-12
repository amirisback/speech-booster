package com.frogobox.speechbooster.model

import androidx.room.ColumnInfo

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 04/09/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.model
 *
 */
data class RepositoryScript(

    @ColumnInfo(name = "title")
    var title: String? = "",

    @ColumnInfo(name = "description")
    var description: String? = ""

)