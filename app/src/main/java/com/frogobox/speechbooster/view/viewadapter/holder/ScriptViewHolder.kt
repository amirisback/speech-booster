package com.frogobox.speechbooster.view.viewadapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.frogobox.speechbooster.base.BaseListener
import com.frogobox.speechbooster.base.BaseViewHolder
import com.frogobox.speechbooster.model.Script
import kotlinx.android.synthetic.main.recyclerview_item_script.view.*

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
 * com.frogobox.speechbooster.view.viewadapter.holder
 *
 */
class ScriptViewHolder (view: View): RecyclerView.ViewHolder(view), BaseViewHolder<Script> {

    val tvTitle = view.tv_title
    val tvDescription = view.tv_description
    val tvDateTime = view.tv_date
    val ivFavorite = view.iv_favorite

    override fun bindItem(data: Script, listener: BaseListener<Script>) {
        onItemViewClicked(data, listener)
        tvTitle.text = data.title
        tvDescription.text = data.description
        tvDateTime.text = data.dateTime

        if (data.favorite!!) {
            ivFavorite.visibility = View.VISIBLE
        } else {
            ivFavorite.visibility = View.GONE
        }
    }

    override fun onItemViewClicked(data: Script, listener: BaseListener<Script>) {
        itemView.setOnClickListener {
            listener.onItemClicked(data)
        }
    }

}