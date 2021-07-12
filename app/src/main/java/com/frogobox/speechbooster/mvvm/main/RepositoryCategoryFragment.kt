package com.frogobox.speechbooster.mvvm.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseFragment
import com.frogobox.speechbooster.core.BaseListener
import com.frogobox.speechbooster.databinding.FragmentRepositoryCategoryBinding
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_CATEGORY
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.source.model.CategoryScript
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.route.Implicit.Activity.startCategoryScriptActivity
import com.frogobox.speechbooster.mvvm.category.CategoryAdapter
import com.frogobox.speechbooster.mvvm.category.CategoryViewModel

class RepositoryCategoryFragment : BaseFragment<FragmentRepositoryCategoryBinding>(),
    BaseListener<CategoryScript> {

    private lateinit var mViewModel: CategoryViewModel


    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentRepositoryCategoryBinding {
        return FragmentRepositoryCategoryBinding.inflate(inflater, container, false)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupData()
        binding?.ads?.let { setupShowAdsBanner(it.adsBanner) }
    }

    override fun setupViewModel() {
        mViewModel = (activity as MainActivity).obtainCategoryViewModel().apply {

        }
    }

    private fun setupData() {
        context?.let { mViewModel.showExampleData(it) }?.let { setupRecyclerView(it) }
    }

    private fun setupRecyclerView(data: List<CategoryScript>) {
        val adapter = CategoryAdapter()
        context?.let { adapter.setRecyclerViewLayout(it, R.layout.recyclerview_item_category) }
        adapter.setRecyclerViewListener(this)
        adapter.setRecyclerViewData(data)
        binding?.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onItemClicked(data: CategoryScript) {
        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_CATEGORY, data)
        context?.let { startCategoryScriptActivity(it, extras) }
    }

    override fun onItemLongClicked(data: CategoryScript) {
        noAction()
    }


}
