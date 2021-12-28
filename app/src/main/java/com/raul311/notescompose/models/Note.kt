package com.raul311.notescompose.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "body") var body: String,
    @ColumnInfo(name = "version") var version: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)