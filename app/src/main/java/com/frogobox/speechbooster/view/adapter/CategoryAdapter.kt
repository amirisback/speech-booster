package com.frogobox.speechbooster.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.speechbooster.base.view.BaseViewAdapter
import com.frogobox.speechbooster.base.view.BaseViewHolder
import com.frogobox.speechbooster.model.CategoryScript
import kotlinx.android.synthetic.main.recyclerview_item_category.view.*

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 09/09/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.view.viewadapter.adapter
 *
 */
class CategoryAdapter : BaseViewAdapter<CategoryScript, CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(
            LayoutInflater.from(mContext).inflate(
                mRecyclerViewLayout,
                parent,
                false
            )
        )

    inner class CategoryViewHolder(view: View) : BaseViewHolder<CategoryScript>(view) {

        val ivCategory = view.iv_category
        val tvCategory = view.tv_category

        override fun initComponent(data: CategoryScript) {
            super.initComponent(data)
            ivCategory.setImageResource(data.imageCategory)
            tvCategory.text = data.category
        }

    }

}