package com.raul311.notescompose.notes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.raul311.notescompose.core.data.notes.NotesRepository
import com.raul311.notescompose.core.datastore.notes.NoteDatabase
import com.raul311.notescompose.core.models.notes.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val notesRepo: NotesRepository

    init {
        val noteDB = NoteDatabase.getDatabase(application).noteDao()
        notesRepo = NotesRepository(noteDB)
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return notesRepo.getAllNotes()
    }

    fun getNote(id: Long): LiveData<Note> {
        return notesRepo.getNote(id)
    }

    fun insertNote(note: Note) {
        println("note id = $note")
        if (note.id == 0L) {
            viewModelScope.launch(Dispatchers.IO) {
                notesRepo.insertNode(note)
            }
        } else {
            updateNote(note)
        }
    }

    fun updateNote(note: Note) {
        println("updating note id = $note")
        viewModelScope.launch {
            notesRepo.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            notesRepo.deleteNote(note)
        }
    }

}