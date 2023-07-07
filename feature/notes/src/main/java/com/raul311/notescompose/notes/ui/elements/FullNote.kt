package com.raul311.notescompose.notes.ui.elements

import android.content.Context
import com.raul311.notescompose.core.models.notes.Note

typealias OnNoteClicked = (Note) -> Unit
typealias OnSaveNoteClicked = (Note) -> Unit
typealias OnNewNoteClicked = (Context) -> Unit

