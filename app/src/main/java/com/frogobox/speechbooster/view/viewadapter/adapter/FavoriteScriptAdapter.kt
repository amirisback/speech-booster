package com.frogobox.speechbooster.view.viewadapter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frogobox.speechbooster.base.BaseListener
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
class FavoriteScriptAdapter : RecyclerView.Adapter<FavoriteScriptViewHolder>(),
    BaseViewAdapter<FavoriteScript> {

    private lateinit var mContext: Context
    private lateinit var mListener: BaseListener<FavoriteScript>

    private val mDataList = mutableListOf<FavoriteScript>()
    private var mLayoutItem: Int = 0

    override fun setLayoutItem(context: Context, layoutItem: Int) {
        mContext = context
        mLayoutItem = layoutItem
    }

    override fun setListener(listener: BaseListener<FavoriteScript>) {
        mListener = listener
    }

    override fun setRecyclerViewData(dataList: List<FavoriteScript>) {
        mDataList.clear()
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteScriptViewHolder(LayoutInflater.from(mContext).inflate(mLayoutItem, parent, false))

    override fun onBindViewHolder(holder: FavoriteScriptViewHolder, position: Int) {
        holder.bindItem(mDataList[position], mListener)
    }

    override fun getItemCount(): Int = mDataList.size
}
    