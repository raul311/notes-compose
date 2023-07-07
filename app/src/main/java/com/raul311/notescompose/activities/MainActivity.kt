package com.raul311.notescompose.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.raul311.notescompose.core.models.notes.Note
import com.raul311.notescompose.notes.ui.elements.HomeContent
import com.raul311.notescompose.notes.ui.elements.OnNoteClicked
import com.raul311.notescompose.notes.ui.theme.Purple200
import com.raul311.notescompose.notes.viewmodels.NotesViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val notesViewModel: NotesViewModel by viewModels()
            HomeScreen(notesViewModel)
        }
    }
}

@Composable
fun HomeScreen(notesViewModel: NotesViewModel) {

    val context = LocalContext.current
    val notes = notesViewModel.getAllNotes().observeAsState()
    notes.value?.let { notes ->
        println("notes size = ${notes.size}")
        HomeContent(
            notes,
            onNoteClicked = { fullScreenNote(context = context, note = it) },
            onNewNoteClicked = { fullScreenNewNote(context = context) }
        )
//        HomeContent()
    }

}



fun fullScreenNote(context: Context, note : Note) {
    val intent = Intent(context, FullScreenActivity::class.java)
    intent.putExtra("note-id", note.id)
    context.startActivity(intent)
}

fun fullScreenNewNote(context: Context) {
    context.startActivity(Intent(context, FullScreenActivity::class.java))
}
