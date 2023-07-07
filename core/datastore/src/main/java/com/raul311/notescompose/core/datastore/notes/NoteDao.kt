package com.raul311.notescompose.core.datastore.notes

import androidx.lifecycle.LiveData
import androidx.room.*
import com.raul311.notescompose.core.models.notes.Note

@Dao
interface NoteDao {

    @Insert
    fun insertNode(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(note: Note)

    @Query("SELECT * FROM Note")
    fun getNotes() : LiveData<List<Note>>

    @Query("SELECT * FROM Note WHERE id IN (:id)")
    fun getNote(id: Long): LiveData<Note>

    @Delete
    fun deleteNote(note: Note)

}