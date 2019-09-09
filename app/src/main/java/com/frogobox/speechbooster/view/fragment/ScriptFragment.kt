package com.frogobox.speechbooster.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseFragment
import com.frogobox.speechbooster.base.BaseListener
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.helper.ConstHelper.Tag.TAG_ACTIVITY_CREATE
import com.frogobox.speechbooster.helper.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.view.navigation.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.view.navigation.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.view.navigation.Route.routeImplicit.startScriptDetailActivity
import com.frogobox.speechbooster.view.navigation.Route.routeImplicit.startScriptEditorActivity
import com.frogobox.speechbooster.view.activity.MainActivity
import com.frogobox.speechbooster.view.viewadapter.adapter.ScriptAdapter
import com.frogobox.speechbooster.viewmodel.ScriptMainViewModel
import kotlinx.android.synthetic.main.fragment_script.*

class ScriptFragment : BaseFragment(), BaseListener<Script> {

    lateinit var mViewModel: ScriptMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setupViewModel()
        return inflater.inflate(R.layout.fragment_script, container, false)
    }

    fun setupViewModel() {
        mViewModel = (activity as MainActivity).obtainScriptMainViewModel().apply {

            scriptLive.observe(this@ScriptFragment, Observer {
                setupRecyclerView(it)
            })

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFabButton()
        setupDataRoomScript()
    }

    private fun setupFabButton() {

        val option = createOptionBundle(TAG_ACTIVITY_CREATE)
        fab_script_editor.setOnClickListener {
            context?.let {
                startScriptEditorActivity(it, null, option)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        setupDataRoomScript()
    }

    private fun setupDataRoomScript(){
        mViewModel.getData()
    }

    private fun setupRecyclerView(noteList: List<Script>) {
        val adapter = ScriptAdapter()
        context?.let { adapter.setLayoutItem(it, R.layout.recyclerview_item_script) }
        adapter.setListener(this)
        adapter.setRecyclerViewData(noteList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onItemClicked(data: Script) {

        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data)
        val option = createOptionBundle(TAG_ACTIVITY_EDIT)

        context?.let {
            startScriptDetailActivity(it, extras, option)
        }
    }

    override fun onItemLongClicked(data: Script) {
        noAction()
    }


}
