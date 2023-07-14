package com.raul311.notescompose.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.raul311.notescompose.core.models.notes.Note
import com.raul311.notescompose.notes.ui.elements.FullScreenNote
import com.raul311.notescompose.notes.ui.theme.NotesComposeTheme
import com.raul311.notescompose.notes.viewmodels.NotesViewModel
import kotlinx.coroutines.launch

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
        println("raul - note id = $id")
        var note = Note("", "", 1)
        lifecycleScope.launch {
            if (id != 0L) {
                notesViewModel.getNote(id).collect {
                    note = it
                    setContent {
                        NotesComposeTheme {
                            println("raul $note")
                            FullScreenNote(note!!, notesViewModel)
                        }
                    }
                }
            }
        }
    }
}