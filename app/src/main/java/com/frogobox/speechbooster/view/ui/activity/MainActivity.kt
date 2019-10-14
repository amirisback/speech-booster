package com.frogobox.speechbooster.view.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.view.ui.BaseActivity
import com.frogobox.speechbooster.view.ui.fragment.RepositoryFragment
import com.frogobox.speechbooster.view.ui.fragment.ScriptFragment
import com.frogobox.speechbooster.view.ui.fragment.VideoFragment
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
        setupToolbar()
        setupBottomNav(R.id.framelayout_main_container)
        setupFragment(savedInstanceState)
        setupShowAdsInterstitial()
    }

    fun obtainScriptMainViewModel(): ScriptMainViewModel =
        obtainViewModel(ScriptMainViewModel::class.java)

    fun obtainCategoryViewModel(): CategoryViewModel =
        obtainViewModel(CategoryViewModel::class.java)

    fun obtainFavoriteViewModel(): FavoriteScriptMainViewModel =
        obtainViewModel(FavoriteScriptMainViewModel::class.java)

    fun obtainVideoViewModel(): VideoScriptMainViewModel =
        obtainViewModel(VideoScriptMainViewModel::class.java)

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            bottom_nav_main_menu.selectedItemId = R.id.bottom_menu_myscript
        }
    }

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
                baseStartActivity<AboutUsActivity>()
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
                    setupCustomTitleToolbar(R.string.title_myscript)
                    setupChildFragment(frameLayout, ScriptFragment())
                    setupShowAdsInterstitial()
                }

                R.id.bottom_menu_video -> {
                    setupCustomTitleToolbar(R.string.title_video)
                    setupChildFragment(frameLayout, VideoFragment())
                    setupShowAdsInterstitial()
                }

                R.id.bottom_menu_sample_script -> {
                    setupCustomTitleToolbar(R.string.title_repository_script)
                    setupChildFragment(frameLayout, RepositoryFragment())
                    setupShowAdsInterstitial()
                }
            }

            true
        }

    }
}
