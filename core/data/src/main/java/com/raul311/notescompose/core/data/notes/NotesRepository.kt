package com.raul311.notescompose.core.data.notes

import com.raul311.notescompose.core.datastore.notes.NoteDao
import com.raul311.notescompose.core.models.notes.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepository @Inject constructor(private val noteDao: NoteDao) {

    fun getAllNotes(): Flow<List<Note>> = noteDao.getNotes()

    fun getNote(id: Long): Flow<Note> = noteDao.getNote(id)

    fun insertNode(note: Note) = noteDao.insertNode(note)

    fun updateNote(note: Note) = noteDao.updateNote(note)

    fun deleteNote(note: Note) = noteDao.deleteNote(note)

}