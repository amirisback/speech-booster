package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.view.fragment.ScriptFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment(R.id.frameLayout, ScriptFragment())
    }




}
