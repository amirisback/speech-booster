package com.frogobox.speechbooster.mvvm.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.frogobox.sdk.core.FrogoFunc.noAction
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.speechbooster.core.BaseActivity
import com.frogobox.speechbooster.databinding.ActivityCategoryScriptBinding
import com.frogobox.speechbooster.databinding.RecyclerviewItemCategoryScriptBinding
import com.frogobox.speechbooster.source.model.CategoryScript
import com.frogobox.speechbooster.source.model.RepositoryScript
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getBaseBundle
import com.frogobox.speechbooster.util.ConstHelper.Extra.EXTRA_CATEGORY
import com.frogobox.speechbooster.util.ConstHelper.Extra.EXTRA_EXAMPLE_SCRIPT
import com.frogobox.speechbooster.util.ConstHelper.Tag.TAG_ACTIVITY_DETAIL
import com.frogobox.speechbooster.util.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.route.Implicit.Activity.startScriptDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryScriptActivity : BaseActivity<ActivityCategoryScriptBinding>() {

    private val mViewModel: CategoryScriptViewModel by viewModel()

    override fun setupViewBinding(): ActivityCategoryScriptBinding {
        return ActivityCategoryScriptBinding.inflate(layoutInflater)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDetailActivity("")
        setupData()
        setupShowAdsBanner(binding.ads.adsBanner)
    }

    override fun setupViewModel() {
        mViewModel.apply {

        }
    }

    private fun setupData() {
        val extraDataResult = getBaseBundle<CategoryScript>(this, TYPE_OBJECT, EXTRA_CATEGORY)
        setupRecyclerView(mViewModel.showExampleData(this, extraDataResult.category))
    }

    private fun setupRecyclerView(data: List<RepositoryScript>) {

        val adapterCallback =
            object : IFrogoBindingAdapter<RepositoryScript, RecyclerviewItemCategoryScriptBinding> {
                override fun onItemClicked(data: RepositoryScript) {
                    val extras = createBaseBundle(TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT, data)
                    val option = createOptionBundle(TAG_ACTIVITY_DETAIL)
                    startScriptDetailActivity(this@CategoryScriptActivity, extras, option)
                }

                override fun onItemLongClicked(data: RepositoryScript) {
                    noAction()
                }

                override fun setViewBinding(parent: ViewGroup): RecyclerviewItemCategoryScriptBinding {
                    return RecyclerviewItemCategoryScriptBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                }

                override fun setupInitComponent(
                    binding: RecyclerviewItemCategoryScriptBinding,
                    data: RepositoryScript
                ) {
                    binding.apply {
                        tvTitleExampleScript.text = data.title
                        tvDescriptionExampleScript.text = data.description
                    }
                }
            }

        binding.recyclerView.injectorBinding<RepositoryScript, RecyclerviewItemCategoryScriptBinding>()
            .addData(data)
            .addCallback(adapterCallback)
            .createLayoutLinearVertical(false)
            .build()

    }

}