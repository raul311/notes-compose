package com.raul311.notescompose.notes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.raul311.notescompose.core.data.notes.NotesRepository
import com.raul311.notescompose.core.models.notes.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    application: Application,
    private val notesRepo: NotesRepository
) : AndroidViewModel(application) {

    fun getAllNotes(): Flow<List<Note>> {
        return notesRepo.getAllNotes()
    }

    fun getNote(id: Long): Flow<Note> {
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
        viewModelScope.launch(Dispatchers.IO) {
            notesRepo.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepo.deleteNote(note)
        }
    }

}