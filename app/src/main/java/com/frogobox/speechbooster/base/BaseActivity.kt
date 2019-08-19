package com.frogobox.speechbooster.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.speechbooster.helper.FunHelper

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PublicSpeakingBooster
 * Copyright (C) 16/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.publicspeakingbooster.base
 *
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var mActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this

    }

    protected inline fun <reified ClassActivity> baseStartActivity(context: Context) {
        context.startActivity(Intent(context, ClassActivity::class.java))
    }

    protected inline fun <reified ClassActivity, Model> baseStartActivity(context: Context, extraKey: String, data: Model) {
        val intent = Intent(context, ClassActivity::class.java)
        val extraData = FunHelper.ConverterJson.toJson(data)
        intent.putExtra(extraKey, extraData)
        context.startActivity(intent)
    }

    protected inline fun <reified Model> baseGetExtraData(extraKey: String) : Model {
        val extraIntent = intent.getStringExtra(extraKey)
        val extraData = FunHelper.ConverterJson.fromJson<Model>(extraIntent)
        return extraData
    }

    protected fun setupDetail(title: String) {
        setTitle(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}