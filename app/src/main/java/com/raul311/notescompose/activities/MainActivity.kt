package com.raul311.notescompose.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import com.raul311.notescompose.core.models.notes.Note
import com.raul311.notescompose.notes.ui.elements.HomeContent
import com.raul311.notescompose.notes.viewmodels.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notesViewModel: NotesViewModel by viewModels()
        var notes: List<Note>? = null
        println("raul MainActivity")
        lifecycleScope.launch {
            println("raul lifecycleScope")
            notesViewModel.getAllNotes().collect {
                println("raul all notes $it")
                notes = it
                setContent {
                    HomeScreen(notes!!)
                }
            }
        }

    }

}

@Composable
fun HomeScreen(notes: List<Note>?) {

    val context = LocalContext.current
    println("raul notes size = ${notes!!.size}")
    HomeContent(
        notes,
        onNoteClicked = { fullScreenNote(context = context, note = it) },
        onNewNoteClicked = { fullScreenNewNote(context = context) }
    )

}

fun fullScreenNote(context: Context, note : Note) {
    println("raul - open fullscreen note -> $note")
    val intent = Intent(context, FullScreenActivity::class.java)
    intent.putExtra("note-id", note.id)
    context.startActivity(intent)
}

fun fullScreenNewNote(context: Context) {
    context.startActivity(Intent(context, FullScreenActivity::class.java))
}
