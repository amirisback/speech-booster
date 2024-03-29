package com.frogobox.speechbooster.mvvm.script


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.sdk.ext.emptyViewHandle
import com.frogobox.sdk.ext.progressViewHandle
import com.frogobox.speechbooster.core.BaseFragment
import com.frogobox.speechbooster.databinding.FragmentScriptBinding
import com.frogobox.speechbooster.databinding.RecyclerviewItemScriptBinding
import com.frogobox.speechbooster.route.Implicit.Activity.startScriptDetailActivity
import com.frogobox.speechbooster.route.Implicit.Activity.startScriptEditorActivity
import com.frogobox.speechbooster.source.model.Script
import com.frogobox.speechbooster.util.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.ConstHelper.Tag.TAG_ACTIVITY_CREATE
import com.frogobox.speechbooster.util.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.util.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScriptFragment : BaseFragment<FragmentScriptBinding>() {

    private val mViewModel: ScriptMainViewModel by viewModel()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentScriptBinding {
        return FragmentScriptBinding.inflate(inflater, container, false)
    }

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
        super.onViewCreatedExt(view, savedInstanceState)
        setupFabButton()
        setupDataRoomScript()
    }

    override fun onResume() {
        super.onResume()
        setupDataRoomScript()
    }

    override fun setupViewModel() {
        mViewModel.apply {

            eventEmpty.observe(this@ScriptFragment) {
                binding.empty.emptyView.emptyViewHandle(it)
            }

            scriptListLive.observe(this@ScriptFragment) {
                setupRecyclerView(it)
            }

            eventShowProgress.observe(this@ScriptFragment) {
                binding.progress.progressView.progressViewHandle(it)
            }

        }
    }

    private fun setupDataRoomScript() {
        mViewModel.getScriptData()
    }

    private fun setupFabButton() {
        val option = createOptionBundle(TAG_ACTIVITY_CREATE)
        binding.fabScriptEditor.setOnClickListener {
            context?.let {
                startScriptEditorActivity(it, null, option)
            }
            // setupShowAdsInterstitial()
        }
    }

    private fun setupRecyclerView(data: List<Script>) {

        val adapterCallback = object : IFrogoBindingAdapter<Script, RecyclerviewItemScriptBinding> {
            override fun onItemClicked(
                binding: RecyclerviewItemScriptBinding,
                data: Script,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Script>
            ) {
                val extras = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data)
                val option = createOptionBundle(TAG_ACTIVITY_EDIT)
                context?.let { startScriptDetailActivity(it, extras, option) }
            }

            override fun onItemLongClicked(
                binding: RecyclerviewItemScriptBinding,
                data: Script,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Script>
            ) {
            }

            override fun setViewBinding(parent: ViewGroup): RecyclerviewItemScriptBinding {
                return RecyclerviewItemScriptBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }

            override fun setupInitComponent(
                binding: RecyclerviewItemScriptBinding,
                data: Script,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Script>
            ) {
                binding.apply {
                    tvTitle.text = data.title
                    tvDescription.text = data.description
                    tvDate.text = data.dateTime

                    if (data.favorite!!) {
                        ivFavorite.visibility = View.VISIBLE
                    } else {
                        ivFavorite.visibility = View.GONE
                    }
                }
            }
        }

        binding.recyclerView.injectorBinding<Script, RecyclerviewItemScriptBinding>()
            .addData(data)
            .addCallback(adapterCallback)
            .createLayoutStaggeredGrid(2)
            .build()

    }

}