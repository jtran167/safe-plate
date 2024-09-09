package com.safeplate.data.interfaces;

import com.safeplate.data.models.Allergy;

import java.util.ArrayList;

public interface IAllergy {
    ArrayList<Allergy> fetchAllergiesByUserId(int userId);
    boolean DeleteAllergyByAllergyId(int allergyId);
    boolean InsertAllergyForUserId(int userId, String food);
}
