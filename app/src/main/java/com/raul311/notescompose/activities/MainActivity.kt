package com.raul311.notescompose.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.raul311.notescompose.models.Note
import com.raul311.notescompose.ui.theme.NotesComposeTheme
import com.raul311.notescompose.models.NotesViewModel
import com.raul311.notescompose.ui.elements.ExpandableCard2
import com.raul311.notescompose.ui.elements.OnNoteClicked
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {

//    val notesViewModel by viewModels<NotesViewModel>()

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesComposeTheme {
                val notesViewModel: NotesViewModel by viewModels()
                HomeScreen(notesViewModel)
//                model.getAllNotes().observe(this, { notes ->
//                    HomeContent(
//                        notes,
//                        onNoteClicked = { fullScreenNote(context = this, note = it) }
//                    )
//                })

            }
        }
    }

}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(notesViewModel: NotesViewModel) {

    val context = LocalContext.current
    val notes = notesViewModel.getAllNotes().observeAsState()
    notes.value?.let { notes ->
        System.out.println("notes size = ${notes.size}")
        HomeContent(
            notes,
            onNoteClicked = { fullScreenNote(context = context, note = it) }
        )
    }

}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun HomeContent(notes: List<Note>, onNoteClicked : OnNoteClicked) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Notes") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { fullScreenNewNote(context = context) },
                backgroundColor = Color.Red,
                contentColor = Color.White,
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "test",
                )
            }
        },
        content = {
            Surface(
                color = MaterialTheme.colors.background
            ) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2)
                ) {
                    items(notes.size) {
                        ExpandableCard2(
                            notes[it],
                            onNoteClicked
                        )
                    }
                }
            }
        }
    )
}

fun fullScreenNote(context: Context, note : Note) {
    val intent = Intent(context, FullScreenActivity::class.java)
    intent.putExtra("note-id", note.id)
    context.startActivity(intent)
}

fun fullScreenNewNote(context: Context) {
    context.startActivity(Intent(context, FullScreenActivity::class.java))
}




