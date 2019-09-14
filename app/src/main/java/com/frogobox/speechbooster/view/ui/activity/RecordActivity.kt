package com.frogobox.speechbooster.view.ui.activity


import android.os.Bundle
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.view.ui.BaseActivity
import com.frogobox.speechbooster.model.RepositoryScript
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getBaseBundle
import com.frogobox.speechbooster.util.helper.ConstHelper.Arg.ARGUMENTS_EXAMPLE_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Arg.ARGUMENTS_FAVORITE_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Arg.ARGUMENTS_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_EXAMPLE_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_FAVORITE_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.view.ui.fragment.RecordFragment
import com.frogobox.speechbooster.viewmodel.VideoScriptRecordViewModel


class RecordActivity : BaseActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        setupNoLimitStatBar()
        setupRoleFragmentInstance()
    }

    fun obtainVideoRecordViewModel(): VideoScriptRecordViewModel =
        obtainViewModel(VideoScriptRecordViewModel::class.java)

    private fun setupRoleFragmentInstance() {

        val recordFragment = RecordFragment()

        if (checkExtra(EXTRA_SCRIPT)) {

            val extraDataResultScript = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
            recordFragment.baseNewInstance(ARGUMENTS_SCRIPT, extraDataResultScript)
            setupChildFragment(R.id.container, recordFragment)

        } else if (checkExtra(EXTRA_EXAMPLE_SCRIPT)) {

            val extraDataResultExampleScript = getBaseBundle<RepositoryScript>(mActivity, TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT)
            recordFragment.baseNewInstance(ARGUMENTS_EXAMPLE_SCRIPT, extraDataResultExampleScript)
            setupChildFragment(R.id.container, recordFragment)

        } else if (checkExtra(EXTRA_FAVORITE_SCRIPT)) {

            val extraDataResultFavoriteScript = getBaseBundle<FavoriteScript>(mActivity, TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT)
            recordFragment.baseNewInstance(ARGUMENTS_FAVORITE_SCRIPT, extraDataResultFavoriteScript)
            setupChildFragment(R.id.container, recordFragment)

        }

    }

}
