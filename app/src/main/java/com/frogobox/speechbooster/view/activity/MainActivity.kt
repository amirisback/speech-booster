package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.view.fragment.ExampleScriptFragment
import com.frogobox.speechbooster.view.fragment.ScriptFragment
import com.frogobox.speechbooster.view.fragment.VideoFragment
import com.frogobox.speechbooster.viewmodel.ScriptMainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment(R.id.framelayout_main_container)
        setupBottomNav(R.id.framelayout_main_container)
    }

    private fun setupFragment(frameLayout: Int) {
        setTitle(R.string.title_myscript)
        setupChildFragment(frameLayout, ScriptFragment())
    }

    fun obtainScriptMainViewModel(): ScriptMainViewModel = obtainViewModel(ScriptMainViewModel::class.java)

    private fun setupBottomNav(frameLayout: Int){
        bottom_nav_main_menu.clearAnimation()
        bottom_nav_main_menu.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.bottom_menu_myscript -> {
                    setTitle(R.string.title_myscript)
                    setupChildFragment(frameLayout, ScriptFragment())
                }

                R.id.bottom_menu_video -> {
                    setTitle(R.string.title_video)
                    setupChildFragment(frameLayout, VideoFragment())
                }

                R.id.bottom_menu_sample_script -> {
                    setTitle(R.string.title_sample_script)
                    setupChildFragment(frameLayout, ExampleScriptFragment())
                }
            }

            true
        }

    }
}
