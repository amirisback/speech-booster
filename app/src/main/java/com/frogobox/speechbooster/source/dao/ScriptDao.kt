package com.frogobox.speechbooster.source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.frogobox.speechbooster.model.Script
import io.reactivex.Single
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
 * com.frogobox.speechbooster.source.dao
 *
 */
@Dao
interface ScriptDao {

    @Query("SELECT * FROM script_table")
    fun getAllData(): Single<List<Script>>

    @Insert
    fun insertData(data: Script)

    @Query("UPDATE script_table SET title = :title, description = :description, dateTime = :dateTime WHERE table_id = :tableid")
    fun updateData(tableid: Int, title: String, description: String, dateTime: String)

    @Query("DELETE FROM script_table WHERE table_id = :tableid")
    fun deleteData(tableid: Int)

    @Query("DELETE FROM script_table")
    fun nukeData()

}