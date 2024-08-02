package com.example.b07demosummer2024;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Collection> itemList;

    public ItemAdapter(List<Collection> itemList) { 
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_adapater, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Collection collection = itemList.get(position);
        holder.textViewName.setText(collection.getName());
        holder.textViewLotNumber.setText(collection.getLotNumber());
        holder.textViewCategory.setText(collection.getCategory());
        holder.textViewPeriod.setText(collection.getPeriod());
        holder.textViewDescription.setText(collection.getDescription());
        holder.textViewMediaUrl.setText(collection.getMediaUrl());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewLotNumber, textViewCategory, textViewPeriod, textViewDescription, textViewMediaUrl;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLotNumber = itemView.findViewById(R.id.textViewLotNumber);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewPeriod = itemView.findViewById(R.id.textViewPeriod);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            //textViewMediaUrl = itemView.findViewById(R.id.textViewMediaUrl);
        }
    }
}
