package com.example.b07demosummer2024;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class LoginView extends Fragment {
    private EditText insertUsername, insertPassword;
    private Button loginButton;
    LoginPresenter presenter;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_page_fragment, container, false);
        insertUsername = view.findViewById(R.id.insertUsername);
        insertPassword = view.findViewById(R.id.insertPassword);
        loginButton = view.findViewById(R.id.loginButton);
        presenter = new LoginPresenter(this, new LoginModel());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { loginUser(); }
        });

        return view;
    }

    // hello world testing

    private void loginUser() {
        presenter.checkDB(insertUsername.getText().toString(), insertPassword.getText().toString());
    }

    public void authSucess() {
        AdminFragment adminFragment = new AdminFragment();
        loadFragment(adminFragment);
    }

    public void authFaliure() {
        Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}