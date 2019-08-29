package com.frogobox.speechbooster.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.frogobox.speechbooster.helper.FunHelper
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.helper.ConstHelper
import com.frogobox.speechbooster.navigation.Navigation
import com.frogobox.speechbooster.util.ViewModelFactory


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

    protected fun setupBaseFragment(frameId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            commit()
        }
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

    protected fun setupDetailActivity(title: String) {
        setTitle(title)
        val upArrow = ContextCompat.getDrawable(this, R.drawable.ic_toolbar_back_home)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorBaseWhite)))
    }

    protected fun optionToolbar() : Int {

        val optionEdit = Navigation.getBundle.baseGetBundle<Int>(
            ConstHelper.TypeData.TYPE_INT,
            this,
            ConstHelper.Extra.OPTION_ACTIVITY_EDIT
        )
        val optionCreate = Navigation.getBundle.baseGetBundle<Int>(
            ConstHelper.TypeData.TYPE_INT,
            this,
            ConstHelper.Extra.OPTION_ACTIVITY_CREATE
        )
        var option = 0

        if (optionEdit != 0) {
            option = optionEdit
        } else if (optionCreate != 0) {
            option = optionCreate
        }

        return option
    }

    fun <T : ViewModel> obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

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