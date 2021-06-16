package com.frogobox.speechbooster.view.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.view.ui.BaseActivity
import com.frogobox.speechbooster.databinding.ActivityScriptDetailBinding
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.model.RepositoryScript
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getBaseBundle
import com.frogobox.speechbooster.util.helper.ConstHelper.Const.DEFAULT_NULL
import com.frogobox.speechbooster.util.helper.ConstHelper.Const.OPTION_GET
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_EXAMPLE_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_FAVORITE_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.helper.FunHelper.Func.createDialogDefault
import com.frogobox.speechbooster.view.callback.FavoriteEditorViewCallback
import com.frogobox.speechbooster.view.callback.ScriptEditorViewCallback
import com.frogobox.speechbooster.view.route.Implicit.Activity.startRecordActivity
import com.frogobox.speechbooster.view.route.Implicit.Activity.startScriptEditorActivity
import com.frogobox.speechbooster.viewmodel.ScriptDetailViewModel

class ScriptDetailActivity : BaseActivity<ActivityScriptDetailBinding>(), ScriptEditorViewCallback, FavoriteEditorViewCallback {

    private lateinit var mViewModel: ScriptDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScriptDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setupDetailActivity("")
        setupViewModel()
        setupLiveObserve()
        setupRoleView()
        setupShowAdsBanner(binding.adsBanner)
    }

    fun obtainScriptDetailViewModel(): ScriptDetailViewModel =
        obtainViewModel(ScriptDetailViewModel::class.java)

    private fun setupViewElement(title: String, date: String, desc: String, bundle: Bundle) {
        binding.apply {

            tvTitleDetail.text = title
            tvDescriptionDetail.text = desc
            if (!date.equals(DEFAULT_NULL)) {
                tvDateDetail.visibility = View.VISIBLE
                tvDateDetail.text = date
            } else {
                tvDateDetail.visibility = View.GONE
            }
            btnStartRecord.setOnClickListener {
                startRecordActivity(this@ScriptDetailActivity, bundle)
                setupShowAdsInterstitial()
            }
        }

    }

    private fun setupRoleView() {
        if (checkExtra(EXTRA_SCRIPT)) {
            val extraScript = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
            val bundleScript = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, extraScript)
            mViewModel.getFavoriteData(extraScript.table_id.toString(), OPTION_GET)
            setupViewElement(
                extraScript.title!!,
                extraScript.dateTime!!,
                extraScript.description!!,
                bundleScript
            )
        } else if (checkExtra(EXTRA_EXAMPLE_SCRIPT)) {
            val extraExampleScript =
                getBaseBundle<RepositoryScript>(mActivity, TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT)
            val bundleExampleScript =
                createBaseBundle(TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT, extraExampleScript)
            setupViewElement(
                extraExampleScript.title!!,
                DEFAULT_NULL,
                extraExampleScript.description!!,
                bundleExampleScript
            )
            extraExampleScript.id?.let { mViewModel.getFavoriteData(it, OPTION_GET) }
        } else if (checkExtra(EXTRA_FAVORITE_SCRIPT)) {
            val extraFavoreiteScript =
                getBaseBundle<FavoriteScript>(mActivity, TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT)
            val bundleFavoreiteScript =
                createBaseBundle(TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT, extraFavoreiteScript)
            setupViewElement(
                extraFavoreiteScript.title!!,
                DEFAULT_NULL,
                extraFavoreiteScript.description!!,
                bundleFavoreiteScript
            )
            extraFavoreiteScript.script_id?.let { mViewModel.getFavoriteData(it, OPTION_GET) }
        }
    }

    private fun setupFavoriteView(isFavorite: Boolean) {
        if (isFavorite) {
            binding.ivBtnFavorite.setImageResource(R.drawable.ic_toolbar_favorite)
        } else {
            binding.ivBtnFavorite.setImageResource(R.drawable.ic_toolbar_unfavorite)
        }
    }

    private fun setupViewModel() {
        mViewModel = obtainScriptDetailViewModel()
    }

    private fun setupLiveObserve() {
        mViewModel.apply {
            isFavoriteLive.observe(this@ScriptDetailActivity, Observer {
                setupFavoriteView(it)
                setupButtonFav(it)
            })
        }
    }

    private fun setupDeleteFromFavorite(script_id: String) {
        mViewModel.deleteFromFavorite(script_id)
    }

    private fun setupButtonFav(isFavorite: Boolean) {
        binding.ivBtnFavorite.setOnClickListener {
            if (isFavorite) {
                listenerDeleteFromFavorite()
            } else {
                listenerAddToFavorite()
            }
            setupShowAdsInterstitial()
        }
    }

    private fun listenerDeleteFromFavorite() {
        if (checkExtra(EXTRA_SCRIPT)) {
            val extraScript = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
            setupDeleteFromFavorite(extraScript.table_id.toString())
        } else if (checkExtra(EXTRA_EXAMPLE_SCRIPT)) {
            val extraExampleScript =
                getBaseBundle<RepositoryScript>(mActivity, TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT)
            extraExampleScript.id?.let { setupDeleteFromFavorite(it) }
        } else if (checkExtra(EXTRA_FAVORITE_SCRIPT)) {
            val extraFavoriteScript =
                getBaseBundle<FavoriteScript>(mActivity, TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT)
            extraFavoriteScript.script_id?.let { setupDeleteFromFavorite(it) }
        }

        showToast(getString(R.string.toast_fav_delete))
    }

    private fun listenerAddToFavorite() {

        var title = ""
        var script_id = ""
        var description = ""

        if (checkExtra(EXTRA_SCRIPT)) {
            val extraScript = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
            title = extraScript.title!!
            script_id = extraScript.table_id.toString()
            description = extraScript.description!!
        } else if (checkExtra(EXTRA_EXAMPLE_SCRIPT)) {
            val extraExampleScript =
                getBaseBundle<RepositoryScript>(mActivity, TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT)
            title = extraExampleScript.title!!
            script_id = extraExampleScript.id!!
            description = extraExampleScript.description!!
        } else if (checkExtra(EXTRA_FAVORITE_SCRIPT)) {
            val extraScript =
                getBaseBundle<FavoriteScript>(mActivity, TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT)
            title = extraScript.title!!
            script_id = extraScript.script_id!!
            description = extraScript.description!!
        }
        mViewModel.addToFavorite(title, script_id, description, this)

        showToast(getString(R.string.toast_fav_save))
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
        createDialogDefault(this, titleDialog, messageDialog, {
            val data = getBaseBundle<Script>(mActivity, TYPE_OBJECT, EXTRA_SCRIPT)
            mViewModel.deleteScriptData(data.table_id, this)

        }){

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
        binding.progress.progressView.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
        binding.progress.progressView.visibility = View.GONE
    }

    override fun onSuccesInsert() {
        setupLiveObserve()

    }

    override fun onSuccesDelete() {
        finish()
    }

    override fun onFailed(message: String) {
        showToast(message)
    }
}