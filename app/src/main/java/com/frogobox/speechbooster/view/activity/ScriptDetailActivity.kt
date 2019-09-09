package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_EXAMPLE_SCRIPT
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.helper.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.helper.FunHelper.Func.createDialogDefault
import com.frogobox.speechbooster.helper.FunHelper.Func.showToast
import com.frogobox.speechbooster.model.ExampleScript
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.view.navigation.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.view.navigation.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.view.navigation.Navigation.BundleHelper.getBaseBundle
import com.frogobox.speechbooster.view.navigation.Route.routeImplicit.startRecordActivity
import com.frogobox.speechbooster.view.navigation.Route.routeImplicit.startScriptEditorActivity
import com.frogobox.speechbooster.view.callback.ScriptEditorViewCallback
import com.frogobox.speechbooster.viewmodel.ScriptDetailViewModel
import kotlinx.android.synthetic.main.activity_script_detail.*

class ScriptDetailActivity : BaseActivity(), ScriptEditorViewCallback {

    lateinit var mViewModel: ScriptDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_script_detail)
        setupDetailActivity("")
        setupViewModel()
        setupRoleView()
    }

    private fun setupViewElement(data: ExampleScript) {
        tv_title_detail.text = data.title
        tv_date_detail.text = ""
        tv_date_detail.visibility = View.GONE
        tv_description_detail.text = data.description
        btn_start_record.setOnClickListener {
            startRecordActivity(this, createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data))
        }
    }

    private fun setupViewElement(data: Script) {
        tv_title_detail.text = data.title
        tv_date_detail.text = data.dateTime
        tv_description_detail.text = data.description
        btn_start_record.setOnClickListener {
            startRecordActivity(this, createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data))
        }
    }


    private fun setupRoleView() {
        val extraDataResult = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
        val extraDataEdit = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
        val extraDataExample = getBaseBundle<ExampleScript>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)

        if (extraDataResult != null) {
            setupViewElement(extraDataResult)
        } else if (extraDataEdit!= null) {
            setupViewElement(extraDataEdit)
        } else if (extraDataExample != null) {
            setupViewElement(extraDataExample)
        }
    }

    private fun setupViewModel() {
        mViewModel = obtainScriptDetailViewModel().apply {

        }
    }

    fun obtainScriptDetailViewModel(): ScriptDetailViewModel =
        obtainViewModel(ScriptDetailViewModel::class.java)

    private fun listenerMenuEdit() {
        val data = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data)
        val option = createOptionBundle(TAG_ACTIVITY_EDIT)
        startScriptEditorActivity(this, extras, option)
        finish()
    }

    private fun listenerMenuDelete() {
        val titleDialog = "Hapus data"
        val messageDialog = "Apakah anda yakin ingin menghapus dialog ?"
        createDialogDefault(this, titleDialog, messageDialog) {
            val data = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
            mViewModel.deleteData(data.table_id, this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (tagOption() == TAG_ACTIVITY_EDIT) {
            menuInflater.inflate(R.menu.menu_toolbar_edit_delete, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.toolbar_menu_edit -> {
                listenerMenuEdit()
                true
            }

            R.id.toolbar_menu_delete -> {
                listenerMenuDelete()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onShowProgress() {
        super.onShowProgress()
    }

    override fun onHideProgress() {
        super.onHideProgress()
    }

    override fun onSucces() {
        super.onSucces()
        finish()
    }

    override fun onFailed(message: String) {
        super.onFailed(message)
        showToast(this@ScriptDetailActivity, message)
    }
}
