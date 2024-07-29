package com.example.b07demosummer2024;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.util.Log;
import android.widget.Button;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchItemsFragment extends Fragment {

    private RecyclerView recyclerView;
    private recyclerAdapter recyclerAdapter;
    private ArrayList<Collection> collectionList;

    public SearchItemsFragment() {
        // Required empty public constructor
    }


    public static SearchItemsFragment newInstance(ArrayList<Collection> collections) {
        SearchItemsFragment fragment = new SearchItemsFragment();
        Bundle args = new Bundle();
        args.putSerializable("collections", collections);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            collectionList = (ArrayList<Collection>) getArguments().getSerializable("collections");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_items, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Check if collectionList is not null before setting the adapter
        if (collectionList != null) {
            recyclerAdapter = new recyclerAdapter(collectionList);
            recyclerView.setAdapter(recyclerAdapter);
        }
        recyclerView.setVisibility(View.GONE);

        SearchView searchNum = view.findViewById(R.id.searchNum);
        SearchView searchName = view.findViewById(R.id.searchName);
        SearchView searchCategory = view.findViewById(R.id.searchCategory);
        SearchView searchPeriod = view.findViewById(R.id.searchPeriod);
        searchNum.clearFocus();
        searchName.clearFocus();
        searchCategory.clearFocus();
        searchPeriod.clearFocus();
        String [] itemInfo = new String[4];
        itemInfo[0] = ""; //make sure never have to deal with null.lowercase()
        itemInfo[1] = "";
        itemInfo[2] = "";
        itemInfo[3] = "";

        searchNum.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            public boolean onQueryTextSubmit(String query){
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                itemInfo[0] = newText;
                return true;
            }
        });
        searchName.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            public boolean onQueryTextSubmit(String query){
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                itemInfo[1] = newText;
                return true;
            }
        });
        searchCategory.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            public boolean onQueryTextSubmit(String query){
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                itemInfo[2] = newText;
                return true;
            }
        });
        searchPeriod.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            public boolean onQueryTextSubmit(String query){
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                itemInfo[3] = newText;
                return true;
            }
        });


        Button resultButton = view.findViewById(R.id.resultButton);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(recyclerAdapter == null){
                    Log.i("my tag", "the recycler adapter is null");
                }
                else{

                    ArrayList<Collection> selectedCollections = recyclerAdapter.searchDataList(itemInfo);
                    //HomeFragment fragment = HomeFragment.newInstance(selectedCollections);
                    SearchedCollectionsFragment fragment = SearchedCollectionsFragment.newInstance(selectedCollections);
                    loadFragment(fragment);
                }

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

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}