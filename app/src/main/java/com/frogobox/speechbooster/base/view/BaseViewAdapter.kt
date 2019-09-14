package com.frogobox.speechbooster.base.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 10/09/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.base
 *
 */
abstract class BaseViewAdapter<T, Holder : BaseViewHolder<T>> : RecyclerView.Adapter<Holder>() {

    protected lateinit var mContext: Context
    protected lateinit var mListener: BaseListener<T>

    protected val mRecyclerViewDataList = mutableListOf<T>()
    protected var mRecyclerViewLayout: Int = 0

    fun setRecyclerViewLayout(context: Context, layoutItem: Int) {
        mContext = context
        mRecyclerViewLayout = layoutItem
    }

    fun setRecyclerViewListener(listener: BaseListener<T>) {
        mListener = listener
    }

    fun setRecyclerViewData(dataList: List<T>) {
        mRecyclerViewDataList.clear()
        mRecyclerViewDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItem(mRecyclerViewDataList[position], mListener)
    }

    override fun getItemCount(): Int = mRecyclerViewDataList.size

}