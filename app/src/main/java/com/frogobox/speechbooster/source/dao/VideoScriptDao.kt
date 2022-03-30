package com.frogobox.speechbooster.source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.frogobox.speechbooster.source.model.VideoScript
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
interface VideoScriptDao {

    @Query("SELECT * FROM video_script_table")
    fun getAllData(): Single<List<VideoScript>>

    @Insert
    fun insertData(data: VideoScript)

    @Query("UPDATE video_script_table SET urlVideo = :urlVideo WHERE table_id = :tableid")
    fun updateData(tableid: Int, urlVideo: String)

    @Query("DELETE FROM video_script_table WHERE table_id = :tableid")
    fun deleteData(tableid: Int)

    @Query("DELETE FROM video_script_table")
    fun nukeData()

}