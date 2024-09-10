package com.safeplate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class TreatmentActivity extends AppCompatActivity {
    private ImageButton homeButton;
    private ImageButton accountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);

        this.homeButton = findViewById(R.id.homeButton_treatment);
        this.homeButton.setOnClickListener(view -> {
            this.GoToMainActivity();
        });

        this.accountButton = findViewById(R.id.accountButton_treatment);
        this.accountButton.setOnClickListener(view -> {
            this.GoToAccountActivity();
        });

    }

    private void GoToMainActivity() {
        Intent myIntent = new Intent(TreatmentActivity.this, MainActivity.class);
        myIntent.putExtra("", "");
        TreatmentActivity.this.startActivity(myIntent);
    }

    private void GoToAccountActivity() {
        Intent myIntent = new Intent(TreatmentActivity.this, AccountActivity.class);
        myIntent.putExtra("", "");
        TreatmentActivity.this.startActivity(myIntent);
    }
}
