package com.frogobox.speechbooster.mvvm.repository

import android.app.Application
import android.content.Context
import com.frogobox.sdk.core.FrogoViewModel
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.source.model.CategoryScript
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
class RepositoryCategoryViewModel (
    application: Application,
    private val frogoDataRepository: FrogoDataRepository
) : FrogoViewModel(application) {

    fun showExampleData(context: Context) : MutableList<CategoryScript> {
        val categoryList = mutableListOf<CategoryScript>()
        categoryList.clear()
        categoryList.add(CategoryScript(context.getString(R.string.title_category_religion), R.drawable.ic_repo_religion))
        categoryList.add(CategoryScript(context.getString(R.string.title_category_education), R.drawable.ic_repo_education))
        categoryList.add(CategoryScript(context.getString(R.string.title_category_health), R.drawable.ic_repo_healthy))
        categoryList.add(CategoryScript(context.getString(R.string.title_category_general), R.drawable.ic_repo_general))
        categoryList.add(CategoryScript(context.getString(R.string.title_category_news), R.drawable.ic_repo_news))
        categoryList.add(CategoryScript(context.getString(R.string.title_category_movie), R.drawable.ic_repo_movie))
        return categoryList
    }

}