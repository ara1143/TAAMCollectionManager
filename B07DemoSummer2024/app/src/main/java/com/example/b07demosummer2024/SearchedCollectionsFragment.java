package com.example.b07demosummer2024;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchedCollectionsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Collection> searchedCollections;
    private recyclerAdapter recyclerAdapter;

    public static SearchedCollectionsFragment newInstance(ArrayList<Collection> collections) {
        SearchedCollectionsFragment fragment = new SearchedCollectionsFragment();
        Bundle args = new Bundle();
        args.putSerializable("collections", collections);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchedCollections = (ArrayList<Collection>) getArguments().getSerializable("collections");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_searched_collections, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (searchedCollections != null) {
            recyclerAdapter = new recyclerAdapter(searchedCollections);
            recyclerView.setAdapter(recyclerAdapter);
        }

        Button viewButton = view.findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                List<Collection> selectedCollections = recyclerAdapter.getSelectedCollections();
                // Pass the selected collections to the new fragment
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedCollections", (Serializable) selectedCollections);
                SelectedCollectionsFragment fragment = new SelectedCollectionsFragment();
                fragment.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }
}