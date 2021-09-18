package com.raul311.notescompose.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.raul311.notescompose.models.NotesRepo
import com.raul311.notescompose.ui.elements.FullScreenNote
import com.raul311.notescompose.ui.theme.NotesComposeTheme

class FullScreenActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            NotesComposeTheme {
                fullScreenNote(
                    intent.getLongExtra("note-id", 1L)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun fullScreenNote(id : Long) {
    val notes = NotesRepo.getNotes()
    var note = notes[0]
    for (n in notes) {
        if (n.id == id) {
            note = n
            break
        }
    }
    FullScreenNote(note)
}