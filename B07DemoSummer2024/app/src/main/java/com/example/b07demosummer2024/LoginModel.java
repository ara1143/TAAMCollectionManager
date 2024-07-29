package com.example.b07demosummer2024;

import static java.security.AccessController.getContext;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginModel {

    FirebaseDatabase db;
    private DatabaseReference usersRef;

    public LoginModel() {
        db = FirebaseDatabase.getInstance("https://taamcollectionmanager-default-rtdb.firebaseio.com/");
        usersRef = db.getReference("users");
    }

    // hello world testing

    public void checkAuth(LoginPresenter presenter, String username, String password) {
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isAuth = false;
                for (DataSnapshot data: snapshot.getChildren()) {
                    String username1 = data.child("username").getValue(String.class);
                    String password1 = data.child("password").getValue(String.class);
                    if (username1.equals(username) && password1.equals(password)) {
                        isAuth = true;
                        presenter.authResult(isAuth);
                        return;
                    }
                }
                presenter.authResult(isAuth);
                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}


