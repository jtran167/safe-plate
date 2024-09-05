package data.models

import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log

object User {
    const val TABLE_NAME = "users"
    const val ID_COLUMN = "id"
    const val USER_NAME_COLUMN = "user_name"
    const val PASSWORD_COLUMN = "password"
    const val FIRST_NAME_COLUMN = "first_name"
    const val LAST_NAME_COLUMN = "last_name"
    const val EMAIL_COLUMN = "email"
    const val PHONE_COLUMN = "phone"

    fun createTable(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT, 
                $USER_NAME_COLUMN TEXT UNIQUE, 
                $PASSWORD_COLUMN TEXT, 
                $FIRST_NAME_COLUMN TEXT, 
                $LAST_NAME_COLUMN TEXT, 
                $EMAIL_COLUMN TEXT, 
                $PHONE_COLUMN TEXT
            )
        """.trimIndent()

        try {
            db.execSQL(createTableQuery)
        } catch (ex: SQLException) {
            Log.i("info", ex.message ?: "Error creating table")
        }
    }

    fun insertUser(username: String,
                   password: String,
                   firstName: String,
                   lastName: String,
                   email: String,
                   phone: String) {
        val db = this.db
        db.insert
    }
}