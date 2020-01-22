package com.frogobox.speechbooster.view.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.view.ui.BaseActivity
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_CREATE
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.helper.DateHelper.Companion.getCurrentDate
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getBaseBundle
import com.frogobox.speechbooster.util.helper.ConstHelper.Date.DATE_EEEE_DD_MM_YYYY
import com.frogobox.speechbooster.view.callback.ScriptEditorViewCallback
import com.frogobox.speechbooster.viewmodel.ScriptEditorViewModel
import kotlinx.android.synthetic.main.activity_script_editor.*
import kotlinx.android.synthetic.main.recyclerview_event_progress.*

class ScriptEditorActivity : BaseActivity(), ScriptEditorViewCallback {

    private lateinit var mViewModel: ScriptEditorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_script_editor)
        setupDetailActivity("")
        setupCreateView()
        setupViewModel()
        setupRole({setupExtraData()}){}
        setupShowAdsBanner(ads_banner)
    }

    private fun setupViewModel() {
        mViewModel = obtainScriptViewModel().apply {

        }
    }

    private fun setupRole(listenerEdit: ()-> Unit, listenerCreate: () -> Unit){
        if (tagOption() == TAG_ACTIVITY_EDIT) {
            listenerEdit()
        } else if (tagOption() == TAG_ACTIVITY_CREATE) {
            listenerCreate()
        }
    }

    private fun setupCreateView(){
        tv_script_date.text = getCurrentDate(DATE_EEEE_DD_MM_YYYY)
    }

    private fun saveToRoom() {
        val textTitle = et_script_title.text.toString()
        val textDescription = et_script_description.text.toString()
        val dataScript = Script(title = textTitle, description = textDescription, dateTime = getCurrentDate(DATE_EEEE_DD_MM_YYYY))
        mViewModel.saveScriptData(dataScript, this)
    }

    private fun updateToRoom() {
        val extraData = getBaseBundle<Script>(mActivity, TYPE_OBJECT,  EXTRA_SCRIPT)
        val textTitle = et_script_title.text.toString()
        val textDescription = et_script_description.text.toString()
        val dataScript = Script(title = textTitle, description = textDescription, dateTime = getCurrentDate(DATE_EEEE_DD_MM_YYYY))
        mViewModel.updateScriptData(extraData.table_id, dataScript, this)
    }

    private fun setupExtraData() {
        val extraData = getBaseBundle<Script>(mActivity, TYPE_OBJECT,  EXTRA_SCRIPT)
        et_script_title.setText(extraData.title)
        et_script_description.setText(extraData.description)
    }

    override fun onShowProgress() {
        progress_view.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
        progress_view.visibility = View.GONE
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

    private fun obtainScriptViewModel(): ScriptEditorViewModel = obtainViewModel(ScriptEditorViewModel::class.java)

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
