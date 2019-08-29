package com.frogobox.speechbooster.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseFragment
import com.frogobox.speechbooster.base.BaseListener
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.helper.ConstHelper.Extra.OPTION_ACTIVITY_CREATE
import com.frogobox.speechbooster.helper.ConstHelper.Extra.OPTION_ACTIVITY_EDIT
import com.frogobox.speechbooster.helper.ConstHelper.Tag.TAG_ACTIVITY_CREATE
import com.frogobox.speechbooster.helper.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.helper.ConstHelper.TypeData.TYPE_INT
import com.frogobox.speechbooster.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.navigation.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.navigation.Route.routeImplicit.startScriptDetailActivity
import com.frogobox.speechbooster.navigation.Route.routeImplicit.startScriptEditorActivity
import com.frogobox.speechbooster.view.viewadapter.adapter.ScriptAdapter
import kotlinx.android.synthetic.main.fragment_script.*

class ScriptFragment : BaseFragment(), BaseListener<Script> {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_script, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupFabButton()

    }

    private fun setupFabButton() {

        val option = createBaseBundle(TYPE_INT, OPTION_ACTIVITY_CREATE, TAG_ACTIVITY_CREATE)
        fab_script_editor.setOnClickListener {
            context?.let {
                startScriptEditorActivity(it, null, option)
            }
        }

    }

    private fun setupRecyclerView() {
        val noteList = mutableListOf<Script>()
        noteList.add(Script(1, getString(R.string.dummy), getString(R.string.dummyLong)))
        noteList.add(Script(1, getString(R.string.dummy), getString(R.string.dummy)))
        noteList.add(Script(1, getString(R.string.dummy), getString(R.string.dummy)))
        noteList.add(Script(1, getString(R.string.dummy), getString(R.string.dummyLong)))

        val adapter = ScriptAdapter()
        context?.let { adapter.setLayoutItem(it, R.layout.recyclerview_item_notes) }
        adapter.setListener(this)
        adapter.setRecyclerViewData(noteList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onItemClicked(data: Script) {

        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data)
        val option = createBaseBundle(TYPE_INT, OPTION_ACTIVITY_EDIT, TAG_ACTIVITY_EDIT)

        context?.let {
            startScriptDetailActivity(it, extras, option)
        }
    }

    override fun onItemLongClicked(data: Script) {

    }


}
