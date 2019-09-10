package com.frogobox.speechbooster.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseFragment
import com.frogobox.speechbooster.base.BaseListener
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_CATEGORY
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.model.CategoryScript
import com.frogobox.speechbooster.view.activity.MainActivity
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.view.Route.routeImplicit.startCategoryScriptActivity
import com.frogobox.speechbooster.view.viewadapter.CategoryAdapter
import com.frogobox.speechbooster.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_example_category.*

class ExampleCategoryFragment : BaseFragment(), BaseListener<CategoryScript> {

    lateinit var mViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_example_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupData()
    }

    fun setupViewModel() {
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

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onItemClicked(data: CategoryScript) {
        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_CATEGORY, data)
        context?.let { startCategoryScriptActivity(it, extras) }
    }

    override fun onItemLongClicked(data: CategoryScript) {
        noAction()
    }

}
