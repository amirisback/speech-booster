package com.frogobox.speechbooster.view.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import com.frogobox.speechbooster.base.view.ui.BaseActivity
import com.frogobox.speechbooster.databinding.ActivityVideoDetailBinding

class VideoDetailActivity : BaseActivity<ActivityVideoDetailBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }
}
