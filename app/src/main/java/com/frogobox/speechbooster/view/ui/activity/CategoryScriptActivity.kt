package com.frogobox.speechbooster.view.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.view.ui.BaseActivity
import com.frogobox.speechbooster.base.view.BaseListener
import com.frogobox.speechbooster.databinding.ActivityCategoryScriptBinding
import com.frogobox.speechbooster.model.CategoryScript
import com.frogobox.speechbooster.model.RepositoryScript
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getBaseBundle
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_CATEGORY
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_EXAMPLE_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_DETAIL
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.view.route.Implicit.Activity.startScriptDetailActivity
import com.frogobox.speechbooster.view.adapter.CategoryScriptAdapter
import com.frogobox.speechbooster.viewmodel.CategoryScriptViewModel

class CategoryScriptActivity : BaseActivity<ActivityCategoryScriptBinding>(),
    BaseListener<RepositoryScript> {

    private lateinit var mViewModel: CategoryScriptViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryScriptBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setupDetailActivity("")
        setupViewModel()
        setupData()
        setupShowAdsBanner(binding.ads.adsBanner)
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

    private fun setupRecyclerView(data: List<RepositoryScript>) {
        val adapter = CategoryScriptAdapter()
        adapter.setRecyclerViewLayout(this, R.layout.recyclerview_item_category_script)
        adapter.setRecyclerViewListener(this)
        adapter.setRecyclerViewData(data)
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@CategoryScriptActivity, LinearLayoutManager.VERTICAL, false)

        }

    }

    override fun onItemClicked(data: RepositoryScript) {
        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_EXAMPLE_SCRIPT, data)
        val option = createOptionBundle(TAG_ACTIVITY_DETAIL)
        startScriptDetailActivity(this, extras, option)
    }

    override fun onItemLongClicked(data: RepositoryScript) {
        noAction()
    }

}
