package com.frogobox.speechbooster.camera

import android.app.AlertDialog
import android.os.Bundle
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.util.helper.ConstHelper

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
class DialogConfirmation : androidx.fragment.app.DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        AlertDialog.Builder(activity)
            .setMessage(R.string.permission_request)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                parentFragment?.requestPermissions(
                    ConstHelper.Code.CODE_VIDEO_PERMISSIONS,
                    ConstHelper.Code.CODE_REQUEST_VIDEO_PERMISSIONS
                )
            }
            .setNegativeButton(android.R.string.cancel) { _,_ ->
                parentFragment?.activity?.finish()
            }
            .create()

}