package com.frogobox.speechbooster.mvvm.script


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseFragment
import com.frogobox.speechbooster.core.BaseListener
import com.frogobox.speechbooster.databinding.FragmentScriptBinding
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_CREATE
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.source.model.Script
import com.frogobox.speechbooster.mvvm.main.MainActivity
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.route.Implicit.Activity.startScriptDetailActivity
import com.frogobox.speechbooster.route.Implicit.Activity.startScriptEditorActivity

class ScriptFragment : BaseFragment<FragmentScriptBinding>(),
    BaseListener<Script> {

    private lateinit var mViewModel: ScriptMainViewModel

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentScriptBinding {
        return FragmentScriptBinding.inflate(inflater, container, false)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupFabButton()
        setupDataRoomScript()
    }

    override fun onResume() {
        super.onResume()
        setupDataRoomScript()
    }

    override fun setupViewModel() {
        mViewModel = (activity as MainActivity).obtainScriptMainViewModel().apply {

            eventIsEmpty.observe(this@ScriptFragment, Observer {
                binding?.empty?.let { it1 -> setupEventEmptyView(it1.emptyView, it) }
            })

            scriptListLive.observe(this@ScriptFragment, Observer {
                setupRecyclerView(it)
            })

            eventShowProgress.observe(this@ScriptFragment, Observer {
                binding?.progress?.let { it1 -> setupEventProgressView(it1.progressView, it) }
            })

        }
    }

    private fun setupDataRoomScript() {
        mViewModel.getScriptData()
    }

    private fun setupFabButton() {
        val option = createOptionBundle(TAG_ACTIVITY_CREATE)
        binding?.fabScriptEditor?.setOnClickListener {
            context?.let {
                startScriptEditorActivity(it, null, option)
            }
            setupShowAdsInterstitial()
        }
    }

    private fun setupRecyclerView(data: List<Script>) {
        val adapter = ScriptAdapter()
        context?.let { adapter.setRecyclerViewLayout(it, R.layout.recyclerview_item_script) }
        adapter.setRecyclerViewListener(this)
        adapter.setRecyclerViewData(data)
        binding?.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    override fun onItemClicked(data: Script) {
        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data)
        val option = createOptionBundle(TAG_ACTIVITY_EDIT)
        context?.let { startScriptDetailActivity(it, extras, option) }
    }

    override fun onItemLongClicked(data: Script) {
        noAction()
    }

}