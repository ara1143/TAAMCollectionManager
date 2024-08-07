package com.example.b07demosummer2024;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SelectedCollectionsAdapter extends RecyclerView.Adapter<SelectedCollectionsAdapter.MyViewHolder> {
    private List<Collection> selectedCollections;

    public SelectedCollectionsAdapter(List<Collection> selectedCollections) {
        this.selectedCollections = selectedCollections;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_adapater, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Collection collection = selectedCollections.get(position);
        holder.name.setText(collection.getName());
        holder.category.setText("Category: " + collection.getCategory());
        holder.period.setText("Period: " + collection.getPeriod());
        holder.lot.setText("Lot #: " + collection.getLotNumber());
        holder.description.setText("Description: " + collection.getDescription());

        // Adjust text size based on description length
        String description = collection.getDescription();
        if (description != null) {
            if (description.length() <= 50) {
                holder.description.setTextSize(16); // Set larger text size for short descriptions
            } else if (description.length() <= 100) {
                holder.description.setTextSize(14); // Set medium text size for medium descriptions
            } else {
                holder.description.setTextSize(12); // Set smaller text size for long descriptions
            }
        }

        if (collection.getMediaUrl() != null && !collection.getMediaUrl().isEmpty()) {
            if (isVideoUrl(collection.getMediaUrl())) {
                holder.imageView.setVisibility(View.GONE);
                holder.videoView.setVisibility(View.VISIBLE);
                holder.videoView.setVideoURI(Uri.parse(collection.getMediaUrl()));
                holder.videoView.setOnPreparedListener(mp -> mp.setLooping(true));
                holder.videoView.start();
            } else {
                holder.videoView.setVisibility(View.GONE);
                holder.imageView.setVisibility(View.VISIBLE);
                Picasso.get().load(collection.getMediaUrl()).into(holder.imageView);
            }
        } else {
            holder.videoView.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.GONE);
        }

        // Hide the checkbox
        holder.checkBox.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return selectedCollections.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView category;
        private TextView period;
        private TextView lot;
        private TextView description;
        private ImageView imageView;
        private VideoView videoView;
        private CheckBox checkBox;

        public MyViewHolder(final View view) {
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

    private boolean isVideoUrl(String url) {
        String lowerUrl = url.toLowerCase();
        if (lowerUrl.contains("?")) {
            lowerUrl = lowerUrl.substring(0, lowerUrl.indexOf("?"));
        }
        return lowerUrl.endsWith(".mp4") || lowerUrl.endsWith(".3gp") ||
                lowerUrl.endsWith(".mkv") || lowerUrl.endsWith(".webm") ||
                lowerUrl.endsWith(".avi");
    }
}


