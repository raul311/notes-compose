package com.raul311.notescompose.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun insertNode(note: Note)

    @Update
    fun updateNote(note: Note)

    @Query("SELECT * FROM Note")
    fun getNotes() : LiveData<List<Note>>

    @Query("SELECT * FROM Note WHERE id IN (:id)")
    fun getNote(id: Long): LiveData<Note>

    @Delete
    //suspend?
    fun deleteNote(note: Note)

}