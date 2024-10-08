package com.safeplate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {
    private ImageButton homeButton;
    private ImageButton accountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        this.homeButton = findViewById(R.id.homeButton_info);
        this.homeButton.setOnClickListener(view -> {
            this.GoToMainActivity();
        });

        this.accountButton = findViewById(R.id.accountButton_info);
        this.accountButton.setOnClickListener(view -> {
            this.GoToAccountActivity();
        });

    }

    private void GoToMainActivity() {
        Intent myIntent = new Intent(InfoActivity.this, MainActivity.class);
        myIntent.putExtra("", "");
        InfoActivity.this.startActivity(myIntent);
    }

    private void GoToAccountActivity() {
        Intent myIntent = new Intent(InfoActivity.this, AccountActivity.class);
        myIntent.putExtra("", "");
        InfoActivity.this.startActivity(myIntent);
    }
}
