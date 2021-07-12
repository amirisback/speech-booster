package com.frogobox.speechbooster.view.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseActivity
import com.frogobox.speechbooster.databinding.ActivityMainBinding
import com.frogobox.speechbooster.view.ui.fragment.RepositoryFragment
import com.frogobox.speechbooster.view.ui.fragment.ScriptFragment
import com.frogobox.speechbooster.view.ui.fragment.VideoFragment
import com.frogobox.speechbooster.viewmodel.CategoryViewModel
import com.frogobox.speechbooster.viewmodel.FavoriteScriptMainViewModel
import com.frogobox.speechbooster.viewmodel.ScriptMainViewModel
import com.frogobox.speechbooster.viewmodel.VideoScriptMainViewModel


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {}

    override fun setupUI(savedInstanceState: Bundle?) {
        setupToolbar()
        setupBottomNav(R.id.framelayout_main_container)
        setupFragment(savedInstanceState)
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
            binding.bottomNavMainMenu.selectedItemId = R.id.bottom_menu_myscript
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbarMain)
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
        binding.bottomNavMainMenu.apply {

            clearAnimation()
            setOnNavigationItemSelectedListener {

                when (it.itemId) {
                    R.id.bottom_menu_myscript -> {
                        setupCustomTitleToolbar(R.string.title_myscript)
                        setupChildFragment(frameLayout, ScriptFragment())
                    }

                    R.id.bottom_menu_video -> {
                        setupCustomTitleToolbar(R.string.title_video)
                        setupChildFragment(frameLayout, VideoFragment())
                    }

                    R.id.bottom_menu_sample_script -> {
                        setupCustomTitleToolbar(R.string.title_repository_script)
                        setupChildFragment(frameLayout, RepositoryFragment())
                    }
                }

                true
            }

        }
    }

}
