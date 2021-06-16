package com.frogobox.speechbooster.view.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import com.frogobox.speechbooster.base.view.ui.BaseActivity
import com.frogobox.speechbooster.databinding.ActivityAboutUsBinding

class AboutUsActivity : BaseActivity<ActivityAboutUsBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setupDetailActivity("")
    }

}
