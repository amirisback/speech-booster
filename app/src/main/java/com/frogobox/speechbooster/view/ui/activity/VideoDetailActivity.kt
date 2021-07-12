package com.frogobox.speechbooster.view.ui.activity

import android.os.Bundle
import com.frogobox.speechbooster.core.BaseActivity
import com.frogobox.speechbooster.databinding.ActivityVideoDetailBinding

class VideoDetailActivity : BaseActivity<ActivityVideoDetailBinding>() {

    override fun setupViewBinding(): ActivityVideoDetailBinding {
        return ActivityVideoDetailBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {}

    override fun setupUI(savedInstanceState: Bundle?) {}
}
