package com.frogobox.speechbooster.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseFragment
import com.frogobox.speechbooster.helper.PagerHelper
import kotlinx.android.synthetic.main.fragment_example.*

class ExampleFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_example, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = PagerHelper(childFragmentManager)
        pagerAdapter.setupPagerFragment(ExampleCategoryFragment(), resources.getString(R.string.title_favorite_category))
        pagerAdapter.setupPagerFragment(ExampleFavoriteFragment(), resources.getString(R.string.title_favorite_script))
        viewpager.adapter = pagerAdapter
        tablayout.setupWithViewPager(viewpager)

    }
}
