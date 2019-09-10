package com.frogobox.speechbooster.view.viewadapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.frogobox.speechbooster.base.BaseListener
import com.frogobox.speechbooster.base.BaseViewHolder
import com.frogobox.speechbooster.model.ExampleScript
import kotlinx.android.synthetic.main.recyclerview_item_category_script.view.*

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

class CategoryScriptViewHolder(view: View) : BaseViewHolder<ExampleScript>(view) {

    val tvTitleExample = view.tv_title_example_script
    val tvDescExample = view.tv_description_example_script

    override fun initComponent(data: ExampleScript) {
        super.initComponent(data)
        tvTitleExample.text = data.title
        tvDescExample.text = data.description
    }

}