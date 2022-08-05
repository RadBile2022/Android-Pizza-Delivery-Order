package com.example.ujkradar.utilities

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.example.ujkradar.utilities.DatabaseContract.NoteColumn.Companion.NOMER_MEJA
import com.example.ujkradar.utilities.DatabaseContract.NoteColumn.Companion.TABLE_NAME
import com.example.ujkradar.utilities.DatabaseContract.NoteColumn.Companion._ID

class PesananHelper(context: Context) {
    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: PesananHelper? = null

        fun getInstance(context: Context): PesananHelper {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PesananHelper(context)
            }
        }
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }
    fun close() {
        dataBaseHelper.close()
        if (database.isOpen)
            database.close()
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }
    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC")
    }
    fun queryByNoMeja(NoMeja: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$NOMER_MEJA = ?",
            arrayOf(NoMeja),
            null,
            null,
            null,
            null)
    }
    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
    }
}