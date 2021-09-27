package com.raul311.notescompose.ui.elements

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raul311.notescompose.models.Note
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

typealias OnNoteClicked = (Note) -> Unit

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun FullScreenNote(title: String, data: String) {
    OpenNote(title, data)
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun FullScreenNote() {
    OpenNote("", "")
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
private fun OpenNote (
    title: String,
    data: String
) {
    val context = LocalContext.current
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
                        var title by remember { mutableStateOf(title) }
                        var data by remember { mutableStateOf(data) }
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
                onClick = { /* TODO */ },
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

