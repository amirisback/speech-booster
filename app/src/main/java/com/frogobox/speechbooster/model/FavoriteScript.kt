package com.frogobox.speechbooster.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
@Entity(tableName = "favorite_script_table")
data class FavoriteScript(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "table_id")
    var table_id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = "",

    @ColumnInfo(name = "description")
    var description: String? = "",

    @ColumnInfo(name = "dateTime")
    var dateTime: String? = ""

)