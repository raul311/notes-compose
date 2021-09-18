package com.raul311.notescompose.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.raul311.notescompose.models.NotesRepo
import com.raul311.notescompose.ui.elements.ExpandableCard2
import com.raul311.notescompose.ui.elements.OnNoteClicked

class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesComposeTheme {
                HomeContent(
                    onNoteClicked = { fullScreenNote(context = this, note = it) }
                )
            }
        }
    }

}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun HomeContent(onNoteClicked : OnNoteClicked) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Notes") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
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
                val notes = NotesRepo.getNotes()
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2)
                ) {
                    items(notes.size) {
                        val note = notes[it]
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




