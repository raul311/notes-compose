package com.raul311.notescompose.ui.elements

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raul311.notescompose.models.Note
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

typealias OnNoteClicked = (Note) -> Unit

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun FullScreenNote (
    note : Note
) {
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
                        var title by remember { mutableStateOf(note.title) }
                        var data by remember { mutableStateOf(note.data) }
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = title,
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
        }
    )
}