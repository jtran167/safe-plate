package com.safeplate.data.instances;

import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.safeplate.data.DataSingleton;
import com.safeplate.data.enums.WhyCreateAccountFailed;
import com.safeplate.data.interfaces.IUser;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class User implements IUser {

    public boolean accountExists(String email) {
        Cursor cursor = null;
        String query = "select user_name" +
                "from users" +
                "where email = " + email;
        boolean response = false;
        try {
            cursor = DataSingleton.instance.getDB().rawQuery(query, null);
            String dbUserName = cursor.getString(0);
            if (dbUserName != null) {
                response = true;
            }
        } catch (Exception ex) {
            Log.i("info", ex.getMessage());
        }
        return response;
    }


    public com.safeplate.data.models.User login(String username, String password) {
        Cursor cursor = null;
        String query = "select * " +
                "from users " +
                "where user_name = '" + username + "';";
        com.safeplate.data.models.User response = null;
        try {
            cursor = DataSingleton.instance.getDB().rawQuery(query, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String dbUserName = cursor.getString(1);
                String dbPassword = cursor.getString(2);
                String firstName = cursor.getString(3);
                String lastName = cursor.getString(4);
                String email = cursor.getString(5);
                String phone = cursor.getString(6);
                String passwordHashCode = this.hashPassword(password);
                if (dbUserName.equals(username) && dbPassword.equals(passwordHashCode)) {
                    response = new com.safeplate.data.models.User(
                            id,
                            dbUserName,
                            dbPassword,
                            firstName,
                            lastName,
                            email,
                            phone
                            );
                }
            }
        } catch (Exception ex) {
            Log.i("info", ex.getMessage());
        }
        return response;
    }

    public void resetPassword(String username, String email, String password) {
        String passwordHashcode = this.hashPassword(password);
        String query = "update users " +
                "set password = " + passwordHashcode +
                "where user_name = " + username +
                "and email = " + email;
        try {
            DataSingleton.instance.getDB().execSQL(query);
        } catch (Exception ex) {
            Log.i("info", ex.getMessage());
        }
    }

    @Override
    public com.safeplate.data.requests.User.CreateAccountResponse createAccount(String username, String password, String firstName, String lastName,
                                                                                         String email, String phone) {
        com.safeplate.data.requests.User.CreateAccountResponse response = new com.safeplate.data.requests.User.CreateAccountResponse();
        String passwordHashcode = this.hashPassword(password);
        String query = "insert into users " +
                "(" + com.safeplate.data.models.User.userNameColumn + ", " +
                com.safeplate.data.models.User.passwordColumn + ", " +
                com.safeplate.data.models.User.firstNameColumn + ", " +
                com.safeplate.data.models.User.lastNameColumn + ", " +
                com.safeplate.data.models.User.emailColumn + ", " +
                com.safeplate.data.models.User.phoneColumn + ", " +
                "values (" +
                "'" + username + "', " +
                "'" + passwordHashcode + "', " +
                "'" + firstName + "', " +
                "'" + lastName + "', " +
                "'" + email + "', " +
                "'" + phone + "'');";
        try {
            DataSingleton.instance.getDB().execSQL(query);
        } catch (SQLiteConstraintException ex) {
            response.setErrorCode(WhyCreateAccountFailed.UsernameAlreadyExists);
        } catch (Exception ex) {
            Log.i("info", ex.getMessage());
        }
        return response;
    }

    @Override
    public void editProfile(int id, String firstName, String lastName, String phone) {
        String query = "update users " +
                "set " + com.safeplate.data.models.User.firstNameColumn + " = '" + firstName + "', " +
                com.safeplate.data.models.User.lastNameColumn + " = '" + lastName + "', " +
                com.safeplate.data.models.User.phoneColumn + " = '" + phone + "', " +
                " where " + com.safeplate.data.models.User.idColumn + " = " + id;

        try {
            DataSingleton.instance.getDB().execSQL(query);
        } catch (Exception ex){
            Log.i("info", ex.getMessage());
        }
    }

    @Override
    public com.safeplate.data.models.User fetchByUserName(String username) {
        Cursor cursor = null;
        String query = "select * " +
                "from users " +
                "where user_name = '" + username + "';";
        com.safeplate.data.models.User response = null;

        try {
            cursor = DataSingleton.instance.getDB().rawQuery(query, null);
            while(cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String dbUserName = cursor.getString(1);
                String dbPassword = cursor.getString(2);
                String firstName = cursor.getString(3);
                String lastName = cursor.getString(4);
                String email = cursor.getString(5);
                String phone = cursor.getString(6);
                response = new com.safeplate.data.models.User(
                        id,
                        dbUserName,
                        dbPassword,
                        firstName,
                        lastName,
                        email,
                        phone
                );
            }
        } catch (Exception ex){
            Log.i("info", ex.getMessage());
        }
        return response;
    }

    private String hashPassword(String password) {
        String response = "";
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            response = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException ex) {
            Log.i("info", ex.getMessage());
        }
        return response;
    }
}
