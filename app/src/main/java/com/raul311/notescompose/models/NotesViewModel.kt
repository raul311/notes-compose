package com.raul311.notescompose.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

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
        notesRepo.insertNode(note)
    }

    fun updateNote(note: Note) {
        notesRepo.updateNote(note)
    }

    fun deleteNote(note: Note) {
        notesRepo.deleteNote(note)
    }

}