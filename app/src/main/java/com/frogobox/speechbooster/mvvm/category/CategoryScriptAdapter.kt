package com.frogobox.speechbooster.mvvm.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseViewAdapter
import com.frogobox.speechbooster.core.BaseViewHolder
import com.frogobox.speechbooster.source.model.RepositoryScript

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

        val tvTitleExample = view.findViewById<TextView>(R.id.tv_title_example_script)
        val tvDescExample = view.findViewById<TextView>(R.id.tv_description_example_script)

        override fun initComponent(data: RepositoryScript) {
            super.initComponent(data)
            tvTitleExample.text = data.title
            tvDescExample.text = data.description
        }

    }

}