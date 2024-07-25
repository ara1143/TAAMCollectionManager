package com.example.b07demosummer2024;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginFragment extends Fragment {
    private EditText insertUsername, insertPassword;
    private Button loginButton;

    private FirebaseDatabase db;
    private DatabaseReference usersRef;

    // ArrayList<Collection> collectionList = (ArrayList<Collection>) getArguments().getSerializable("collections");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_page_fragment, container, false);
        insertUsername = view.findViewById(R.id.insertUsername);
        insertPassword = view.findViewById(R.id.insertPassword);
        loginButton = view.findViewById(R.id.loginButton);

        db = FirebaseDatabase.getInstance("https://taamcollectionmanager-default-rtdb.firebaseio.com/");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        return view;
    }

    private void loginUser() {
        String password = insertPassword.getText().toString();
        String username = insertUsername.getText().toString();

        if (password.isEmpty() || username.isEmpty()) {
            Toast.makeText(getContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        usersRef = db.getReference("users");

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data: snapshot.getChildren()) {
                    String username1 = data.child("username").getValue(String.class);
                    String password1 = data.child("password").getValue(String.class);
                    if (username1.equals(username) && password1.equals(password)) {
                        Toast.makeText(getContext(), "You are an admin (need to connect to Admin Fragment)!", Toast.LENGTH_SHORT).show();
                        // Fragment adminFragment = new AdminFragment();
                        // loadFragment(adminFragment);
                        // need to connect to AdminFragment
                        return;
                    }
                }
                Toast.makeText(getContext(), "Incorrect username or password, please try again.", Toast.LENGTH_SHORT).show();
                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
