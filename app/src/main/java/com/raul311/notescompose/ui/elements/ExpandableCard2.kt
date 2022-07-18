package com.raul311.notescompose.ui.elements

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raul311.notescompose.models.notes.Note

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ExpandableCard2(
    note : Note,
    onNoteClicked : OnNoteClicked
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(15.dp)
            .clickable {
                onNoteClicked(note)
            },
        elevation = 10.dp,

        ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
        ) {
            Text(
                note.title!!,
                modifier = Modifier
                    .padding(bottom = 30.dp)
            )
            Text(
                note.body
            )
        }
    }
}
