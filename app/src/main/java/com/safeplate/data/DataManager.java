package com.safeplate.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.safeplate.data.models.Allergy;
import com.safeplate.data.models.FoodDiary;
import com.safeplate.data.models.User;

public class DataManager {
    static private SQLiteDatabase db;

    public DataManager(Context context) {
        DataBaseHelper helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    static public SQLiteDatabase getDB() {
        return db;
    }

    private class DataBaseHelper extends SQLiteOpenHelper {

        static private final String dbName = "safeplate";
        static private final int dbVersion = 2;

        public DataBaseHelper(Context context) {
            super(context, dbName, null, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                User.CreateTable(db);
                Allergy.CreateTable(db);
                FoodDiary.CreateTable(db);
            } catch (SQLException ex) {
                Log.i("info", "In DataBaseHelper class onCreate method");
                Log.i("info", ex.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // none
        }
    }
}
