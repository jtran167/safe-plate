package com.safeplate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.safeplate.data.models.FoodDiary;

import java.util.List;

public class FoodDiaryListAdapter extends RecyclerView.Adapter<FoodDiaryListAdapter.ViewHolder> {

    private final List<FoodDiary> items;
    private final OnItemRemoveListener onItemRemoveListener;

    public FoodDiaryListAdapter(List<FoodDiary> items, OnItemRemoveListener onItemRemoveListener) {
        this.items = items;
        this.onItemRemoveListener = onItemRemoveListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_diary_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDiary item = items.get(position);
        holder.foodDiaryFoodName.setText(item.getFood());
        holder.foodDiaryExperience.setText(item.getExperience());
        holder.foodDiaryDate.setText(item.getTimeStamp());
        holder.removeFoodDiaryButton.setOnClickListener(v -> {
            onItemRemoveListener.onItemRemove(position);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemRemoveListener {
        void onItemRemove(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodDiaryFoodName;
        TextView foodDiaryExperience;
        TextView foodDiaryDate;
        Button removeFoodDiaryButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodDiaryFoodName = itemView.findViewById(R.id.foodDiary_foodName);
            foodDiaryExperience = itemView.findViewById(R.id.foodDiary_experience);
            foodDiaryDate = itemView.findViewById(R.id.foodDiary_date);
            removeFoodDiaryButton = itemView.findViewById(R.id.removeFoodDiaryButton);
        }
    }
}
