package com.raul311.notescompose.models.notes

import androidx.lifecycle.LiveData

class NotesRepo(private val noteDao: NoteDao) {

    fun getAllNotes(): LiveData<List<Note>> = noteDao.getNotes()

    fun getNote(id: Long): LiveData<Note> = noteDao.getNote(id)

    suspend fun insertNode(note: Note) = noteDao.insertNode(note)

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

}