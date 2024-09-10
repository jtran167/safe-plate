package com.safeplate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.safeplate.data.Interactor;
import com.safeplate.data.models.FoodDiary;
import com.safeplate.data.models.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageButton userButton;
    private ImageButton adviceButton;
    private ImageButton factsButton;
    private ImageButton treatmentButton;
    private RecyclerView foodDiaryRecyclerView;
    private List<FoodDiary> foodDiaryList;
    private FoodDiaryListAdapter foodDiaryListAdapter;
    private String[] foodDiaryExperienceOptions = { "Good", "Bad"};
    private Spinner foodDiaryExperienceSpinner;
    private EditText addFoodDiaryEditText;
    private Button addFoodDiaryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User loggedInUser = LoggedInUser.getInstance();

        this.addFoodDiaryEditText = findViewById(R.id.addNewFoodDiaryNameEditText);
        this.addFoodDiaryButton = findViewById(R.id.addNewFoodDiaryButton);
        this.addFoodDiaryButton.setOnClickListener(view -> {
            this.addFoodDiary();
        });
        this.foodDiaryRecyclerView = findViewById(R.id.foodDiaryRecyclerView);
        this.foodDiaryExperienceSpinner = findViewById(R.id.foodDiaryExperienceSpinner);
        ArrayAdapter<String> foodDiaryExperienceAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, foodDiaryExperienceOptions);
        foodDiaryExperienceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.foodDiaryExperienceSpinner.setAdapter(foodDiaryExperienceAdapter);
        this.foodDiaryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.foodDiaryList = Interactor.getFoodDiaryInstance().getFoodDiariesByUserId(loggedInUser.getId());
        this.foodDiaryListAdapter = new FoodDiaryListAdapter(this.foodDiaryList != null ? this.foodDiaryList : new ArrayList<>(),
         this::removeItem);
        this.foodDiaryRecyclerView.setAdapter(foodDiaryListAdapter);

        this.userButton = findViewById(R.id.accountButton);
        this.userButton.setOnClickListener(view -> {
            GoToAccountActivity();
        });

        this.adviceButton = findViewById(R.id.adviceButton);
        this.adviceButton.setOnClickListener(view -> {
            goToAdviceActivity();
        });
        this.treatmentButton = findViewById(R.id.treatmentButton);
        this.treatmentButton.setOnClickListener(view -> {
            goToTreatmentActivity();
        });

        this.factsButton = findViewById(R.id.factsButton);
        this.factsButton.setOnClickListener(view -> {
            goToFactsActivity();
        });
    }

    private void addFoodDiary() {
        int userId = LoggedInUser.getInstance().getId();
        String food = this.addFoodDiaryEditText.getText().toString();
        if (!food.isEmpty()) {
            FoodDiary foodDiaryToInsert = new FoodDiary();
            foodDiaryToInsert.setUserId(userId);
            foodDiaryToInsert.setFood(food);
            foodDiaryToInsert.setExperience(this.foodDiaryExperienceSpinner.getSelectedItem().toString());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Format the date and time
            String formattedTimestamp = LocalDateTime.now().format(formatter);
            foodDiaryToInsert.setTimeStamp(formattedTimestamp);
            Interactor.getFoodDiaryInstance().InsertFoodDiary(foodDiaryToInsert);
            this.foodDiaryList.clear();
            this.foodDiaryList.addAll(Interactor.getFoodDiaryInstance().getFoodDiariesByUserId(userId));
            this.foodDiaryListAdapter.notifyDataSetChanged();
        }
    }

    private void removeItem(int position) {
        try {
            FoodDiary foodDiaryToRemove = this.foodDiaryList.get(position);
            this.foodDiaryList.remove(position);
            this.foodDiaryListAdapter.notifyItemRemoved(position);
            Interactor.getFoodDiaryInstance().DeleteFoodDiary(foodDiaryToRemove);
        }
        catch (Exception ex) {
            Log.i("info", ex.getMessage());
        }
    }

    private void GoToAccountActivity() {
        Intent myIntent = new Intent(MainActivity.this, AccountActivity.class);
        myIntent.putExtra("", "");
        MainActivity.this.startActivity(myIntent);
    }

    private void goToTreatmentActivity() {
        Intent myIntent = new Intent(MainActivity.this, TreatmentActivity.class);
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
