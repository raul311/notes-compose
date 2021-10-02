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
import com.raul311.notescompose.ui.elements.FullScreenNote
import com.raul311.notescompose.ui.theme.NotesComposeTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.raul311.notescompose.ui.elements.OnSaveNoteClicked

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
                        intent.getLongExtra("note-id", 1L),
                        notesViewModel
                    )
                } else {
                    fullScreenNote(notesViewModel = notesViewModel)
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
    id : Long,
    notesViewModel: NotesViewModel
) {
    val note = notesViewModel.getNote(id).observeAsState()
    note.value?.let { note ->
        FullScreenNote(
            note,
            onSaveNoteClicked = { saveNote(note = note, notesViewModel = notesViewModel) }
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
private fun fullScreenNote(
    notesViewModel: NotesViewModel
) {
//    var note = Note("", "", 1)
    var note by remember { mutableStateOf(Note("", "", 1))}
    //FullScreenNote(note, onSaveNoteClicked = { saveNote(note = note, notesViewModel = notesViewModel)})

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
    System.out.println("saving note $note");
    notesViewModel.insertNote(note)
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun FullScreenNote(
    note: Note,
    onSaveNoteClicked : OnSaveNoteClicked
) {
//    OpenNote(note, onSaveNoteClicked)
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
private fun OpenNote (
    note: Note,
    onSaveNoteClicked : OnSaveNoteClicked
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
                    onSaveNoteClicked(n)
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