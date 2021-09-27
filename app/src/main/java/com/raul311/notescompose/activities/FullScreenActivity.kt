package com.raul311.notescompose.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.raul311.notescompose.models.NotesViewModel
import com.raul311.notescompose.ui.elements.FullScreenNote
import com.raul311.notescompose.ui.theme.NotesComposeTheme

class FullScreenActivity : ComponentActivity() {

    private val notesViewModel: NotesViewModel by viewModels()

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            NotesComposeTheme {
                if (intent.hasExtra("note-id")) {
                    fullScreenNote(
                        notesViewModel,
                        intent.getLongExtra("note-id", 1L)
                    )
                } else {
                    FullScreenNote()
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun fullScreenNote(
    notesViewModel: NotesViewModel,
    id : Long
) {
    val note = notesViewModel.getNote(id).observeAsState()
    note.value?.let {
        FullScreenNote(
            it.title!!,
            it.data
        )
    }
}
