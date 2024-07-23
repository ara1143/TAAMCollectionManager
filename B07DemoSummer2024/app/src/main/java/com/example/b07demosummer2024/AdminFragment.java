package com.example.b07demosummer2024;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

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
        if (getArguments() != null) {
            collectionList = (ArrayList<Collection>) getArguments().getSerializable("collections");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_admin_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerAdapter = new recyclerAdapter(collectionList);
        recyclerView.setAdapter(recyclerAdapter);

        return view;
    }
}
