package com.raul311.notescompose.core.datastore.notes

import androidx.room.*
import com.raul311.notescompose.core.models.notes.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    fun insertNode(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(note: Note)

    @Query("SELECT * FROM Note")
    fun getNotes() : Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE id IN (:id)")
    fun getNote(id: Long): Flow<Note>

    @Delete
    fun deleteNote(note: Note)

}