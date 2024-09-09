package com.safeplate.data.instances;

import android.database.Cursor;
import android.util.Log;

import com.safeplate.data.DataSingleton;
import com.safeplate.data.interfaces.IAllergy;

import java.util.ArrayList;

public class Allergy implements IAllergy {

    @Override
    public ArrayList<com.safeplate.data.models.Allergy> fetchAllergiesByUserId(int userId) {
        Cursor cursor = null;
        String query = "select * " +
                "from allergies " +
                "where userId = " + userId;
        ArrayList<com.safeplate.data.models.Allergy> response = new ArrayList<>();
        try {
            cursor = DataSingleton.instance.getDB().rawQuery(query, null);
            while (cursor.moveToNext()) {
                response.add(new com.safeplate.data.models.Allergy(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2)
                ));
            }
        } catch (Exception ex) {
            Log.i("info", ex.getMessage());
        }
        return response;
    }

    @Override
    public boolean DeleteAllergyByAllergyId(int allergyId) {
        String query = "delete from allergies "
                + " where " + com.safeplate.data.models.Allergy.idColumn
                + " = " + allergyId + ";";
        try {
            DataSingleton.instance.getDB().execSQL(query);
        } catch (Exception ex) {
            Log.i("info", ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean InsertAllergyForUserId(int userId, String food) {
        String query = "insert into allergies " +
                "(" + com.safeplate.data.models.Allergy.userIdColumn + ", " +
                com.safeplate.data.models.Allergy.foodColumn + ") values (" +
                userId + ", '" + food + "');";
        try {
            DataSingleton.instance.getDB().execSQL(query);
        } catch (Exception ex) {
            Log.i("info", ex.getMessage());
            return false;
        }
        return true;
    }
}
