package com.raul311.notescompose.core.datastore.notes

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(com.raul311.notescompose.core.models.notes.Note::class), version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}