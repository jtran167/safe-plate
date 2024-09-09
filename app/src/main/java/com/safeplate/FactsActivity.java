package com.safeplate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FactsActivity extends AppCompatActivity {
    private ImageButton homeButton;
    private ImageButton accountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        this.homeButton = findViewById(R.id.homeButton_facts);
        this.homeButton.setOnClickListener(view -> {
            this.GoToMainActivity();
        });

        this.accountButton = findViewById(R.id.accountButton_facts);
        this.accountButton.setOnClickListener(view -> {
            this.GoToAccountActivity();
        });

    }

    private void GoToMainActivity() {
        Intent myIntent = new Intent(FactsActivity.this, MainActivity.class);
        myIntent.putExtra("", "");
        FactsActivity.this.startActivity(myIntent);
    }

    private void GoToAccountActivity() {
        Intent myIntent = new Intent(FactsActivity.this, AccountActivity.class);
        myIntent.putExtra("", "");
        FactsActivity.this.startActivity(myIntent);
    }
}
