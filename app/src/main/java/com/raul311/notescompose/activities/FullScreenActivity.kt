package com.raul311.notescompose.activities

import android.app.Activity
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
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.sharp.Close
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

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
    var expanded by remember { mutableStateOf(false) }
    val activity = (LocalContext.current as? Activity)
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 10.dp,
            ) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxWidth()
                ){
                    val (back, settings) = createRefs()
                    IconButton(
                        onClick = {
                            saveNote(title!!, data, note.version, note.id, notesViewModel)
                            activity?.finish()
                        },
                        modifier = Modifier
                            .constrainAs(back) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                            }
                    ) {
                        Icon(Icons.Filled.ArrowBack,"Back")
                    }
                }
            }
        },
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
        bottomBar = {
            BottomAppBar(
                content = {
                    BottomNavigation() {
                        BottomNavigationItem(
                            icon = {
                                Icon(Icons.Sharp.Close, "")
                            },
                            label = { Text(text = "Checkboxes") },
                            selected = false,
                            onClick = {
                            },
                        )
                        BottomNavigationItem(
                            icon = {
                                Icon(Icons.Filled.Done, "")
                            },
                            label = { Text(text = "Save") },
                            selected = false,
                            onClick = {
                                saveNote(title!!, data, note.version, note.id, notesViewModel)
                            },
                        )
                        BottomNavigationItem(
                            icon = {
                                Icon(Icons.Filled.MoreVert, "")
                            },
                            label = { Text(text = "Settings") },
                            selected = false,
                            onClick = {
                                expanded = !expanded
                            },
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            DropdownMenuItem(
                                onClick = {
                                    deleteNote(note, notesViewModel)
                                    activity?.finish()
                                }) {
                                Text(text = "Delete Note")
                            }
                            DropdownMenuItem(
                                onClick = {
                                    saveNote(title!!, data, note.version, 0, notesViewModel)
                                    expanded = !expanded
                                }) {
                                Text(text = "Make a Copy")
                            }
                            DropdownMenuItem(onClick = { /*TODO*/ }) {
                                Text(text = "Share (Pending)")
                            }
                            DropdownMenuItem(onClick = { /*TODO*/ }) {
                                Text(text = "Collaborator (Pending)")
                            }
                            DropdownMenuItem(onClick = { /*TODO*/ }) {
                                Text(text = "Labels (Pending)")
                            }
                        }
                    }
                }
            )
        }
    )
}

private fun saveNote(title: String, data: String, version: Int, id: Long, notesViewModel: NotesViewModel) {
    val note = Note(title, data, version, id)
    println("click save note $note")
    notesViewModel.insertNote(note)
}

private fun deleteNote(note: Note, notesViewModel: NotesViewModel) {
    println("click delete note")
    notesViewModel.deleteNote(note)
}
