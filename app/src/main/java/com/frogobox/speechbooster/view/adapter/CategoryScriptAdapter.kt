package com.frogobox.speechbooster.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.speechbooster.base.view.BaseViewAdapter
import com.frogobox.speechbooster.base.view.BaseViewHolder
import com.frogobox.speechbooster.model.RepositoryScript
import kotlinx.android.synthetic.main.recyclerview_item_category_script.view.*

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
class CategoryScriptAdapter :
    BaseViewAdapter<RepositoryScript, CategoryScriptAdapter.CategoryScriptViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryScriptViewHolder(
            LayoutInflater.from(mContext).inflate(
                mRecyclerViewLayout,
                parent,
                false
            )
        )

    inner class CategoryScriptViewHolder(view: View) : BaseViewHolder<RepositoryScript>(view) {

        val tvTitleExample = view.tv_title_example_script
        val tvDescExample = view.tv_description_example_script

        override fun initComponent(data: RepositoryScript) {
            super.initComponent(data)
            tvTitleExample.text = data.title
            tvDescExample.text = data.description
        }

    }

}