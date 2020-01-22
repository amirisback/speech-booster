package com.frogobox.speechbooster.view.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.view.ui.BaseFragment
import com.frogobox.speechbooster.base.view.BaseListener
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_CREATE
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.view.ui.activity.MainActivity
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.view.route.Implicit.Activity.startScriptDetailActivity
import com.frogobox.speechbooster.view.route.Implicit.Activity.startScriptEditorActivity
import com.frogobox.speechbooster.view.adapter.ScriptAdapter
import com.frogobox.speechbooster.viewmodel.ScriptMainViewModel
import kotlinx.android.synthetic.main.fragment_script.*
import kotlinx.android.synthetic.main.recyclerview_event_empty.*
import kotlinx.android.synthetic.main.recyclerview_event_progress.*

class ScriptFragment : BaseFragment(),
    BaseListener<Script> {

    private lateinit var mViewModel: ScriptMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setupViewModel()
        return inflater.inflate(R.layout.fragment_script, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFabButton()
        setupDataRoomScript()
    }

    override fun onResume() {
        super.onResume()
        setupDataRoomScript()
    }

    private fun setupViewModel() {
        mViewModel = (activity as MainActivity).obtainScriptMainViewModel().apply {

            eventIsEmpty.observe(this@ScriptFragment, Observer {
                setupEventEmptyView(empty_view, it)
            })

            scriptListLive.observe(this@ScriptFragment, Observer {
                setupRecyclerView(it)
            })

            eventShowProgress.observe(this@ScriptFragment, Observer {
                setupEventProgressView(progress_view, it)
            })

        }
    }

    private fun setupDataRoomScript() {
        mViewModel.getScriptData()
    }

    private fun setupFabButton() {
        val option = createOptionBundle(TAG_ACTIVITY_CREATE)
        fab_script_editor.setOnClickListener {
            context?.let {
                startScriptEditorActivity(it, null, option)
            }
        }
    }

    private fun setupRecyclerView(data: List<Script>) {
        val adapter = ScriptAdapter()
        context?.let { adapter.setRecyclerViewLayout(it, R.layout.recyclerview_item_script) }
        adapter.setRecyclerViewListener(this)
        adapter.setRecyclerViewData(data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
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