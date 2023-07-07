package com.raul311.notescompose.notes.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.raul311.notescompose.core.models.notes.Note
import com.raul311.notescompose.notes.ui.theme.Purple200
import com.raul311.notescompose.notes.ui.theme.Purple700


@Composable
fun HomeContent(
    notes: List<Note>,
    onNoteClicked : OnNoteClicked,
    onNewNoteClicked: OnNewNoteClicked) {
//){
    val context = LocalContext.current

    Scaffold(
        backgroundColor = Purple200,
        topBar = {
            TopAppBar(
                title = { Text("My Notes") }
            )
        },
        content = {
            Surface(color = Purple200) {
                LazyColumn (modifier = Modifier.padding(it)) {
                    items(notes.size) {
                        NoteCard(note = notes[it], onNoteClicked = onNoteClicked)
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNewNoteClicked(context) },
                backgroundColor = Purple700,
                contentColor = Color.White,
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "test",
                )
            }
        },
    )
}


@Composable
fun NoteCard(note: Note, onNoteClicked : OnNoteClicked) {
    val paddingModifier = Modifier.padding(10.dp)
    Card(
        elevation = 10.dp,
        contentColor = Color.Blue,
        shape = RoundedCornerShape(16.dp),
        modifier = paddingModifier.clickable { onNoteClicked(note) },
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Text with card content color (Blue)",
                        modifier = paddingModifier
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Date",
                        modifier = paddingModifier,
                        textAlign = TextAlign.Right
                    )
                }
            }
            Row {
                Text(
                    text = "Text with card custom color",
                    color = Color.Black,
                    modifier = paddingModifier
                )
            }
        }
    }
}