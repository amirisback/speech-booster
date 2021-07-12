package com.frogobox.speechbooster.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseViewAdapter
import com.frogobox.speechbooster.core.BaseViewHolder
import com.frogobox.speechbooster.model.CategoryScript

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

        val ivCategory = view.findViewById<ImageView>(R.id.iv_category)
        val tvCategory = view.findViewById<TextView>(R.id.tv_category)

        override fun initComponent(data: CategoryScript) {
            super.initComponent(data)
            ivCategory.setImageResource(data.imageCategory)
            tvCategory.text = data.category
        }

    }

}