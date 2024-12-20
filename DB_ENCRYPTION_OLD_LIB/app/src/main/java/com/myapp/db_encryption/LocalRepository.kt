package com.myapp.db_encryption

import android.content.ContentValues
import android.content.Context

private var id = 0

class LocalRepository(context: Context) {

    private val db = MyNotesDbHelper.getInstance(context).getDatabase()

    suspend fun saveNotes(notes: Notes){
        val values = ContentValues().apply {
            put(NotesReaderContract.NotesEntry.ID, id++)
            put(NotesReaderContract.NotesEntry.COLUMN_NAME_TITLE, notes.title)
            put(NotesReaderContract.NotesEntry.COLUMN_NAME_DETAIL, notes.content)
        }
        val newRowId = db.insert(NotesReaderContract.NotesEntry.TABLE_NAME, null, values)
    }

    suspend fun getNotes():List<Notes>{
        val sortOrder = "${NotesReaderContract.NotesEntry.ID} DESC"
        val cursor = db.query(
            NotesReaderContract.NotesEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            sortOrder
        )


        val notes = mutableListOf<Notes>()
        with(cursor) {
            while (moveToNext()) {
                val title = getString(getColumnIndexOrThrow(NotesReaderContract.NotesEntry.COLUMN_NAME_TITLE))
                val content = getString(getColumnIndexOrThrow(NotesReaderContract.NotesEntry.COLUMN_NAME_DETAIL))
                notes.add(
                    Notes(
                        title,
                        content
                    ))
            }
        }
        cursor.close()

        return notes
    }
}