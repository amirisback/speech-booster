package com.frogobox.speechbooster.view.viewadapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.frogobox.speechbooster.base.BaseListener
import com.frogobox.speechbooster.base.BaseViewHolder
import com.frogobox.speechbooster.model.ExampleScript
import kotlinx.android.synthetic.main.recyclerview_item_category.view.*
import kotlinx.android.synthetic.main.recyclerview_item_example_script.view.*

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
class ExampleScriptViewHolder (view: View) : RecyclerView.ViewHolder(view),
    BaseViewHolder<ExampleScript> {

    val tvTitleExample = view.tv_title_example_script
    val tvDescExample = view.tv_description_example_script

    override fun bindItem(data: ExampleScript, listener: BaseListener<ExampleScript>) {
        onItemViewClicked(data, listener)
        tvTitleExample.text = data.title
        tvDescExample.text = data.description
    }

    override fun onItemViewClicked(data: ExampleScript, listener: BaseListener<ExampleScript>) {
        itemView.setOnClickListener {
            listener.onItemClicked(data)
        }
    }


}