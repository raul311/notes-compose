package com.raul311.notescompose.models

import androidx.compose.runtime.Immutable

@Immutable
data class Note(
    val id: Long,
    val title: String,
    val data: String = ""
)

val notes = listOf(
    Note(
        id = 1L,
        title = "Title to first note",
        data = "This is the body of the note section"
    ),
    Note(
        id = 2L,
        title = "My Title",
        data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat."
    ),
    Note(
        id = 3L,
        title = "Title extra note",
        data = "This is the body of the note section"
    )
)