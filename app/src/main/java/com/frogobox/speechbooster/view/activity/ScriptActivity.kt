package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseListener
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_NOTE
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.navigation.Navigation.createBundle.baseCreateBundleObject
import com.frogobox.speechbooster.navigation.Route.routeImplicit.openScriptDetailActivity
import com.frogobox.speechbooster.view.viewadapter.adapter.ScriptAdapter
import kotlinx.android.synthetic.main.activity_script.*

class ScriptActivity : BaseActivity(), BaseListener<Script> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_script)

        setupRecyclerView()

    }

    fun setupRecyclerView(){
        val noteList = mutableListOf<Script>()
        noteList.add(Script(1, getString(R.string.dummy), getString(R.string.dummyLong)))
        noteList.add(Script(1, getString(R.string.dummy), getString(R.string.dummy)))
        noteList.add(Script(1, getString(R.string.dummy), getString(R.string.dummy)))
        noteList.add(Script(1, getString(R.string.dummy), getString(R.string.dummyLong)))
        noteList.add(Script(1, getString(R.string.dummy), getString(R.string.dummyLong)))

        val adapter = ScriptAdapter()
        adapter.setLayoutItem(this, R.layout.recyclerview_item_notes)
        adapter.setListener(this)
        adapter.setRecyclerViewData(noteList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onItemClicked(data: Script) {
        openScriptDetailActivity(this, baseCreateBundleObject(EXTRA_NOTE, data))
    }

    override fun onItemLongClicked(data: Script) {

    }

}
