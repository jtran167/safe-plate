package com.safeplate.login

import androidx.appcompat.app.AppCompatActivity
import data.DatabaseHelper

class LoginActivity : AppCompatActivity() {
    lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}