package com.frogobox.speechbooster.base

import android.content.Context

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 21/08/2019.
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
interface BaseViewAdapter<T> {
    fun setLayoutItem(context: Context, layoutItem: Int)
    fun setListener(listener: BaseListener<T>)
    fun setRecyclerViewData(dataList: List<T>)
}
