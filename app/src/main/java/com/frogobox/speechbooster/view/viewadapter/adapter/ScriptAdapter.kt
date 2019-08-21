package com.frogobox.speechbooster.view.viewadapter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frogobox.speechbooster.base.BaseListener
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
class ScriptAdapter : RecyclerView.Adapter<ScriptViewHolder>(), BaseViewAdapter<Script> {

    private lateinit var mContext: Context
    private lateinit var mListener: BaseListener<Script>

    private val mDataList = mutableListOf<Script>()
    private var mLayoutItem: Int = 0

    override fun setLayoutItem(context: Context, layoutItem: Int) {
        mContext = context
        mLayoutItem = layoutItem
    }

    override fun setListener(listener: BaseListener<Script>) {
        mListener = listener
    }

    override fun setRecyclerViewData(dataList: List<Script>) {
        mDataList.clear()
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ScriptViewHolder(LayoutInflater.from(mContext).inflate(mLayoutItem, parent, false))

    override fun onBindViewHolder(holder: ScriptViewHolder, position: Int) {
        holder.bindItem(mDataList[position], mListener)
    }

    override fun getItemCount(): Int = mDataList.size

}

