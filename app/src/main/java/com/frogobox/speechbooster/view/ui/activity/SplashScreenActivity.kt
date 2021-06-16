package com.frogobox.speechbooster.view.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import com.frogobox.speechbooster.base.view.ui.BaseActivity
import com.frogobox.speechbooster.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(LayoutInflater.from(this))
        setupFullScreen()
        setContentView(binding.root)
        setupSplashScreen<MainActivity>()
    }

}
