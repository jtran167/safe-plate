package com.safeplate.data.instances;

import android.database.Cursor;
import android.util.Log;

import com.safeplate.data.DataSingleton;
import com.safeplate.data.interfaces.IFoodDiary;

import java.util.ArrayList;

public class FoodDiary implements IFoodDiary {
    @Override
    public boolean InsertFoodDiary(com.safeplate.data.models.FoodDiary foodDiary) {
        String query = "insert into food_diaries " +
                "(" + com.safeplate.data.models.FoodDiary.userIdColumn + ", " +
                com.safeplate.data.models.FoodDiary.foodColumn + ", " +
                com.safeplate.data.models.FoodDiary.experienceColumn + ", " +
                com.safeplate.data.models.FoodDiary.timeStampColumn + ") values (" +
                foodDiary.getUserId() + ", " +
                "'" + foodDiary.getFood() + "'," +
                "'" + foodDiary.getExperience() + "'," +
                "'" + foodDiary.getTimeStamp() + "'" + ");";
        try {
            DataSingleton.instance.getDB().execSQL(query);
        } catch (Exception ex) {
            Log.i("info", ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<com.safeplate.data.models.FoodDiary> getFoodDiariesByUserId(int userId) {
        Cursor cursor = null;
        String query = "select *" +
                " from food_diaries" +
                " where userId = " + userId;
        ArrayList<com.safeplate.data.models.FoodDiary> response = new ArrayList<>();
        try {
            cursor = DataSingleton.instance.getDB().rawQuery(query, null);
            while (cursor.moveToNext()) {
                response.add(new com.safeplate.data.models.FoodDiary(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            }
        } catch (Exception ex) {
            Log.i("info", ex.getMessage());
        }
        return response;
    }

    @Override
    public boolean DeleteFoodDiary(com.safeplate.data.models.FoodDiary foodDiary) {
        String query = "delete from food_diaries "
                + " where " + com.safeplate.data.models.FoodDiary.idColumn
                + " = " + foodDiary.getId() + ";";
        try {
            DataSingleton.instance.getDB().execSQL(query);
        } catch (Exception ex) {
            Log.i("info", ex.getMessage());
            return false;
        }
        return true;
    }
}
