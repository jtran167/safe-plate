package com.safeplate.data.models;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FoodDiary {
    static public final String tableName = "food_diaries";
    static public final String idColumn = "id";
    static public final String userIdColumn = "userId";
    static public final String foodColumn = "food";
    static public final String experienceColumn = "experience";
    static public final String timeStampColumn = "timestamp";

    static public final void CreateTable(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + tableName + "(" +
                idColumn + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                userIdColumn + " INTEGER, " +
                foodColumn + " TEXT, " +
                experienceColumn + " TEXT, " +
                timeStampColumn + " TEXT)";

        try {
            db.execSQL(createTableQuery);
        } catch (SQLException ex) {
            Log.i("info" , ex.getMessage());
        }
    }

    private int id;
    private int userId;
    private String food;
    private String experience;
    private String timeStamp;

    public FoodDiary() {}

    public FoodDiary(int id, int userId, String food, String experience, String timestamp) {
        this.id = id;
        this.userId = userId;
        this.food = food;
        this.experience = experience;
        this.timeStamp = timestamp;
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

    public String getExperience() { return this.experience;}

    public void setExperience(String experience) { this.experience = experience;}

    public String getTimeStamp() { return this.timeStamp;}

    public void setTimeStamp(String timeStamp) { this.timeStamp = timeStamp;}
}
