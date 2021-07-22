package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.note_input)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,
            parent, false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onClickItem((allNotes[viewHolder.adapterPosition]))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currNote = allNotes[position]
        holder.textView.text = currNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INotesRVAdapter{
    fun onClickItem(note: Note)
}