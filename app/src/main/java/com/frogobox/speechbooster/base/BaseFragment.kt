package com.frogobox.speechbooster.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.frogobox.speechbooster.helper.FunHelper

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PublicSpeakingBooster
 * Copyright (C) 16/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.publicspeakingbooster.base
 *
 */
abstract class BaseFragment : Fragment() {

    lateinit var mActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = (activity as BaseActivity)
    }

    protected fun <Fragment : androidx.fragment.app.Fragment> putArgs(argsBuilder: Bundle.() -> Unit) {
        this.apply {
            arguments = Bundle().apply(argsBuilder)
        }
    }

    protected fun <Fragment : androidx.fragment.app.Fragment, Model> baseNewInstance(argsKey: String, data: Model) {
        val argsData = FunHelper.ConverterJson.toJson(data)
        this.putArgs<Fragment> {
            putString(argsKey, argsData)
        }
    }

    protected inline fun <Fragment : androidx.fragment.app.Fragment, reified Model> baseGetInstance(argsKey: String) : Model {
        val argsData = this.arguments?.getString(argsKey)
        val instaceData = FunHelper.ConverterJson.fromJson<Model>(argsData)
        return instaceData
    }

}