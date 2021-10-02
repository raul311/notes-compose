package com.raul311.notescompose.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "body") val data: String = "",
    @ColumnInfo(name = "version") val version: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)

//val notes = listOf(
//    Note(
//        id = 1,
//        title = "Title to first note",
//        data = "This is the body of the note section"
//    ),
//    Note(
//        id = 2,
//        title = "My Title",
//        data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
//                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
//                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
//                "ullamco laboris nisi ut aliquip ex ea commodo consequat."
//    ),
//    Note(
//        id = 3,
//        title = "Title extra note",
//        data = "This is the body of the note section"
//    )
//)