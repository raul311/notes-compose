package com.raul311.notescompose.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import com.raul311.notescompose.core.models.notes.Note
import com.raul311.notescompose.notes.ui.elements.FullScreenNote
import com.raul311.notescompose.notes.ui.elements.OnSaveNoteClicked
import com.raul311.notescompose.notes.ui.theme.NotesComposeTheme
import com.raul311.notescompose.notes.viewmodels.NotesViewModel

class FullScreenActivity : ComponentActivity() {

    private val notesViewModel: NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        val id = if (intent.hasExtra("note-id")) {
            intent.getLongExtra("note-id", 1L)
        } else {
            0
        }

        setContent {
            NotesComposeTheme {
                val note = if (id != 0L) {
                    notesViewModel.getNote(id).observeAsState().value
                } else {
                    Note("", "", 1)
                }
                println("raul $note")
                FullScreenNote(note!!, notesViewModel)
            }
        }
    }

    private fun saveNote(
        title: String,
        body: String,
        version: Int,
        id: Long,
        notesViewModel: NotesViewModel
    ) {

        val note = Note(title, body, version + 1, id)
        println("click save note $note")
        notesViewModel.insertNote(note)
    }

    private fun deleteNote(note: Note, notesViewModel: NotesViewModel) {
        println("click delete note")
        notesViewModel.deleteNote(note)
    }

}