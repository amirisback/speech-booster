package com.frogobox.speechbooster.view.activity


import android.os.Bundle
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.view.fragment.RecordFragment


class RecordActivity : BaseActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_new)
        setupNoLimitStatBar()
        setupChildFragment(R.id.container, RecordFragment())
    }

//    fun setupViewElement(){
//        val extraDataResult = Navigation.BundleHelper.getBaseBundle<Script>(
//            mActivity,
//            ConstHelper.TypeData.TYPE_OBJECT,
//            ConstHelper.Extra.EXTRA_SCRIPT
//        )
//
//        tv_description.text = extraDataResult.description
//        tv_title.text = extraDataResult.title
//    }


}
