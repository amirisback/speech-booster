package com.frogobox.speechbooster.view.ui.activity


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseActivity
import com.frogobox.speechbooster.databinding.ActivityRecordBinding
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.model.RepositoryScript
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


class RecordActivity : BaseActivity<ActivityRecordBinding>() {

    override fun setupViewBinding(): ActivityRecordBinding {
        return ActivityRecordBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {}

    override fun setupUI(savedInstanceState: Bundle?) {
        setupNoLimitStatBar()
        setupRoleFragmentInstance()
        setupReadExternalStoragePermission()
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

            val extraDataResultExampleScript =
                getBaseBundle<RepositoryScript>(mActivity, TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT)
            recordFragment.baseNewInstance(ARGUMENTS_EXAMPLE_SCRIPT, extraDataResultExampleScript)
            setupChildFragment(R.id.container, recordFragment)

        } else if (checkExtra(EXTRA_FAVORITE_SCRIPT)) {

            val extraDataResultFavoriteScript =
                getBaseBundle<FavoriteScript>(mActivity, TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT)
            recordFragment.baseNewInstance(ARGUMENTS_FAVORITE_SCRIPT, extraDataResultFavoriteScript)
            setupChildFragment(R.id.container, recordFragment)

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            READ_EXTERNAL_STORAGE_PERMMISSION_RESULT -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Call cursor loader
                // Toast.makeText(this, "Now have access to view thumbs", Toast.LENGTH_SHORT).show();
                // supportLoaderManager.initLoader(MEDIASTORE_LOADER_ID, null, this)
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun setupReadExternalStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Start cursor loader

            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "App needs to view thumbnails", Toast.LENGTH_SHORT).show()
                }
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_EXTERNAL_STORAGE_PERMMISSION_RESULT
                )
            }
        } else {

        }
    }

    companion object {
        private val READ_EXTERNAL_STORAGE_PERMMISSION_RESULT = 0
    }

}
