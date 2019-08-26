package com.frogobox.speechbooster.source.dao

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Query("SELECT * FROM script")
    fun getAllData(): LiveData<List<Script>>

//    @Update
//    fun updateData(data: ScriptDao)

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun inserAllData(assets : List<Script>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: Script)

    @Query("DELETE FROM script")
    fun deleteAllData()

}