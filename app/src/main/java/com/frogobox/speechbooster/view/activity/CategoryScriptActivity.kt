package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.base.BaseListener
import com.frogobox.speechbooster.model.CategoryScript
import com.frogobox.speechbooster.model.ExampleScript
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getBaseBundle
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_CATEGORY
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_DETAIL
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.view.route.Implicit.Activity.startScriptDetailActivity
import com.frogobox.speechbooster.view.viewadapter.CategoryScriptAdapter
import com.frogobox.speechbooster.viewmodel.CategoryScriptViewModel
import kotlinx.android.synthetic.main.activity_category_script.*

class CategoryScriptActivity : BaseActivity(), BaseListener<ExampleScript> {

    lateinit var mViewModel: CategoryScriptViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_script)
        setupDetailActivity("")
        setupViewModel()
        setupData()
    }

    fun obtainCategoryScriptViewModel(): CategoryScriptViewModel =
        obtainViewModel(CategoryScriptViewModel::class.java)

    private fun setupViewModel() {
        mViewModel = obtainCategoryScriptViewModel().apply {

        }
    }

    private fun setupData() {
        val extraDataResult = getBaseBundle<CategoryScript>(mActivity, TYPE_OBJECT, EXTRA_CATEGORY)
        setupRecyclerView(mViewModel.showExampleData(this, extraDataResult.category))
    }

    private fun setupRecyclerView(data: List<ExampleScript>) {
        val adapter = CategoryScriptAdapter()
        adapter.setRecyclerViewLayout(this, R.layout.recyclerview_item_category_script)
        adapter.setRecyclerViewListener(this)
        adapter.setRecyclerViewData(data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onItemClicked(data: ExampleScript) {
        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data)
        val option = createOptionBundle(TAG_ACTIVITY_DETAIL)
        startScriptDetailActivity(this, extras, option)
    }

    override fun onItemLongClicked(data: ExampleScript) {
        noAction()
    }

}
