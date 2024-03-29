package com.frogobox.speechbooster.mvvm.script

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.frogobox.sdk.ext.hasExtraExt
import com.frogobox.sdk.ext.showToast
import com.frogobox.sdk.util.FrogoFunc.createDialogDefault
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseActivity
import com.frogobox.speechbooster.databinding.ActivityScriptDetailBinding
import com.frogobox.speechbooster.route.Implicit.Activity.startRecordActivity
import com.frogobox.speechbooster.route.Implicit.Activity.startScriptEditorActivity
import com.frogobox.speechbooster.source.local.LocalDataCallback
import com.frogobox.speechbooster.source.model.FavoriteScript
import com.frogobox.speechbooster.source.model.RepositoryScript
import com.frogobox.speechbooster.source.model.Script
import com.frogobox.speechbooster.util.ConstHelper.Const.DEFAULT_NULL
import com.frogobox.speechbooster.util.ConstHelper.Const.OPTION_GET
import com.frogobox.speechbooster.util.ConstHelper.Extra.EXTRA_EXAMPLE_SCRIPT
import com.frogobox.speechbooster.util.ConstHelper.Extra.EXTRA_FAVORITE_SCRIPT
import com.frogobox.speechbooster.util.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.util.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getBaseBundle
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScriptDetailActivity : BaseActivity<ActivityScriptDetailBinding>() {

    private val mViewModel: ScriptDetailViewModel by viewModel()

    override fun setupViewBinding(): ActivityScriptDetailBinding {
        return ActivityScriptDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupDetailActivity("")
        setupRoleView()
        showAdBanner(binding.adsBanner)
    }

    override fun setupViewModel() {
        mViewModel.apply {
            isFavoriteLive.observe(this@ScriptDetailActivity, Observer {
                setupFavoriteView(it)
                setupButtonFav(it)
            })
        }
    }

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
                // setupShowAdsInterstitial()
            }
        }

    }

    private fun setupRoleView() {
        if (hasExtraExt(EXTRA_SCRIPT)) {
            val extraScript = getBaseBundle<Script>(this, TYPE_OBJECT, EXTRA_SCRIPT)
            val bundleScript = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, extraScript)
            mViewModel.getFavoriteData(extraScript.table_id.toString(), OPTION_GET)
            setupViewElement(
                extraScript.title!!,
                extraScript.dateTime!!,
                extraScript.description!!,
                bundleScript
            )
        } else if (hasExtraExt(EXTRA_EXAMPLE_SCRIPT)) {
            val extraExampleScript =
                getBaseBundle<RepositoryScript>(this, TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT)
            val bundleExampleScript =
                createBaseBundle(TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT, extraExampleScript)
            setupViewElement(
                extraExampleScript.title!!,
                DEFAULT_NULL,
                extraExampleScript.description!!,
                bundleExampleScript
            )
            extraExampleScript.id?.let { mViewModel.getFavoriteData(it, OPTION_GET) }
        } else if (hasExtraExt(EXTRA_FAVORITE_SCRIPT)) {
            val extraFavoreiteScript =
                getBaseBundle<FavoriteScript>(this, TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT)
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
            // setupShowAdsInterstitial()
        }
    }

    private fun listenerDeleteFromFavorite() {
        if (hasExtraExt(EXTRA_SCRIPT)) {
            val extraScript = getBaseBundle<Script>(this, TYPE_OBJECT, EXTRA_SCRIPT)
            setupDeleteFromFavorite(extraScript.table_id.toString())
        } else if (hasExtraExt(EXTRA_EXAMPLE_SCRIPT)) {
            val extraExampleScript =
                getBaseBundle<RepositoryScript>(this, TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT)
            extraExampleScript.id?.let { setupDeleteFromFavorite(it) }
        } else if (hasExtraExt(EXTRA_FAVORITE_SCRIPT)) {
            val extraFavoriteScript =
                getBaseBundle<FavoriteScript>(this, TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT)
            extraFavoriteScript.script_id?.let { setupDeleteFromFavorite(it) }
        }

        showToast(getString(R.string.toast_fav_delete))
    }

    private fun listenerAddToFavorite() {

        var title = ""
        var script_id = ""
        var description = ""

        if (hasExtraExt(EXTRA_SCRIPT)) {
            val extraScript = getBaseBundle<Script>(this, TYPE_OBJECT, EXTRA_SCRIPT)
            title = extraScript.title!!
            script_id = extraScript.table_id.toString()
            description = extraScript.description!!
        } else if (hasExtraExt(EXTRA_EXAMPLE_SCRIPT)) {
            val extraExampleScript =
                getBaseBundle<RepositoryScript>(this, TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT)
            title = extraExampleScript.title!!
            script_id = extraExampleScript.id!!
            description = extraExampleScript.description!!
        } else if (hasExtraExt(EXTRA_FAVORITE_SCRIPT)) {
            val extraScript =
                getBaseBundle<FavoriteScript>(this, TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT)
            title = extraScript.title!!
            script_id = extraScript.script_id!!
            description = extraScript.description!!
        }
        mViewModel.addToFavorite(title, script_id, description, object : LocalDataCallback {

            override fun onShowProgress() {
                binding.progress.progressView.visibility = View.VISIBLE
            }

            override fun onHideProgress() {
                binding.progress.progressView.visibility = View.GONE
            }

            override fun onSuccesInsert() {
            }

            override fun onSuccesDelete() {
                finish()
            }

            override fun onFailed(message: String) {
                showToast(message)
            }

        })

        showToast(getString(R.string.toast_fav_save))
    }

    private fun listenerMenuEdit() {
        val data = getBaseBundle<Script>(this, TYPE_OBJECT, EXTRA_SCRIPT)
        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data)
        val option = createOptionBundle(TAG_ACTIVITY_EDIT)
        startScriptEditorActivity(this, extras, option)
        finish()
    }

    private fun listenerMenuDelete() {
        val titleDialog = getString(R.string.dialog_title_delete)
        val messageDialog = getString(R.string.dialog_message_delete)
        createDialogDefault(this, titleDialog, messageDialog, {
            val data = getBaseBundle<Script>(this, TYPE_OBJECT, EXTRA_SCRIPT)
            mViewModel.deleteScriptData(data.table_id, object : LocalDataCallback {
                override fun onShowProgress() {
                    binding.progress.progressView.visibility = View.VISIBLE
                }

                override fun onHideProgress() {
                    binding.progress.progressView.visibility = View.GONE
                }

                override fun onSuccesInsert() {
                }

                override fun onSuccesDelete() {
                    finish()
                }

                override fun onFailed(message: String) {
                    showToast(message)
                }
            })

        }) {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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

}