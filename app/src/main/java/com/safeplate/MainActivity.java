package com.safeplate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageButton userButton;
    private ImageButton adviceButton;
    private ImageButton factsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.userButton = findViewById(R.id.accountButton);
        this.userButton.setOnClickListener(view -> {
            GoToAccountActivity();
        });

        this.adviceButton = findViewById(R.id.adviceButton);
        this.adviceButton.setOnClickListener(view -> {
            goToAdviceActivity();
        });

        this.factsButton = findViewById(R.id.factsButton);
        this.factsButton.setOnClickListener(view -> {
            goToFactsActivity();
        });
    }

    private void GoToAccountActivity() {
        Intent myIntent = new Intent(MainActivity.this, AccountActivity.class);
        myIntent.putExtra("", "");
        MainActivity.this.startActivity(myIntent);
    }

    private void goToAdviceActivity() {
        Intent myIntent = new Intent(MainActivity.this, AdviceActivity.class);
        myIntent.putExtra("", "");
        MainActivity.this.startActivity(myIntent);
    }

    private void goToFactsActivity() {
        Intent myIntent = new Intent(MainActivity.this, FactsActivity.class);
        myIntent.putExtra("", "");
        MainActivity.this.startActivity(myIntent);
    }
}
