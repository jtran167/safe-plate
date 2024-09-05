package data

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import data.models.User

private const val DATABASE_NAME = "safeplate_db"
private const val DATABASE_VERSION = 1;

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        try {
            User.createTable(db)  // Create table when database is created
        }
        catch (ex: SQLException) {
            Log.i("info", "In DataBaseHelper class onCreate method");
            ex.message?.let { Log.i("info", it) };
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {     // Create a new version
    }

    fun insertUser(username: String,
                   password: String,
                   firstName: String,
                   lastName: String,
                   email: String,
                   phone: String) {
        val db = this.writableDatabase
        db.insert
    }
}
