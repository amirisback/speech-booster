package com.frogobox.speechbooster.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseFragment
import com.frogobox.speechbooster.base.BaseListener
import com.frogobox.speechbooster.helper.ConstHelper
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_CATEGORY
import com.frogobox.speechbooster.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.helper.FunHelper
import com.frogobox.speechbooster.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.model.CategoryScript
import com.frogobox.speechbooster.view.navigation.Navigation
import com.frogobox.speechbooster.view.navigation.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.view.navigation.Route
import com.frogobox.speechbooster.view.navigation.Route.routeImplicit.startExampleScriptActivity
import com.frogobox.speechbooster.view.viewadapter.adapter.CategoryScriptAdapter
import kotlinx.android.synthetic.main.fragment_example_category.*

class ExampleCategoryFragment : BaseFragment(), BaseListener<CategoryScript> {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_example_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
    }

    private fun setupData() {
        val categoryList = mutableListOf<CategoryScript>()
        categoryList.add(CategoryScript(getString(R.string.title_category_religion), R.drawable.ic_toolbar_favorite))
        categoryList.add(CategoryScript(getString(R.string.title_category_religion), R.drawable.ic_toolbar_favorite))
        categoryList.add(CategoryScript(getString(R.string.title_category_religion), R.drawable.ic_toolbar_favorite))
        categoryList.add(CategoryScript(getString(R.string.title_category_religion), R.drawable.ic_toolbar_favorite))
        categoryList.add(CategoryScript(getString(R.string.title_category_religion), R.drawable.ic_toolbar_favorite))
        setupRecyclerView(categoryList)
    }

    private fun setupRecyclerView(noteList: List<CategoryScript>) {
        val adapter = CategoryScriptAdapter()
        context?.let { adapter.setLayoutItem(it, R.layout.recyclerview_item_category) }
        adapter.setListener(this)
        adapter.setRecyclerViewData(noteList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onItemClicked(data: CategoryScript) {

        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_CATEGORY, data)
        context?.let { startExampleScriptActivity(it, extras) }

    }

    override fun onItemLongClicked(data: CategoryScript) {
        noAction()
    }

}
