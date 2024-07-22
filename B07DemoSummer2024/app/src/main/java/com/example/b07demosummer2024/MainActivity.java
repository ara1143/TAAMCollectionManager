package com.example.b07demosummer2024;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance("https://taamcollectionmanager-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = db.getReference("testDemo");

        //users and collections nodes reference
        DatabaseReference usersRef = db.getReference("users");
        DatabaseReference collectionsRef = db.getReference("collections");

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }
    }

    private void addUser(DatabaseReference usersRef, String username, String password) {
        //push user data to the users node
        DatabaseReference newUserRef = usersRef.push();
        newUserRef.setValue(new User(username, password), (error, ref) -> {
            if (error != null) {
                Log.e("Firebase", "Error adding user: " + error.getMessage());
            } else {
                Log.d("Firebase", "User added successfully!");
            }
        });
    }

    private void addCollection(DatabaseReference collectionsRef, String name, String lotNumber, String category, String period, String description, String mediaUrl) {
        // Push collection data to the collections node
        DatabaseReference newCollectionRef = collectionsRef.push();
        newCollectionRef.setValue(new Collection(name, lotNumber, category, period, description, mediaUrl), (error, ref) -> {
            if (error != null) {
                Log.e("Firebase", "Error adding collection: " + error.getMessage());
            } else {
                Log.d("Firebase", "Collection added successfully!");
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}