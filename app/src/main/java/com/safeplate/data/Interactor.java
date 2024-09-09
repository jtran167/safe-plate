package com.safeplate.data;

import com.safeplate.data.instances.Allergy;
import com.safeplate.data.instances.FoodDiary;
import com.safeplate.data.instances.User;
import com.safeplate.data.interfaces.IAllergy;
import com.safeplate.data.interfaces.IFoodDiary;
import com.safeplate.data.interfaces.IUser;

public final class Interactor {
    static private Interactor instance;
    static private IUser User;
    static private IAllergy Allergy;
    static private IFoodDiary FoodDiary;

    private Interactor() {
        this.User = new User();
        this.Allergy = new Allergy();
        this.FoodDiary = new FoodDiary();
    }

    static private Interactor getInstance() {
        if (instance == null) {
            instance = new Interactor();
        }
        return instance;
    }

    static public IUser getUserInstance() {
        if (instance == null) {
            getInstance();
        }
        return User;
    }

    static public IAllergy getAllergyInstance () {
        if (instance == null) {
            getInstance();
        }
        return Allergy;
    }

    static public IFoodDiary getFoodDiaryInstance () {
        if (instance == null) {
            getInstance();
        }
        return FoodDiary;
    }
}
