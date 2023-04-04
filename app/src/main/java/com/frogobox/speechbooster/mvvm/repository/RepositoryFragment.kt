package com.frogobox.speechbooster.mvvm.repository


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseFragment
import com.frogobox.speechbooster.databinding.FragmentRepositoryBinding
import com.frogobox.speechbooster.util.PagerHelper

class RepositoryFragment : BaseFragment<FragmentRepositoryBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRepositoryBinding {
        return FragmentRepositoryBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {}

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
        super.onViewCreatedExt(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager(){
        val pagerAdapter = PagerHelper(childFragmentManager)
        pagerAdapter.setupPagerFragment(RepositoryCategoryFragment(), resources.getString(R.string.title_favorite_category))
        pagerAdapter.setupPagerFragment(RepositoryFavoriteFragment(), resources.getString(R.string.title_favorite_script))
        binding.apply {
            viewpager.adapter = pagerAdapter
            tablayout.setupWithViewPager(viewpager)
        }
    }

}
