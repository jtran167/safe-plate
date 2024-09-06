package com.safeplate.data.models;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class User {
    static public final String tableName = "users";
    static public final String idColumn = "id";
    static public final String userNameColumn = "user_name";
    static public final String passwordColumn = "password";
    static public final String firstNameColumn = "first_name";
    static public final String lastNameColumn = "last_name";
    static public final String emailColumn = "email";
    static public final String phoneColumn = "phone";

    static public final void CreateTable(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + tableName + "(" +
                idColumn + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                userNameColumn + " TEXT UNIQUE, " +
                passwordColumn + " TEXT, " +
                firstNameColumn + " TEXT, " +
                lastNameColumn + " TEXT, " +
                emailColumn + " TEXT, " +
                phoneColumn + " TEXT)";

        try {
            db.execSQL(createTableQuery);
        } catch (SQLException ex) {
            Log.i("info" , ex.getMessage());
        }
    }

    private int id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public User() {}

    public User(int id, String userName, String password, String firstName, String lastName,
                String email, String phone) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName() {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
