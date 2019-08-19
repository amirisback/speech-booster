package com.frogobox.speechbooster.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActionListener
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_NOTE
import com.frogobox.speechbooster.helper.FunHelper
import com.frogobox.speechbooster.model.Note
import com.frogobox.speechbooster.view.adapter.NoteAdapter
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : BaseActivity(), BaseActionListener<Note> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        setupRecyclerView()

    }

    fun setupRecyclerView(){
        val noteList = mutableListOf<Note>()
        noteList.add(Note(getString(R.string.dummy), getString(R.string.dummyLong)))
        noteList.add(Note(getString(R.string.dummy), getString(R.string.dummy)))
        noteList.add(Note(getString(R.string.dummy), getString(R.string.dummy)))
        noteList.add(Note(getString(R.string.dummy), getString(R.string.dummyLong)))
        noteList.add(Note(getString(R.string.dummy), getString(R.string.dummyLong)))

        val adapter = NoteAdapter(this, noteList, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onItemClicked(data: Note) {
        val extraData = FunHelper.ConverterJson.toJson(data)
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(EXTRA_NOTE, extraData)
        startActivity(intent)
    }

    override fun onItemLongClicked(data: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
