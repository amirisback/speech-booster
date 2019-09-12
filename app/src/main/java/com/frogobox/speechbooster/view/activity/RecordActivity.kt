package com.frogobox.speechbooster.view.activity


import android.os.Bundle
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.model.ExampleScript
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getBaseBundle
import com.frogobox.speechbooster.util.helper.ConstHelper.Arg.ARGUMENTS_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.view.fragment.RecordFragment
import com.frogobox.speechbooster.viewmodel.VideoScriptRecordViewModel


class RecordActivity : BaseActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_new)
        setupNoLimitStatBar()
        setupRoleFragmentInstance()
    }

    fun obtainVideoRecordViewModel(): VideoScriptRecordViewModel =
        obtainViewModel(VideoScriptRecordViewModel::class.java)

    private fun setupRoleFragmentInstance() {
        val extraDataResultScript = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
        val extraDataResultExampleScript = getBaseBundle<ExampleScript>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
        val extraDataResultFavoriteScript = getBaseBundle<FavoriteScript>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
        val recordFragment = RecordFragment()

        if (extraDataResultScript != null) {
            setupNewInstance(recordFragment, extraDataResultScript)
        } else if (extraDataResultExampleScript != null) {
            setupNewInstance(recordFragment, extraDataResultExampleScript)
        } else if (extraDataResultFavoriteScript != null) {
            setupNewInstance(recordFragment, extraDataResultFavoriteScript)
        }

    }

    private fun setupNewInstance(fragment: RecordFragment, data: Script){
        fragment.baseNewInstance(ARGUMENTS_SCRIPT, data)
        setupChildFragment(R.id.container, fragment)
    }

    private fun setupNewInstance(fragment: RecordFragment, data: ExampleScript){
        fragment.baseNewInstance(ARGUMENTS_SCRIPT, data)
        setupChildFragment(R.id.container, fragment)
    }

    private fun setupNewInstance(fragment: RecordFragment, data: FavoriteScript){
        fragment.baseNewInstance(ARGUMENTS_SCRIPT, data)
        setupChildFragment(R.id.container, fragment)
    }

}
