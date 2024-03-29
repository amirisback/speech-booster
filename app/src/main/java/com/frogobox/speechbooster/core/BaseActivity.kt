package com.frogobox.speechbooster.core

import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.view.*
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.frogobox.admob.ui.FrogoAdmobBindActivity
import com.frogobox.sdk.ext.newInstanceExt
import com.frogobox.sdk.ext.startActivityExt
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.util.ConstHelper
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getOptionBundle


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
abstract class BaseActivity<VB : ViewBinding> : FrogoAdmobBindActivity<VB>() {

    protected fun setupNoLimitStatBar() {
        val windows = window // in Activity's onCreate() for instance
        windows.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    protected inline fun <reified ClassActivity> setupSplashScreen() {
        Handler().postDelayed({
            startActivityExt<ClassActivity>()
            this@BaseActivity.finish()
        }, ConstHelper.Const.SPLASH_INTERVAL.toLong())
    }

    protected fun setupChildFragment(frameId: Int, fragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            commit()
        }
    }

    protected fun <Model, VB : ViewBinding> baseFragmentNewInstance(
        fragment: BaseFragment<VB>,
        argumentKey: String,
        extraDataResult: Model
    ) {
        fragment.newInstanceExt(argumentKey, extraDataResult)
    }

    protected fun tagOption(): Int {
        return getOptionBundle(this)
    }

    override fun setupDetailActivity(title: String) {
        setTitle(title)
        val upArrow = ContextCompat.getDrawable(this, R.drawable.ic_toolbar_back_home)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.colorBaseWhite
                )
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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