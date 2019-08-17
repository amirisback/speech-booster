package com.frogobox.speechbooster.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.model.Note
import com.frogobox.speechbooster.view.adapter.NoteAdapter
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val noteList = mutableListOf<Note>()
        noteList.add(Note(getString(R.string.dummy), getString(R.string.dummyLong)))
        noteList.add(Note(getString(R.string.dummy), getString(R.string.dummy)))
        noteList.add(Note(getString(R.string.dummy), getString(R.string.dummy)))
        noteList.add(Note(getString(R.string.dummy), getString(R.string.dummyLong)))
        noteList.add(Note(getString(R.string.dummy), getString(R.string.dummyLong)))


        val adapter = NoteAdapter(this, noteList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


    }
}
