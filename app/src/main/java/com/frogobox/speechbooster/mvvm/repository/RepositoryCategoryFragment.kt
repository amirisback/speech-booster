package com.frogobox.speechbooster.mvvm.repository


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter

import com.frogobox.speechbooster.core.BaseFragment
import com.frogobox.speechbooster.databinding.FragmentRepositoryCategoryBinding
import com.frogobox.speechbooster.databinding.RecyclerviewItemCategoryBinding
import com.frogobox.speechbooster.util.ConstHelper.Extra.EXTRA_CATEGORY
import com.frogobox.speechbooster.util.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.sdk.core.FrogoFunc.noAction
import com.frogobox.speechbooster.source.model.CategoryScript
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.route.Implicit.Activity.startCategoryScriptActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryCategoryFragment : BaseFragment<FragmentRepositoryCategoryBinding>() {

    private val mViewModel: RepositoryCategoryViewModel by viewModel()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRepositoryCategoryBinding {
        return FragmentRepositoryCategoryBinding.inflate(inflater, container, false)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupData()
        setupShowAdsBanner(binding.ads.adsBanner)
    }

    override fun setupViewModel() {
        mViewModel.apply {

        }
    }

    private fun setupData() {
        context?.let { mViewModel.showExampleData(it) }?.let { setupRecyclerView(it) }
    }

    private fun setupRecyclerView(data: List<CategoryScript>) {

        val adapterCallback =
            object : IFrogoBindingAdapter<CategoryScript, RecyclerviewItemCategoryBinding> {
                override fun onItemClicked(
                    binding: RecyclerviewItemCategoryBinding,
                    data: CategoryScript,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<CategoryScript>
                ) {
                    val extras = createBaseBundle(TYPE_OBJECT, EXTRA_CATEGORY, data)
                    context?.let { startCategoryScriptActivity(it, extras) }
                }

                override fun onItemLongClicked(
                    binding: RecyclerviewItemCategoryBinding,
                    data: CategoryScript,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<CategoryScript>
                ) {
                    noAction()
                }

                override fun setViewBinding(parent: ViewGroup): RecyclerviewItemCategoryBinding {
                    return RecyclerviewItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                }

                override fun setupInitComponent(
                    binding: RecyclerviewItemCategoryBinding,
                    data: CategoryScript,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<CategoryScript>
                ) {
                    binding.apply {
                        ivCategory.setImageResource(data.imageCategory)
                        tvCategory.text = data.category
                    }
                }
            }

        binding.recyclerView.injectorBinding<CategoryScript, RecyclerviewItemCategoryBinding>()
            .addData(data)
            .addCallback(adapterCallback)
            .createLayoutGrid(2)
            .build()

    }

}
