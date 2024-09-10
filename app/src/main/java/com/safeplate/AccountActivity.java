package com.safeplate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.safeplate.data.DataManager;
import com.safeplate.data.DataSingleton;
import com.safeplate.data.Interactor;
import com.safeplate.data.models.Allergy;
import com.safeplate.data.models.User;
import java.util.List;

public class AccountActivity extends AppCompatActivity {
    private List<Allergy> allergyList;
    private AllergyListAdapter listAdapter;
    private EditText addAllergyEditText;
    private Button addAllergyButton;
    private TextView usernameTextView;
    private TextView emailTextView;
    private TextView userFullNameTextView;
    private ImageButton homeButton;
    private ImageButton aboutUsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataManager dataManager = new DataManager(this);
        DataSingleton.SetInstance(dataManager);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        this.addAllergyEditText = findViewById(R.id.addNewAllergyEditText);
        this.addAllergyButton = findViewById(R.id.addNewAllergyButton);
        this.usernameTextView = findViewById(R.id.userNameTextView);
        this.emailTextView = findViewById(R.id.emailTextView);
        this.userFullNameTextView = findViewById(R.id.userFullNameTextView);
        this.homeButton = findViewById(R.id.homeButton);
        this.aboutUsButton = findViewById(R.id.aboutUsButton);

        User loggedInUser = LoggedInUser.getInstance();
        this.usernameTextView.setText(loggedInUser.getUserName());
        this.emailTextView.setText(loggedInUser.getEmail());
        this.userFullNameTextView.setText(String.format("%s %s", loggedInUser.getFirstName(), loggedInUser.getLastName()));

        RecyclerView recyclerView = findViewById(R.id.listOfAllergies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int userId = LoggedInUser.getInstance().getId();

        allergyList = Interactor.getAllergyInstance().fetchAllergiesByUserId(userId);
        listAdapter = new AllergyListAdapter(allergyList, this::removeItem);
        recyclerView.setAdapter(listAdapter);

        this.addAllergyButton.setOnClickListener(v -> {
            this.addAllergy();
        });

        this.aboutUsButton.setOnClickListener(view -> {
            this.goToAboutUsActivity();
        });

        this.homeButton.setOnClickListener(view -> {
            this.GoToMainActivity();
        });
    }

    private void addAllergy() {
        int userId = LoggedInUser.getInstance().getId();
        String food = this.addAllergyEditText.getText().toString();
        if (!food.isEmpty()) {
            Interactor.getAllergyInstance().InsertAllergyForUserId(userId, food);
            allergyList.clear();
            allergyList.addAll(Interactor.getAllergyInstance().fetchAllergiesByUserId(userId));
            listAdapter.notifyDataSetChanged();
        }
    }

    private void removeItem(int position) {
        try {
            Allergy allergyToRemove = allergyList.get(position);
            allergyList.remove(position);
            listAdapter.notifyItemRemoved(position);
            Interactor.getAllergyInstance().DeleteAllergyByAllergyId(allergyToRemove.getId());
        }
        catch (Exception ex) {
            Log.i("info", ex.getMessage());
        }
    }

    private void GoToMainActivity() {
        Intent myIntent = new Intent(AccountActivity.this, MainActivity.class);
        myIntent.putExtra("", "");
        AccountActivity.this.startActivity(myIntent);
    }

    private void goToAboutUsActivity() {
        Intent myIntent = new Intent(AccountActivity.this, InfoActivity.class);
        myIntent.putExtra("", "");
        AccountActivity.this.startActivity(myIntent);
    }
}
