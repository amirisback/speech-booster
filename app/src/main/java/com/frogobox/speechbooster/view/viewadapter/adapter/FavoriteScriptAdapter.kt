package com.frogobox.speechbooster.view.viewadapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.frogobox.speechbooster.base.BaseViewAdapter
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.view.viewadapter.holder.FavoriteScriptViewHolder

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 04/09/2019.
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
class FavoriteScriptAdapter : BaseViewAdapter<FavoriteScript, FavoriteScriptViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteScriptViewHolder(LayoutInflater.from(mContext).inflate(mRecyclerViewLayout, parent, false))

}
    