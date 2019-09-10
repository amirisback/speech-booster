package com.frogobox.speechbooster.view.viewadapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.frogobox.speechbooster.base.BaseViewAdapter
import com.frogobox.speechbooster.model.CategoryScript
import com.frogobox.speechbooster.view.viewadapter.holder.CategoryViewHolder

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
class CategoryAdapter : BaseViewAdapter<CategoryScript, CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(LayoutInflater.from(mContext).inflate(mRecyclerViewLayout, parent, false))

}