package com.frogobox.speechbooster.core

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.frogobox.sdk.view.FrogoFragment
import com.google.android.gms.ads.AdView

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
abstract class BaseFragment<VB : ViewBinding> : FrogoFragment<VB>() {

    protected lateinit var mActivity: BaseActivity<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = (activity as BaseActivity<*>)
    }

    protected fun setupShowAdsBanner(mAdView: AdView) {
        mActivity.showAdBanner(mAdView)
    }


}