package com.example.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDb:RoomDatabase(){

    abstract fun getNoteDao(): NoteDao

    companion object{

        @Volatile
        private var INSTANCE: NoteDb? = null

        fun getDatabase(context: Context): NoteDb{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDb::class.java,
                    "notes_table"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}