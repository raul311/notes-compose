package com.raul311.notescompose.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val notesRepo: NotesRepo

    init {
        val noteDB = NoteDatabase.getDatabase(application).noteDao()
        notesRepo = NotesRepo(noteDB)
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
            viewModelScope.launch {
                notesRepo.insertNode(note)
            }
        } else {
            updateNote(note = note)
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