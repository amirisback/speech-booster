package com.frogobox.speechbooster.base

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

    protected val mDataList = mutableListOf<T>()
    protected var mLayoutItem: Int = 0

    fun setLayoutItem(context: Context, layoutItem: Int) {
        mContext = context
        mLayoutItem = layoutItem
    }

    fun setListener(listener: BaseListener<T>) {
        mListener = listener
    }

    fun setRecyclerViewData(dataList: List<T>) {
        mDataList.clear()
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItem(mDataList[position], mListener)
    }

    override fun getItemCount(): Int = mDataList.size

}