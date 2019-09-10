package com.frogobox.speechbooster.camera

import android.app.AlertDialog
import android.os.Bundle

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 03/09/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.util
 *
 */
class DialogError : androidx.fragment.app.DialogFragment() {
    private val ARG_MESSAGE = "message"

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog =
        AlertDialog.Builder(activity)
            .setMessage(arguments?.getString(ARG_MESSAGE))
            .setPositiveButton(android.R.string.ok)  { _, _ -> activity?.finish() }
            .create()

    companion object {
        fun newInstance(message: String) = DialogError().apply {
            arguments = Bundle().apply { putString(ARG_MESSAGE, message) }
        }
    }

}

