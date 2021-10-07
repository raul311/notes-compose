package com.raul311.notescompose.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import com.raul311.notescompose.models.Note
import com.raul311.notescompose.models.NotesViewModel
import com.raul311.notescompose.ui.theme.NotesComposeTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
                val id = if (intent.hasExtra("note-id")) {
                    intent.getLongExtra("note-id", 1L)
                } else {
                    0
                }

                if (id != 0L) {
                    val note = notesViewModel.getNote(id).observeAsState()
                    note.value?.let { note ->
                        fullScreenNote(
                            note,
                            notesViewModel = notesViewModel
                        )
                    }
                } else {
                    fullScreenNote(
                        Note("", "", 1),
                        notesViewModel = notesViewModel
                    )
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
private fun fullScreenNote(
    note: Note,
    notesViewModel: NotesViewModel
) {

    var title by remember { mutableStateOf(note.title) }
    var data by remember { mutableStateOf(note.data) }
    Scaffold(
        content = {
            Surface(
                color = MaterialTheme.colors.background
            ) {
                Card(
                    elevation = 10.dp,

                    ) {
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                    ) {

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = title!!,
                            onValueChange = {
                                title = it
                            },
                            label = {
                                Text("Title")
                            },
                            singleLine = true,
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxSize(),
                            value = data,
                            onValueChange = {
                                data = it
                            },
                            label = {
                                Text("Note")
                            }
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val n = Note(title, data, note.version, note.id)
                    println("click save note $n")
                    saveNote(n, notesViewModel)
                },
                backgroundColor = Color.Red,
                contentColor = Color.White,
            ) {
                Icon(
                    imageVector = Icons.Filled.Create,
                    contentDescription = "save",
                )
            }
        }
    )
}

private fun saveNote(note: Note, notesViewModel: NotesViewModel) {
    println("saving note $note");
    notesViewModel.insertNote(note)
}
