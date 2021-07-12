package com.frogobox.speechbooster.mvvm.main

import android.os.Bundle
import com.frogobox.speechbooster.core.BaseActivity
import com.frogobox.speechbooster.databinding.ActivitySplashScreenBinding
import com.frogobox.speechbooster.mvvm.main.MainActivity

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override fun setupViewBinding(): ActivitySplashScreenBinding {
        return ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {}

    override fun setupUI(savedInstanceState: Bundle?) {
        setupFullScreen()
        setupSplashScreen<MainActivity>()
    }

}
