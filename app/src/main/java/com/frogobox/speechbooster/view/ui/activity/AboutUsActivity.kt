package com.frogobox.speechbooster.view.ui.activity

import android.os.Bundle
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.view.ui.BaseActivity

class AboutUsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        setupDetailActivity("")
        setupShowAdsInterstitial()
    }

}
