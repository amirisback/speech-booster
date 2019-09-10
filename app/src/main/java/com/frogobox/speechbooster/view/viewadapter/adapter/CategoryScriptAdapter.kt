package com.frogobox.speechbooster.view.viewadapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.frogobox.speechbooster.base.BaseViewAdapter
import com.frogobox.speechbooster.model.ExampleScript
import com.frogobox.speechbooster.view.viewadapter.holder.CategoryScriptViewHolder

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
class CategoryScriptAdapter : BaseViewAdapter<ExampleScript, CategoryScriptViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryScriptViewHolder(LayoutInflater.from(mContext).inflate(mRecyclerViewLayout, parent, false))

}