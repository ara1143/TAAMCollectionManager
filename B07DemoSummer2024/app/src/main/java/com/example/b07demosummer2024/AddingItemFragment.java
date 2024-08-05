package com.example.b07demosummer2024;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AddingItemFragment extends Fragment {
    EditText etltno;
    EditText etname;
    EditText etcategory;
    EditText etperiod;
    EditText etdescription;
    EditText etpicvid;
    DatabaseReference itemdbref;
    Button bsub;
    Button backButton;


    public AddingItemFragment() {
        // Required empty public constructor
    }

    public static AddingItemFragment newInstance(ArrayList<Collection> collections) {
        AddingItemFragment fragment = new AddingItemFragment();
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
        View view = inflater.inflate(R.layout.fragment_adding_item, container, false);
        etltno = view.findViewById(R.id.lotNumber);
        etname = view.findViewById(R.id.name);
        etcategory = view.findViewById(R.id.category);
        etperiod = view.findViewById(R.id.period);
        etdescription = view.findViewById(R.id.description);
        etpicvid = view.findViewById(R.id.pictureVideoUrl);
        bsub = view.findViewById(R.id.submitButton);
        itemdbref = FirebaseDatabase.getInstance("https://taamcollectionmanager-default-rtdb.firebaseio.com/").getReference("collections");

        bsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertItemData();
            }
        });

        backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void insertItemData() {
        String lotnumber = etltno.getText().toString();
        String name = etname.getText().toString();
        String category = etcategory.getText().toString();
        String period = etperiod.getText().toString();
        String desc = etdescription.getText().toString();
        String URL = etpicvid.getText().toString();
        if (lotnumber.isEmpty() || name.isEmpty() || category.isEmpty() ||
                period.isEmpty() || desc.isEmpty() || URL.isEmpty()) {
            //Toast.makeText(MainActivity.this, "Please fill out details properly", Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), "Please fill out details properly", Toast.LENGTH_SHORT).show();
        } else {
            itemdbref.orderByChild("lotNumber").equalTo(lotnumber).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // If an item with the same lot number exists, show an error message
                        //Toast.makeText(MainActivity.this, "This lot is already full", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), "This lot is already full", Toast.LENGTH_SHORT).show();
                    } else {
                        // If no item with the same lot number exists, add the new item to the database
                        Collection zol = new Collection(name, lotnumber, category, period, desc, URL);
                        itemdbref.push().setValue(zol).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(getContext(), "Item added", Toast.LENGTH_SHORT).show();
                                loadFragment(new AdminFragment());
                            }else{
                                Toast.makeText(getContext(), "Failed to add item", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle errors
                }
            });
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}