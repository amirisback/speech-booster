package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.helper.ConstHelper.Date.DATE_DD_MM_YYYY
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_NOTE
import com.frogobox.speechbooster.helper.DateHelper.Companion.getCurrentDate
import com.frogobox.speechbooster.model.Script

class ScriptDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_script_detail)

        val data = baseGetExtraData<Script>(EXTRA_NOTE)
//        setTitle(getCurrentDate(DATE_DD_MM_YYYY))
        setTitle(data.description)


    }
}
