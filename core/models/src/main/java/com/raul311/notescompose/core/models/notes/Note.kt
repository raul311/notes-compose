package com.raul311.notescompose.core.models.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity
data class Note(
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "body") var body: String,
    @ColumnInfo(name = "version") var version: Int,
    @ColumnInfo(name = "timeStamp") var timeStamp: Long = Calendar.getInstance().timeInMillis,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)