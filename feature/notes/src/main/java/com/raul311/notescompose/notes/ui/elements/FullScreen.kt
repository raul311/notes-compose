package com.raul311.notescompose.notes.ui.elements

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.raul311.notescompose.core.models.notes.Note
import com.raul311.notescompose.notes.viewmodels.NotesViewModel

@Composable
fun FullScreenNote(
    note: Note,
    notesViewModel: NotesViewModel
) {
    var title by remember { mutableStateOf(note.title) }
    var isSettingsMenuExpanded by remember { mutableStateOf(false) }
    var body by remember { mutableStateOf(note.body) }
    val activity = (LocalContext.current as? Activity)

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 10.dp,
            ) {
                ConstraintLayout (
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val (back) = createRefs()
                    IconButton(
                        onClick = {
                            val note = Note(title, body, note.version + 1, note.id)
                            println("top bar click save note $note")
                            notesViewModel.insertNote(note)
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
                            .padding(it)
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
                                val note = Note(
                                    title!!,
                                    body,
                                    note.version,
                                    note.id)
                                println("BottomNavigationItem click save note $note")
                                notesViewModel.insertNote(note)
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
                                    notesViewModel.deleteNote(note)
                                    activity?.finish()
                                }) {
                                Text(text = "Delete Note")
                            }
                            DropdownMenuItem(
                                onClick = {
                                    val note = Note(
                                        title!!,
                                        body,
                                        note.version,
                                        0)
                                    notesViewModel.insertNote(note)
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