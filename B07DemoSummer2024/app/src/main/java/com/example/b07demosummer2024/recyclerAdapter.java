package com.example.b07demosummer2024;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.b07demosummer2024.Collection;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<com.example.b07demosummer2024.Collection> collectionList;
    private ArrayList<Collection> selectedCollections = new ArrayList<>();

    public recyclerAdapter(ArrayList<com.example.b07demosummer2024.Collection> collectionList) {
        this.collectionList = collectionList;
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_adapater, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Collection collection = collectionList.get(position);
        holder.name.setText(collection.getName());
        holder.category.setText("Category: " + collection.getCategory());
        holder.period.setText("Period: " + collection.getPeriod());
        holder.lot.setText("Lot #: " + collection.getLotNumber());
        holder.description.setText("Description: " + collection.getDescription());

        String description = collection.getDescription();
        if (description != null) {
            if (description.length() <= 50) {
                holder.description.setTextSize(16);
            } else if (description.length() <= 100) {
                holder.description.setTextSize(14);
            } else {
                holder.description.setTextSize(12);
            }
        }

        if (collection.getMediaUrl() != null && !collection.getMediaUrl().isEmpty()) {
            if (isVideoUrl(collection.getMediaUrl())) {
                holder.imageView.setVisibility(View.GONE);
                holder.videoView.setVisibility(View.VISIBLE);
                holder.videoView.setVideoURI(Uri.parse(collection.getMediaUrl()));
                holder.videoView.setOnPreparedListener(mp -> {
                    mp.setLooping(true);
                    holder.videoView.start();
                });
            } else {
                holder.videoView.setVisibility(View.GONE);
                holder.imageView.setVisibility(View.VISIBLE);
                Picasso.get().load(collection.getMediaUrl()).into(holder.imageView);
            }
        }

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(selectedCollections.contains(collection));
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedCollections.add(collection);
            } else {
                selectedCollections.remove(collection);
            }
        });
    }

    private boolean isVideoUrl(String url) {
        String lowerUrl = url.toLowerCase();
        if (lowerUrl.contains("?")) {
            lowerUrl = lowerUrl.substring(0, lowerUrl.indexOf("?"));
        }
        return lowerUrl.endsWith(".mp4") || lowerUrl.endsWith(".3gp") ||
                lowerUrl.endsWith(".mkv") || lowerUrl.endsWith(".webm") ||
                lowerUrl.endsWith(".avi");
    }




    public ArrayList<Collection> searchDataList(String [] searchInfo){

        ArrayList<Collection> searchList = new ArrayList<Collection>();
        for(Collection items: collectionList) { //this might cause issues
            boolean validResult = true;
            boolean validKeyword = false;
            if(searchInfo[0].length() != 0){
                validResult = items.getLotNumber().contains(searchInfo[0].toLowerCase());
            }
            if(searchInfo[1].length() != 0){
                validResult = validResult
                        && items.getName().toLowerCase().contains(searchInfo[1].toLowerCase());
            }
            if(searchInfo[2].length() != 0){
                validResult = validResult
                        && items.getCategory().toLowerCase().contains(searchInfo[2].toLowerCase());
            }
            if(searchInfo[3].length() != 0){
                validResult = validResult
                        && items.getPeriod().toLowerCase().contains(searchInfo[3].toLowerCase());
            }
            if(searchInfo[4].length() != 0){
                validKeyword = items.getLotNumber().contains(searchInfo[4].toLowerCase())
                        || items.getName().toLowerCase().contains(searchInfo[4].toLowerCase())
                        || items.getCategory().toLowerCase().contains(searchInfo[4].toLowerCase())
                        || items.getPeriod().toLowerCase().contains(searchInfo[4].toLowerCase());
            }
            if ((searchInfo[4].isEmpty() && validResult) || (validKeyword && validResult)) {
                searchList.add(items);
            }
        }
        return searchList;
    }




    public ArrayList<Collection> getSelectedCollections() {
        return selectedCollections;
    }

    @Override
    public int getItemCount() {
        return collectionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView category;
        private TextView period;
        private TextView lot;
        private TextView description;
        private ImageView imageView;
        private VideoView videoView;
        private CheckBox checkBox;

        public MyViewHolder(final View view){
            super(view);
            name = view.findViewById(R.id.textViewName);
            category = view.findViewById(R.id.textViewCategory);
            period = view.findViewById(R.id.textViewPeriod);
            lot = view.findViewById(R.id.textViewLotNumber);
            description = view.findViewById(R.id.textViewDescription);
            imageView = view.findViewById(R.id.imageView);
            videoView = view.findViewById(R.id.videoView);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }

}