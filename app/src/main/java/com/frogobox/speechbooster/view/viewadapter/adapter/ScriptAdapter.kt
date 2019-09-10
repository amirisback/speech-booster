package com.frogobox.speechbooster.view.viewadapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.frogobox.speechbooster.base.BaseViewAdapter
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.view.viewadapter.holder.ScriptViewHolder

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 18/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.view.adapter
 *
 */
class ScriptAdapter : BaseViewAdapter<Script, ScriptViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ScriptViewHolder(LayoutInflater.from(mContext).inflate(mRecyclerViewLayout, parent, false))

}

