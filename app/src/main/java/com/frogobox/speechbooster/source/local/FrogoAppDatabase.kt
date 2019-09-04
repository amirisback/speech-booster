package com.frogobox.speechbooster.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.frogobox.speechbooster.BuildConfig
import com.frogobox.speechbooster.helper.ConstHelper.RoomDatabase.DATABASE_NAME
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.model.VideoScript
import com.frogobox.speechbooster.source.dao.FavoriteScriptDao
import com.frogobox.speechbooster.source.dao.ScriptDao
import com.frogobox.speechbooster.source.dao.VideoScriptDao

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
 * com.frogobox.speechbooster.source.local
 *
 */
@Database(entities = [
    (Script::class),
    (FavoriteScript::class),
    (VideoScript::class)
], version = 1)


abstract class FrogoAppDatabase : RoomDatabase() {

    abstract fun scriptDao(): ScriptDao
    abstract fun favoriteScriptDao(): FavoriteScriptDao
    abstract fun videoScriptDao(): VideoScriptDao

    companion object {

        @Volatile
        private var INSTANCE: FrogoAppDatabase? = null

        fun getInstance(context: Context): FrogoAppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context): FrogoAppDatabase {
            return if (BuildConfig.DEBUG) {
                Room.databaseBuilder(context.applicationContext,
                    FrogoAppDatabase::class.java, DATABASE_NAME)
                    .addMigrations(MIGRATION_2_3)
                    .fallbackToDestructiveMigration() // FOR DEVELOPMENT ONLY !!!!
                    .build()
            } else {
                Room.databaseBuilder(context.applicationContext,
                    FrogoAppDatabase::class.java, DATABASE_NAME)
                    .addMigrations(MIGRATION_2_3)
                    .build()
            }
        }

        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}