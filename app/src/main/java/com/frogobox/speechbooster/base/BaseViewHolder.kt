package com.frogobox.speechbooster.base

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
interface BaseViewHolder<T> {
    fun bindItem(data: T, listener: BaseListener<T>)
    fun onItemViewClicked(data: T, listener: BaseListener<T>)
}