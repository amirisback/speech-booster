package com.frogobox.speechbooster.view.viewadapter

import android.view.View
import android.view.ViewGroup
import com.frogobox.speechbooster.base.BaseViewAdapter
import com.frogobox.speechbooster.base.BaseViewHolder
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class VideoScriptViewHolder(view: View) : BaseViewHolder<VideoScript>(view) {

        override fun initComponent(data: VideoScript) {
            super.initComponent(data)
        }

    }

}