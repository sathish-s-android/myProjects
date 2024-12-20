package com.myapp.db_encryption

import android.content.Context
import com.myapp.db_encryption.NotesReaderContract.SQL_CREATE_ENTRIES
import com.myapp.db_encryption.NotesReaderContract.SQL_DELETE_ENTRIES
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteOpenHelper

class MyNotesDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    init {
        SQLiteDatabase.loadLibs(context)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    private var db: SQLiteDatabase? = null
    fun getDatabase(): SQLiteDatabase {
        if (db == null || !db!!.isOpen || db!!.isReadOnly) {
            db = getReadableDatabase(getPassword())
        }
        return db!!
    }


    private fun getPassword():String = "123456789098uytrdsdfghjkl9876"

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MyNotesReader.db"
        private lateinit var instance:MyNotesDbHelper
        fun getInstance(context: Context):MyNotesDbHelper{
            return if (::instance.isInitialized){
                instance
            }else{
                instance = MyNotesDbHelper(context)

                instance
            }
        }
    }
}



//import android.content.Context
//import com.myapp.db_encryption.NotesReaderContract.SQL_CREATE_ENTRIES
//import com.myapp.db_encryption.NotesReaderContract.SQL_DELETE_ENTRIES
//import net.zetetic.database.sqlcipher.SQLiteDatabase
//import net.zetetic.database.sqlcipher.SQLiteOpenHelper
//
//class MyNotesDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, getPassword(),null,DATABASE_VERSION,0,null,null,false ) {
//
//    init {
//        System.loadLibrary("sqlcipher")
//    }
//
//    override fun onCreate(db: SQLiteDatabase) {
//        db.execSQL(SQL_CREATE_ENTRIES)
//    }
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        db.execSQL(SQL_DELETE_ENTRIES)
//        onCreate(db)
//    }
//    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        onUpgrade(db, oldVersion, newVersion)
//    }
//
//    private var db: SQLiteDatabase? = null
//    fun getDatabase(): SQLiteDatabase {
//        if (db == null || !db!!.isOpen || db!!.isReadOnly) {
//            db = readableDatabase
//        }
//        return db!!
//    }
//
//
//    companion object {
//        // If you change the database schema, you must increment the database version.
//        const val DATABASE_VERSION = 1
//        const val DATABASE_NAME = "MyNotesReader.db"
//        private lateinit var instance:MyNotesDbHelper
//        fun getInstance(context: Context):MyNotesDbHelper{
//            return if (::instance.isInitialized){
//                instance
//            }else{
//                instance = MyNotesDbHelper(context)
//
//                instance
//            }
//        }
//    }
//}
//
//fun getPassword():String = "123456789098uytrdsdfghjkl9876"