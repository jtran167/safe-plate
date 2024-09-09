package com.safeplate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.safeplate.data.models.Allergy;

import java.util.List;

public class AllergyListAdapter extends RecyclerView.Adapter<AllergyListAdapter.ViewHolder> {

    private final List<Allergy> items;
    private final OnItemRemoveListener onItemRemoveListener;

    public AllergyListAdapter(List<Allergy> items, OnItemRemoveListener onItemRemoveListener) {
        this.items = items;
        this.onItemRemoveListener = onItemRemoveListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Allergy item = items.get(position);
        holder.textView.setText(item.getFood());
        holder.removeButton.setOnClickListener(v -> {
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
        TextView textView;
        Button removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
            removeButton = itemView.findViewById(R.id.remove_button);
        }
    }
}
