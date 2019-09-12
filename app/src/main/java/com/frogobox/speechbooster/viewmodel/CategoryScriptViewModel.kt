package com.frogobox.speechbooster.viewmodel

import android.app.Application
import android.content.Context
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseViewModel
import com.frogobox.speechbooster.model.ExampleScript
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
class CategoryScriptViewModel(
    application: Application,
    private val frogoDataRepository: FrogoDataRepository
) : BaseViewModel(application) {

    fun showExampleData(context: Context, param: String) : MutableList<ExampleScript> {
        val exampleScriptList = mutableListOf<ExampleScript>()
        exampleScriptList.clear()

        if (param.equals(context.getString(R.string.title_category_religion))) {
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_religion_title_0),
                    context.getString(R.string.content_religion_desc_0)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_religion_title_1),
                    context.getString(R.string.content_religion_desc_1)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_religion_title_2),
                    context.getString(R.string.content_religion_desc_2)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_religion_title_3),
                    context.getString(R.string.content_religion_desc_3)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_religion_title_4),
                    context.getString(R.string.content_religion_desc_4)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_religion_title_5),
                    context.getString(R.string.content_religion_desc_5)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_religion_title_6),
                    context.getString(R.string.content_religion_desc_6)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_religion_title_7),
                    context.getString(R.string.content_religion_desc_7)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_religion_title_8),
                    context.getString(R.string.content_religion_desc_8)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_religion_title_9),
                    context.getString(R.string.content_religion_desc_9)
                )
            )
        } else if (param.equals(context.getString(R.string.title_category_education))) {

        } else if (param.equals(context.getString(R.string.title_category_health))) {

            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_health_title_0),
                    context.getString(R.string.content_health_desc_0)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_health_title_1),
                    context.getString(R.string.content_health_desc_1)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_health_title_2),
                    context.getString(R.string.content_health_desc_2)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_health_title_3),
                    context.getString(R.string.content_health_desc_3)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_health_title_4),
                    context.getString(R.string.content_health_desc_4)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_health_title_5),
                    context.getString(R.string.content_health_desc_5)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_health_title_6),
                    context.getString(R.string.content_health_desc_6)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_health_title_7),
                    context.getString(R.string.content_health_desc_7)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_health_title_8),
                    context.getString(R.string.content_health_desc_8)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_health_title_9),
                    context.getString(R.string.content_health_desc_9)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_health_title_10),
                    context.getString(R.string.content_health_desc_10)
                )
            )
            exampleScriptList.add(
                ExampleScript(
                    context.getString(R.string.content_health_title_11),
                    context.getString(R.string.content_health_desc_11)
                )
            )

        } else if (param.equals(context.getString(R.string.title_category_general))) {

        }

        return exampleScriptList

    }
}