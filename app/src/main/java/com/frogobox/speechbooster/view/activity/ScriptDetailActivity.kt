package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.helper.FunHelper.Func.createDialogDefault
import com.frogobox.speechbooster.model.ExampleScript
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getBaseBundle
import com.frogobox.speechbooster.util.helper.ConstHelper
import com.frogobox.speechbooster.util.helper.ConstHelper.Const.DEFAULT_NULL
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_EXAMPLE_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_FAVORITE_SCRIPT
import com.frogobox.speechbooster.util.helper.DateHelper
import com.frogobox.speechbooster.view.callback.FavoriteEditorViewCallback
import com.frogobox.speechbooster.view.route.Implicit.Activity.startRecordActivity
import com.frogobox.speechbooster.view.route.Implicit.Activity.startScriptEditorActivity
import com.frogobox.speechbooster.view.callback.ScriptEditorViewCallback
import com.frogobox.speechbooster.viewmodel.ScriptDetailViewModel
import kotlinx.android.synthetic.main.activity_script_detail.*
import kotlinx.android.synthetic.main.recyclerview_event_progress.*

class ScriptDetailActivity : BaseActivity(), ScriptEditorViewCallback, FavoriteEditorViewCallback {

    lateinit var mViewModel: ScriptDetailViewModel
    var isFav = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_script_detail)
        setupDetailActivity("")
        setupViewModel()
        setupRoleView()
    }

    private fun setupViewElement(title: String, date: String, desc: String, bundle: Bundle) {
        tv_title_detail.text = title
        tv_description_detail.text = desc
        if (!date.equals(DEFAULT_NULL)) {
            tv_date_detail.visibility = View.VISIBLE
            tv_date_detail.text = date
        } else {
            tv_date_detail.visibility = View.GONE
        }
        btn_start_record.setOnClickListener {
            startRecordActivity(this, bundle)
        }
    }

    private fun setupRoleView() {

        if (checkExtra(EXTRA_SCRIPT)) {

            val extraScript = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
            val bundleScript = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, extraScript)
            setupViewElement(extraScript.title!!, extraScript.dateTime!!, extraScript.description!!, bundleScript)

        } else if (checkExtra(EXTRA_EXAMPLE_SCRIPT)){

            val extraExampleScript = getBaseBundle<ExampleScript>(mActivity, TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT)
            val bundleExampleScript = createBaseBundle(TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT, extraExampleScript)
            setupViewElement(extraExampleScript.title!!, DEFAULT_NULL, extraExampleScript.description!!, bundleExampleScript)

        } else if (checkExtra(EXTRA_FAVORITE_SCRIPT)) {

            val extraFavoriteScript = getBaseBundle<FavoriteScript>(mActivity, TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT)
            val bundleFavoriteScript = createBaseBundle(TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT, extraFavoriteScript)
            setupViewElement(extraFavoriteScript.title!!, extraFavoriteScript.dateTime!!, extraFavoriteScript.description!!, bundleFavoriteScript)

        }

    }

    fun obtainScriptDetailViewModel(): ScriptDetailViewModel =
        obtainViewModel(ScriptDetailViewModel::class.java)

    private fun setupViewModel() {
        mViewModel = obtainScriptDetailViewModel().apply {

            isFavorite.observe(this@ScriptDetailActivity, Observer {
                setupFavoriteView(it)
            })
        }
    }

    private fun setupFavoriteView(favorite: Boolean){
        if (favorite) {
            iv_btn_favorite.setImageResource(R.drawable.ic_toolbar_favorite)
        } else {
            iv_btn_favorite.setImageResource(R.drawable.ic_toolbar_unfavorite)
        }
    }

    private fun setupAddToFavorite(data: FavoriteScript){
        mViewModel.addFavoriteData(data, this)
    }

    private fun setupDeleteFromFavorite(tableid: Int) {
        mViewModel.deleteFavoriteData(tableid,this)
    }

    private fun setupGetFromFavorite(title: String){
        mViewModel.getFavoriteData(title)
    }



    private fun addToFavorite(data: Script){
        val dateNow = DateHelper.getCurrentDate(ConstHelper.Date.DATE_EEEE_DD_MM_YYYY)
        val favoriteScript = FavoriteScript(title = data.title, dateTime = dateNow, description = data.description)
        setupAddToFavorite(favoriteScript)
    }

    private fun addToFavorite(data: ExampleScript){
        val dateNow = DateHelper.getCurrentDate(ConstHelper.Date.DATE_EEEE_DD_MM_YYYY)
        val favoriteScript = FavoriteScript(title = data.title, dateTime = dateNow, description = data.description)
        setupAddToFavorite(favoriteScript)
    }

    private fun addToFavorite(data: FavoriteScript){
        val dateNow = DateHelper.getCurrentDate(ConstHelper.Date.DATE_EEEE_DD_MM_YYYY)
        val favoriteScript = FavoriteScript(title = data.title, dateTime = dateNow, description = data.description)
        setupAddToFavorite(favoriteScript)
    }

    private fun listenerMenuEdit() {
        val data = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data)
        val option = createOptionBundle(TAG_ACTIVITY_EDIT)
        startScriptEditorActivity(this, extras, option)
        finish()
    }

    private fun listenerMenuDelete() {
        val titleDialog = getString(R.string.dialog_title_delete)
        val messageDialog = getString(R.string.dialog_message_delete)
        createDialogDefault(this, titleDialog, messageDialog) {
            val data = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
            mViewModel.deleteScriptData(data.table_id, this)
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
        progress_view.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
        progress_view.visibility = View.GONE
    }

    override fun onSucces() {
        finish()
    }

    override fun onFailed(message: String) {
        showToast(message)
    }
}
