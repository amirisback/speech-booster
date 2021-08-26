package com.frogobox.speechbooster.mvvm.script

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.frogobox.sdk.core.FrogoDate.DATE_EEEE_DD_MM_YYYY
import com.frogobox.sdk.core.FrogoDate.getCurrentDate
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseActivity
import com.frogobox.speechbooster.databinding.ActivityScriptEditorBinding
import com.frogobox.speechbooster.source.local.LocalDataCallback
import com.frogobox.speechbooster.util.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.ConstHelper.Tag.TAG_ACTIVITY_CREATE
import com.frogobox.speechbooster.util.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.util.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.source.model.Script
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getBaseBundle
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScriptEditorActivity : BaseActivity<ActivityScriptEditorBinding>(), LocalDataCallback {

    private val mViewModel: ScriptEditorViewModel by viewModel()

    override fun setupViewBinding(): ActivityScriptEditorBinding {
        return ActivityScriptEditorBinding.inflate(layoutInflater)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDetailActivity("")
        binding.tvScriptDate.text = getCurrentDate(DATE_EEEE_DD_MM_YYYY)
        setupRole({setupExtraData()}){}
        setupShowAdsBanner(binding.adsBanner)
    }

    override fun setupViewModel() {
        mViewModel.apply {

        }
    }

    private fun setupRole(listenerEdit: ()-> Unit, listenerCreate: () -> Unit){
        if (tagOption() == TAG_ACTIVITY_EDIT) {
            listenerEdit()
        } else if (tagOption() == TAG_ACTIVITY_CREATE) {
            listenerCreate()
        }
    }

    private fun saveToRoom() {
        val textTitle = binding.etScriptTitle.text.toString()
        val textDescription = binding.etScriptDescription.text.toString()
        val dataScript = Script(title = textTitle, description = textDescription, dateTime = getCurrentDate(DATE_EEEE_DD_MM_YYYY))
        mViewModel.saveScriptData(dataScript, this)
    }

    private fun updateToRoom() {
        val extraData = getBaseBundle<Script>(this, TYPE_OBJECT,  EXTRA_SCRIPT)
        val textTitle = binding.etScriptTitle.text.toString()
        val textDescription = binding.etScriptDescription.text.toString()
        val dataScript = Script(title = textTitle, description = textDescription, dateTime = getCurrentDate(DATE_EEEE_DD_MM_YYYY))
        mViewModel.updateScriptData(extraData.table_id, dataScript, this)
    }

    private fun setupExtraData() {
        val extraData = getBaseBundle<Script>(this, TYPE_OBJECT,  EXTRA_SCRIPT)
        binding.etScriptTitle.setText(extraData.title)
        binding.etScriptDescription.setText(extraData.description)
    }

    override fun onShowProgress() {
        binding.progress.progressView.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
        binding.progress.progressView.visibility = View.GONE
    }

    override fun onSuccesInsert() {
        finish()
    }

    override fun onSuccesDelete() {
        finish()
    }

    override fun onFailed(message: String) {
        showToast(message)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_accept, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.toolbar_menu_accept -> {
                setupRole({
                    updateToRoom()
                }){
                    saveToRoom()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
