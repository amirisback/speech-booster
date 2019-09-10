package com.frogobox.speechbooster.viewmodel

import android.app.Application
import android.content.Context
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseViewModel
import com.frogobox.speechbooster.model.CategoryScript
import com.frogobox.speechbooster.source.FrogoDataRepository

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
 * com.frogobox.speechbooster.viewmodel
 *
 */
class CategoryViewModel (
    application: Application,
    private val frogoDataRepository: FrogoDataRepository
) : BaseViewModel(application) {

    fun showExampleData(context: Context) : MutableList<CategoryScript> {
        val categoryList = mutableListOf<CategoryScript>()
        categoryList.clear()
        categoryList.add(CategoryScript(context.getString(R.string.title_category_religion), R.drawable.ic_toolbar_favorite))
        categoryList.add(CategoryScript(context.getString(R.string.title_category_education), R.drawable.ic_toolbar_favorite))
        categoryList.add(CategoryScript(context.getString(R.string.title_category_health), R.drawable.ic_toolbar_favorite))
        categoryList.add(CategoryScript(context.getString(R.string.title_category_general), R.drawable.ic_toolbar_favorite))
        return categoryList
    }

}