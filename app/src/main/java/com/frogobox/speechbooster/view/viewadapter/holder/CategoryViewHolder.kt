package com.frogobox.speechbooster.view.viewadapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.frogobox.speechbooster.base.BaseListener
import com.frogobox.speechbooster.base.BaseViewHolder
import com.frogobox.speechbooster.model.CategoryScript
import kotlinx.android.synthetic.main.recyclerview_item_category.view.*

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

class CategoryViewHolder(view: View) : BaseViewHolder<CategoryScript>(view) {

    val ivCategory = view.iv_category
    val tvCategory = view.tv_category

    override fun initComponent(data: CategoryScript) {
        super.initComponent(data)
        ivCategory.setImageResource(data.imageCategory)
        tvCategory.text = data.category
    }

}