package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_NOTE
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.navigation.Navigation.getBundle.baseGetBundleObject

class ScriptDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_script_detail)

        val data = baseGetBundleObject<Script>(mActivity, EXTRA_NOTE)
        setupDetail(data.description)

    }
}
