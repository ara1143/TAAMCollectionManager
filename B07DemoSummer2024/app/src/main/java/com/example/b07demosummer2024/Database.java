package com.example.b07demosummer2024;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Database {


    static FirebaseDatabase db;
    static ArrayList<Collection> collectionList1;

    public static void getCollections(CollectionCallback callback) {
        db = FirebaseDatabase.getInstance("https://taamcollectionmanager-default-rtdb.firebaseio.com/");
        DatabaseReference collectionsRef = db.getReference("collections");

        collectionList1 = new ArrayList<>();
        collectionsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                collectionList1.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Collection collection = dataSnapshot.getValue(Collection.class);
                    collectionList1.add(collection);
                }
                callback.onCallback(collectionList1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error fetching data: " + error.getMessage());
            }
        });
    }
}