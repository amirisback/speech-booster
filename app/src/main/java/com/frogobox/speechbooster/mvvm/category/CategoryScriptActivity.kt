package com.frogobox.speechbooster.mvvm.category

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseActivity
import com.frogobox.speechbooster.core.BaseListener
import com.frogobox.speechbooster.databinding.ActivityCategoryScriptBinding
import com.frogobox.speechbooster.source.model.CategoryScript
import com.frogobox.speechbooster.source.model.RepositoryScript
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.getBaseBundle
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_CATEGORY
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_EXAMPLE_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_DETAIL
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.route.Implicit.Activity.startScriptDetailActivity

class CategoryScriptActivity : BaseActivity<ActivityCategoryScriptBinding>(),
    BaseListener<RepositoryScript> {

    private lateinit var mViewModel: CategoryScriptViewModel

    override fun setupViewBinding(): ActivityCategoryScriptBinding {
        return ActivityCategoryScriptBinding.inflate(layoutInflater)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDetailActivity("")
        setupData()
        setupShowAdsBanner(binding.ads.adsBanner)
    }

    override fun setupViewModel() {
        mViewModel = obtainCategoryScriptViewModel().apply {

        }
    }

    fun obtainCategoryScriptViewModel(): CategoryScriptViewModel =
        obtainViewModel(CategoryScriptViewModel::class.java)

    private fun setupData() {
        val extraDataResult = getBaseBundle<CategoryScript>(this, TYPE_OBJECT, EXTRA_CATEGORY)
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
