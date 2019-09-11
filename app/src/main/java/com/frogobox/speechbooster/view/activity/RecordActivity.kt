package com.frogobox.speechbooster.view.activity


import android.os.Bundle
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getBaseBundle
import com.frogobox.speechbooster.util.helper.ConstHelper.Arg.ARGUMENTS_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.view.fragment.RecordFragment


class RecordActivity : BaseActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_new)
        setupNoLimitStatBar()
        setupChildFragment(R.id.container, RecordFragment())
        setupViewElement()
    }

    fun setupViewElement(){
        val extraDataResult = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
        RecordFragment().baseNewInstance(ARGUMENTS_SCRIPT, extraDataResult)
    }


}
