package com.safeplate.data.models;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Allergy {
    static public final String tableName = "allergies";
    static public final String idColumn = "id";
    static public final String userIdColumn = "userId";
    static public final String foodColumn = "food";

    static public final void CreateTable(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + tableName + "(" +
                idColumn + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                userIdColumn + " INTEGER, " +
                foodColumn + " TEXT, " +
                "UNIQUE (" + userIdColumn + ", " + foodColumn + "))";

        try {
            db.execSQL(createTableQuery);
        } catch (SQLException ex) {
            Log.i("info" , ex.getMessage());
        }
    }

    private int id;
    private int userId;
    private String food;

    public Allergy() {}

    public Allergy(int id, int userId, String food) {
        this.id = id;
        this.userId = userId;
        this.food = food;
    }

    public int getId() {
        return this.id;
    }

    public int getUserId() { return this.userId;}

    public String getFood() { return this.food; }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) { this.userId = userId; };

    public void setFood(String food) { this.food = food;}
}
