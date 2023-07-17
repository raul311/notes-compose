package com.raul311.notescompose.core.datastore.notes

import javax.inject.Inject

class NoteDatabaseImpl @Inject constructor(
    private val noteDao: NoteDao
) {}