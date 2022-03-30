package com.frogobox.speechbooster.source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.frogobox.speechbooster.source.model.FavoriteScript
import io.reactivex.rxjava3.core.Single

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
 * com.frogobox.speechbooster.source.dao
 *
 */
@Dao
interface FavoriteScriptDao {

    @Query("SELECT * FROM favorite_script_table")
    fun getAllData(): Single<List<FavoriteScript>>

    @Insert
    fun insertData(data: FavoriteScript)

    @Query("UPDATE favorite_script_table SET title = :title, description = :description, dateTime = :dateTime WHERE table_id = :tableid")
    fun updateData(tableid: Int, title: String, description: String, dateTime: String)

    @Query("DELETE FROM favorite_script_table WHERE table_id = :tableId")
    fun deleteDataFromTableId(tableId: Int)

    @Query("DELETE FROM favorite_script_table WHERE script_id = :scriptId")
    fun deleteDataFromScriptId(scriptId: String)

    @Query("SELECT * FROM favorite_script_table WHERE script_id = :scriptId")
    fun searchData(scriptId: String): Single<List<FavoriteScript>>

    @Query("DELETE FROM favorite_script_table")
    fun nukeData()

}