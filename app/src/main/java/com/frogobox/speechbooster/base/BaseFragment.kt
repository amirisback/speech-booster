package com.frogobox.speechbooster.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.frogobox.speechbooster.util.helper.FunHelper
import kotlinx.android.synthetic.main.recyclerview_event_empty.*
import kotlinx.android.synthetic.main.recyclerview_event_progress.*

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

    protected fun setupChildFragment(frameId: Int, fragment: Fragment){
        childFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            commit()
        }
    }

    fun <Model> baseNewInstance(argsKey: String, data: Model){
        val argsData = FunHelper.ConverterJson.toJson(data)
        val bundleArgs = Bundle().apply {
            putString(argsKey, argsData)
        }
        this.arguments = bundleArgs
    }

    protected inline fun <reified Model> baseGetInstance(argsKey: String) : Model {
        val argsData = this.arguments?.getString(argsKey)
        val instaceData = FunHelper.ConverterJson.fromJson<Model>(argsData)
        return instaceData
    }

    protected fun setupEventEmptyView(isEmpty: Boolean) {
        if (isEmpty) {
            empty_view.visibility = View.VISIBLE
        } else {
            empty_view.visibility = View.GONE
        }
    }

    protected fun setupEventProgressView(progress: Boolean) {
        if (progress) {
            progress_view.visibility = View.VISIBLE
        } else {
            progress_view.visibility = View.GONE
        }
    }

    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}