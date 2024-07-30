package com.example.b07demosummer2024;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SelectedCollectionsFragment extends Fragment {
    private RecyclerView recyclerView;
    private SelectedCollectionsAdapter selectedCollectionsAdapter;
    private List<Collection> selectedCollections;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_collections, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (getArguments() != null) {
            selectedCollections = (List<Collection>) getArguments().getSerializable("selectedCollections");
        }

        selectedCollectionsAdapter = new SelectedCollectionsAdapter(selectedCollections);
        recyclerView.setAdapter(selectedCollectionsAdapter);

        Button backButton = view.findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }
}


