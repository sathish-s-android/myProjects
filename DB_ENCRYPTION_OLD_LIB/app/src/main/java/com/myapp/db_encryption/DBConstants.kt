package com.myapp.db_encryption

import android.provider.BaseColumns

object NotesReaderContract {
    // Table contents are grouped together in an anonymous object.
    object NotesEntry {
        const val TABLE_NAME = "my_notes"
        const val ID = "id"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_DETAIL = "detail"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${NotesEntry.TABLE_NAME} (" +
                "${NotesEntry.ID} INTEGER PRIMARY KEY," +
                "${NotesEntry.COLUMN_NAME_TITLE} TEXT," +
                "${NotesEntry.COLUMN_NAME_DETAIL} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${NotesEntry.TABLE_NAME}"
}



