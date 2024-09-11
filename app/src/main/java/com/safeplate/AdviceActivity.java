package com.safeplate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AdviceActivity extends AppCompatActivity {
    private ImageButton homeButton;
    private ImageButton accountButton;
    private ImageButton mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        this.homeButton = findViewById(R.id.homeButton_advice);
        this.homeButton.setOnClickListener(view -> {
            this.GoToMainActivity();
        });

        this.accountButton = findViewById(R.id.accountButton_advice);
        this.accountButton.setOnClickListener(view -> {
            this.GoToAccountActivity();
        });

        this.mapButton = findViewById(R.id.mapButton_advice);
        this.mapButton.setOnClickListener(view -> {
            this.goToMapActivity();
        });
    }

    private void GoToMainActivity() {
        Intent myIntent = new Intent(AdviceActivity.this, MainActivity.class);
        myIntent.putExtra("", "");
        AdviceActivity.this.startActivity(myIntent);
    }

    private void GoToAccountActivity() {
        Intent myIntent = new Intent(AdviceActivity.this, AccountActivity.class);
        myIntent.putExtra("", "");
        AdviceActivity.this.startActivity(myIntent);
    }

    private void goToMapActivity() {
        Intent myIntent = new Intent(AdviceActivity.this, MapActivity.class);
        myIntent.putExtra("", "");
        AdviceActivity.this.startActivity(myIntent);
    }
}
