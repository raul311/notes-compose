package com.raul311.notescompose.ui.elements

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raul311.notescompose.models.Note

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
                        Text(
                            note.title,
                            modifier = Modifier
                                .padding(bottom = 30.dp)
                        )
                        Text(
                            note.data
                        )
                    }
                }
            }
        }
    )
}