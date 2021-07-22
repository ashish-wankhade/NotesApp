package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModel: NoteViewModel
    lateinit var RVNotes: RecyclerView
    lateinit var NoteIP: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RVNotes = findViewById(R.id.rvNotes)

        RVNotes.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        RVNotes.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list -> list?.let{
            adapter.updateList(it)
        }
        })
    }

    override fun onClickItem(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_SHORT).show()
    }

    fun onClickHandleSubmit(view: View) {
        NoteIP = findViewById(R.id.note_input)
        val noteText = NoteIP.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this, "${noteText} added", Toast.LENGTH_SHORT).show()
        }
    }
}