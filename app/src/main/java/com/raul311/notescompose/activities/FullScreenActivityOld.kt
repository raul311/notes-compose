/*package com.raul311.notescompose.activities

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
//import com.raul311.notescompose.core.models.notes.Note
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.raul311.notescompose.notes.viewmodels.NotesViewModel
/*
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
private fun fullScreenNote(
    note: com.raul311.notescompose.core.models.notes.Note,
    notesViewModel: NotesViewModel
) {
    var title by remember { mutableStateOf(note.title) }
    var isSettingsMenuExpanded by remember { mutableStateOf(false) }
    var body by remember { mutableStateOf(note.body)}
    val activity = (LocalContext.current as? Activity)

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 10.dp,
            ) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val (back) = createRefs()
                    IconButton(
                        onClick = {
                            saveNote(
                                title!!,
                                body,
                                note.version,
                                note.id,
                                notesViewModel)
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
                            value = body,
                            onValueChange = { body = it },
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
                                println("whole text = $body")
                            },
                        )
                        BottomNavigationItem(
                            icon = {
                                Icon(Icons.Filled.Done, "")
                            },
                            label = { Text(text = "Save") },
                            selected = false,
                            onClick = {
                                saveNote(
                                    title!!,
                                    body,
                                    note.version,
                                    note.id,
                                    notesViewModel)
                            },
                        )
                        BottomNavigationItem(
                            icon = {
                                Icon(Icons.Filled.MoreVert, "")
                            },
                            label = { Text(text = "Settings") },
                            selected = false,
                            onClick = {
                                isSettingsMenuExpanded = !isSettingsMenuExpanded
                            },
                        )
                        DropdownMenu(
                            expanded = isSettingsMenuExpanded,
                            onDismissRequest = { isSettingsMenuExpanded = false },
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
                                    saveNote(
                                        title!!,
                                        body,
                                        note.version,
                                        0,
                                        notesViewModel)
                                    isSettingsMenuExpanded = !isSettingsMenuExpanded
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

private fun saveNote(
    title: String,
    body: String,
    version: Int,
    id: Long,
    notesViewModel: NotesViewModel
) {

    val note = com.raul311.notescompose.core.models.notes.Note(title, body, version + 1, id)
    println("click save note $note")
    notesViewModel.insertNote(note)
}

private fun deleteNote(note: com.raul311.notescompose.core.models.notes.Note, notesViewModel: NotesViewModel) {
    println("click delete note")
    notesViewModel.deleteNote(note)
}
*/