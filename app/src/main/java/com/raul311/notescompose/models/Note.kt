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