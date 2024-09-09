package com.safeplate.data.interfaces;

import com.safeplate.data.models.FoodDiary;

import java.util.ArrayList;

public interface IFoodDiary {
    boolean InsertFoodDiary(FoodDiary foodDiary);
    ArrayList<com.safeplate.data.models.FoodDiary> getFoodDiariesByUserId(int userId);
    boolean DeleteFoodDiary(FoodDiary foodDiary);
}
