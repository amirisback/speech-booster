package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.view.fragment.ExampleFragment
import com.frogobox.speechbooster.view.fragment.ScriptFragment
import com.frogobox.speechbooster.view.fragment.VideoFragment
import com.frogobox.speechbooster.viewmodel.CategoryViewModel
import com.frogobox.speechbooster.viewmodel.FavoriteScriptMainViewModel
import com.frogobox.speechbooster.viewmodel.ScriptMainViewModel
import com.frogobox.speechbooster.viewmodel.VideoScriptMainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment(R.id.framelayout_main_container)
        setupBottomNav(R.id.framelayout_main_container)
        setupToolbar()
    }

    private fun setupFragment(frameLayout: Int) {
        setTitle(R.string.title_myscript)
        setupChildFragment(frameLayout, ScriptFragment())
    }

    fun obtainScriptMainViewModel(): ScriptMainViewModel =
        obtainViewModel(ScriptMainViewModel::class.java)

    fun obtainCategoryViewModel(): CategoryViewModel =
        obtainViewModel(CategoryViewModel::class.java)

    fun obtainFavoriteViewModel(): FavoriteScriptMainViewModel =
        obtainViewModel(FavoriteScriptMainViewModel::class.java)

    fun obtainVideoViewModel(): VideoScriptMainViewModel =
        obtainViewModel(VideoScriptMainViewModel::class.java)

    private fun setupToolbar() {
        setSupportActionBar(toolbar_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_menu_about -> {
                baseStartActivity<AboutUsActivity>(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupBottomNav(frameLayout: Int) {
        bottom_nav_main_menu.clearAnimation()
        bottom_nav_main_menu.setOnNavigationItemSelectedListener {

            when (it.itemId) {
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
                    setupChildFragment(frameLayout, ExampleFragment())
                }
            }

            true
        }

    }
}
