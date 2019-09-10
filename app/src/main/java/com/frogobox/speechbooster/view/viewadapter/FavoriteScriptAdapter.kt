package com.frogobox.speechbooster.view.viewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.speechbooster.base.BaseViewAdapter
import com.frogobox.speechbooster.base.BaseViewHolder
import com.frogobox.speechbooster.model.FavoriteScript
import kotlinx.android.synthetic.main.recyclerview_item_script.view.*

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
class FavoriteScriptAdapter :
    BaseViewAdapter<FavoriteScript, FavoriteScriptAdapter.FavoriteScriptViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteScriptViewHolder(
            LayoutInflater.from(mContext).inflate(
                mRecyclerViewLayout,
                parent,
                false
            )
        )

    inner class FavoriteScriptViewHolder(view: View) : BaseViewHolder<FavoriteScript>(view) {

        val tvTitle = view.tv_title
        val tvDescription = view.tv_description
        val tvDateTime = view.tv_date

        override fun initComponent(data: FavoriteScript) {
            super.initComponent(data)
            tvTitle.text = data.title
            tvDescription.text = data.description
            tvDateTime.text = data.dateTime
        }

    }

}
    