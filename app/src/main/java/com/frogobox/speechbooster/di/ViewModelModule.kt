package com.frogobox.speechbooster.di

import com.frogobox.speechbooster.mvvm.category.CategoryScriptViewModel
import com.frogobox.speechbooster.mvvm.repository.RepositoryCategoryViewModel
import com.frogobox.speechbooster.mvvm.favorite.FavoriteScriptMainViewModel
import com.frogobox.speechbooster.mvvm.script.ScriptDetailViewModel
import com.frogobox.speechbooster.mvvm.script.ScriptEditorViewModel
import com.frogobox.speechbooster.mvvm.script.ScriptMainViewModel
import com.frogobox.speechbooster.mvvm.video.VideoScriptDetailViewModel
import com.frogobox.speechbooster.mvvm.video.VideoScriptMainViewModel
import com.frogobox.speechbooster.mvvm.video.VideoScriptRecordViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*
 * Created by faisalamir on 03/08/21
 * speech-booster
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
val viewModelModule = module {

    viewModel {
        CategoryScriptViewModel(androidApplication(), get())
    }

    viewModel {
        RepositoryCategoryViewModel(androidApplication(), get())
    }

    viewModel {
        FavoriteScriptMainViewModel(androidApplication(), get())
    }

    viewModel {
        ScriptMainViewModel(androidApplication(), get())
    }

    viewModel {
        ScriptDetailViewModel(androidApplication(), get())
    }

    viewModel {
        ScriptEditorViewModel(androidApplication(), get())
    }

    viewModel {
        VideoScriptMainViewModel(androidApplication(), get())
    }

    viewModel {
        VideoScriptDetailViewModel(androidApplication(), get())
    }

    viewModel {
        VideoScriptRecordViewModel(androidApplication(), get())
    }

}