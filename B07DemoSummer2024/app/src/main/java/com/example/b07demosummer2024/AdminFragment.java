package com.example.b07demosummer2024;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdminFragment extends Fragment {
    private RecyclerView recyclerView;
    private recyclerAdapter recyclerAdapter;
    private ArrayList<Collection> collectionList;

    public static AdminFragment newInstance(ArrayList<Collection> collections) {
        AdminFragment fragment = new AdminFragment();
        Bundle args = new Bundle();
        args.putSerializable("collections", collections);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_admin_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Database.getCollections(new CollectionCallback() {
            @Override
            public void onCallback(ArrayList<Collection> collections) {
                collectionList = collections;
//                for (Collection collection : collectionList) {
//                    Log.i("getName", collection.getName());
//                }
                recyclerAdapter = new recyclerAdapter(collectionList);
                recyclerView.setAdapter(recyclerAdapter);

                Button reportButton = view.findViewById(R.id.button14);
                reportButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Navigate to GenerateReportFragment
                        GenerateReportFragment generateReportFragment = GenerateReportFragment.newInstance();
                        loadFragment(generateReportFragment);
                    }
                });

                Button backButton = view.findViewById(R.id.button15);
                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Navigate to GenerateReportFragment
                        HomeFragment homeFragment = HomeFragment.newInstance(collectionList);
                        loadFragment(homeFragment);
                    }
                });

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
            }
        });

//        recyclerAdapter = new recyclerAdapter(collectionList);
//        recyclerView.setAdapter(recyclerAdapter);
//
//        Button reportButton = view.findViewById(R.id.button14);
//        reportButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigate to GenerateReportFragment
//                GenerateReportFragment generateReportFragment = GenerateReportFragment.newInstance();
//                loadFragment(generateReportFragment);
//            }
//        });
//
//        Button backButton = view.findViewById(R.id.button15);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigate to GenerateReportFragment
//                HomeFragment homeFragment = HomeFragment.newInstance(collectionList);
//                loadFragment(homeFragment);
//            }
//        });
//
//        Button viewButton = view.findViewById(R.id.viewButton);
//        viewButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<Collection> selectedCollections = recyclerAdapter.getSelectedCollections();
//                // Pass the selected collections to the new fragment
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("selectedCollections", (Serializable) selectedCollections);
//                SelectedCollectionsFragment fragment = new SelectedCollectionsFragment();
//                fragment.setArguments(bundle);
//
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, fragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
