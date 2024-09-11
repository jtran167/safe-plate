package com.safeplate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.safeplate.data.DataManager;
import com.safeplate.data.DataSingleton;
import com.safeplate.data.Interactor;

public class LoginActivity extends AppCompatActivity {
    private TextView loginErrorMessage;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataManager dataManager = new DataManager(this);
        DataSingleton.SetInstance(dataManager);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.userNameEditText = findViewById(R.id.userNameEditText);
        this.passwordEditText = findViewById(R.id.passwordEditText);
        this.loginButton = findViewById(R.id.loginButton);
        this.createAccountButton = findViewById(R.id.createAccountButton);
        this.loginErrorMessage = findViewById(R.id.loginErrorMessage);

        this.loginButton.setOnClickListener(
                v -> {
                    com.safeplate.data.models.User user = this.login();
                    if (user != null) {
                        LoggedInUser.setInstance(user);
                        this.moveToMainActivity();
                    }
                    else {
                        this.loginErrorMessage.setVisibility(TextView.VISIBLE);
                        this.loginErrorMessage.setText("Invalid username/password");
                        Toast.makeText(this, "Invalid username/password", Toast.LENGTH_LONG);
                    }
                }
        );

        this.createAccountButton.setOnClickListener(v -> {
            this.moveToCreateAccountActivity();
        });
    }

    private com.safeplate.data.models.User login() {
        String username = this.userNameEditText.getText().toString();
        String password = this.passwordEditText.getText().toString();

        com.safeplate.data.models.User response = Interactor.getUserInstance().login(username, password);
        return response;
    }

    private void moveToMainActivity() {
        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
        myIntent.putExtra("", "");
        LoginActivity.this.startActivity(myIntent);
    }

    private void moveToCreateAccountActivity() {
        Intent myIntent = new Intent(LoginActivity.this, CreateAccountActivity.class);
        myIntent.putExtra("", "");
        LoginActivity.this.startActivity(myIntent);
    }
}