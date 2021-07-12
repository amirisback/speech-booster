package com.frogobox.speechbooster.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseViewAdapter
import com.frogobox.speechbooster.core.BaseViewHolder
import com.frogobox.speechbooster.model.Script

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
class ScriptAdapter : BaseViewAdapter<Script, ScriptAdapter.ScriptViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ScriptViewHolder(LayoutInflater.from(mContext).inflate(mRecyclerViewLayout, parent, false))

    inner class ScriptViewHolder(view: View) : BaseViewHolder<Script>(view) {

        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvDescription = view.findViewById<TextView>(R.id.tv_description)
        val tvDateTime = view.findViewById<TextView>(R.id.tv_date)
        val ivFavorite = view.findViewById<ImageView>(R.id.iv_favorite)

        override fun initComponent(data: Script) {
            super.initComponent(data)

            tvTitle.text = data.title
            tvDescription.text = data.description
            tvDateTime.text = data.dateTime

            if (data.favorite!!) {
                ivFavorite.visibility = View.VISIBLE
            } else {
                ivFavorite.visibility = View.GONE
            }

        }

    }

}

