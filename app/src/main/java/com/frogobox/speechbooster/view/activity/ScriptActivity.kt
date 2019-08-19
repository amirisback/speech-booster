package com.frogobox.speechbooster.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActionListener
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_NOTE
import com.frogobox.speechbooster.helper.FunHelper
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.view.adapter.NoteAdapter
import kotlinx.android.synthetic.main.activity_script.*

class ScriptActivity : BaseActivity(), BaseActionListener<Script> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_script)

        setupRecyclerView()

    }

    fun setupRecyclerView(){
        val noteList = mutableListOf<Script>()
        noteList.add(Script(getString(R.string.dummy), getString(R.string.dummyLong)))
        noteList.add(Script(getString(R.string.dummy), getString(R.string.dummy)))
        noteList.add(Script(getString(R.string.dummy), getString(R.string.dummy)))
        noteList.add(Script(getString(R.string.dummy), getString(R.string.dummyLong)))
        noteList.add(Script(getString(R.string.dummy), getString(R.string.dummyLong)))

        val adapter = NoteAdapter(this, noteList, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onItemClicked(data: Script) {
        baseStartActivity<ScriptDetailActivity, Script>(this, EXTRA_NOTE, data)
    }

    override fun onItemLongClicked(data: Script) {

    }

}
