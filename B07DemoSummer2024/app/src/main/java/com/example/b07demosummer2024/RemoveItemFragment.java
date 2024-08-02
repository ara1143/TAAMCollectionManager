package com.example.b07demosummer2024;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RemoveItemFragment extends Fragment {
    private RecyclerView recyclerView;
    private SelectedCollectionsAdapter selectedCollectionsAdapter;
    private List<Collection> selectedCollections;

    private FirebaseDatabase db;
    private DatabaseReference itemsRef;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_remove_item_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        selectedCollections = (List<Collection>) getArguments().getSerializable("selectedCollections");

        selectedCollectionsAdapter = new SelectedCollectionsAdapter(selectedCollections);
        recyclerView.setAdapter(selectedCollectionsAdapter);

        textView = view.findViewById(R.id.dltConfirmMessage);
        GetConfirmationMessage(textView);

        db = FirebaseDatabase.getInstance("https://taamcollectionmanager-default-rtdb.firebaseio.com/");


        Button rmvBtn = view.findViewById(R.id.RmvRemoveBtn);
        Button cancelBtn = view.findViewById(R.id.RmvCancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { getParentFragmentManager().popBackStack();}
        });

        rmvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { removeItem();}
        });

        return view;
    }


    private void GetConfirmationMessage(TextView text){
            StringBuilder itemlotNumber = new StringBuilder();
            int items = selectedCollections.size();
            for (int i = 0 ; i < items; i++ ){
                itemlotNumber.append(",").append(selectedCollections.get(i).getLotNumber()).append(" ");
            }
            text.setText("Are you sure you want to remove the selected items:(Lot#" + itemlotNumber.toString() + ")");
    }


    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void removeItem(){
        itemsRef = db.getReference("collections");

        itemsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int dltCounter = selectedCollections.size();
                List<String> toDlt = new ArrayList<>();
                for (int i = 0; i < dltCounter ; i++) {
                    toDlt.add(selectedCollections.get(i).getName());
                }
                    boolean itemFound = false;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String item = snapshot.child("name").getValue(String.class);
                        if (item != null && toDlt.contains(item)){
                            snapshot.getRef().removeValue().addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(getContext(), "Item deleted", Toast.LENGTH_SHORT).show();
                                    loadFragment(new AdminFragment());
                                }else{
                                    Toast.makeText(getContext(), "Failed to delete item", Toast.LENGTH_SHORT).show();
                                }
                            });
                            itemFound = true;
                        }
                    }
                    if (!itemFound){
                        Toast.makeText(getContext(), "Item not found" , Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}