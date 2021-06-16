package com.frogobox.speechbooster.view.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.view.ui.BaseFragment
import com.frogobox.speechbooster.databinding.FragmentRepositoryBinding
import com.frogobox.speechbooster.util.helper.PagerHelper

class RepositoryFragment : BaseFragment<FragmentRepositoryBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager(){
        val pagerAdapter = PagerHelper(childFragmentManager)
        pagerAdapter.setupPagerFragment(RepositoryCategoryFragment(), resources.getString(R.string.title_favorite_category))
        pagerAdapter.setupPagerFragment(RepositoryFavoriteFragment(), resources.getString(R.string.title_favorite_script))
        binding?.apply {
            viewpager.adapter = pagerAdapter
            tablayout.setupWithViewPager(viewpager)
        }
    }
}
