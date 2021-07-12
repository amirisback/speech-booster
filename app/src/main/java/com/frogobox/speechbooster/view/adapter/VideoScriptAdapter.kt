package com.frogobox.speechbooster.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.speechbooster.core.BaseViewAdapter
import com.frogobox.speechbooster.core.BaseViewHolder
import com.frogobox.speechbooster.model.VideoScript

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
class VideoScriptAdapter : BaseViewAdapter<VideoScript, VideoScriptAdapter.VideoScriptViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoScriptViewHolder {
        return VideoScriptViewHolder(LayoutInflater.from(mContext).inflate(mRecyclerViewLayout, parent, false))
    }

    inner class VideoScriptViewHolder(view: View) : BaseViewHolder<VideoScript>(view) {

        override fun initComponent(data: VideoScript) {
            super.initComponent(data)
        }

    }

}