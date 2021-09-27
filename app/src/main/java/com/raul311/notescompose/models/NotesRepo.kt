package com.raul311.notescompose.models

import androidx.lifecycle.LiveData

class NotesRepo(private val noteDao: NoteDao) {

    fun getAllNotes(): LiveData<List<Note>> = noteDao.getNotes()

    fun getNote(id: Long): LiveData<Note> = noteDao.getNote(id)

    fun insertNode(note: Note) = noteDao.insertNode(note)

    fun updateNote(note: Note) = noteDao.updateNote(note)

    fun deleteNote(note: Note) = noteDao.deleteNote(note)

}