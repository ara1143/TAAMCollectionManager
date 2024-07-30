package com.example.b07demosummer2024;

import com.google.firebase.database.DataSnapshot;

public class LoginPresenter {

    LoginView view;
    LoginModel model;

    public LoginPresenter(LoginView view, LoginModel model) {
        this.model = model;
        this.view = view;
    }

    // hello world testing

    public void checkDB(String username, String password) {
        if (username.equals("") || password.equals("")) {
            view.authFaliure();
        } else {
            model.checkAuth(this, username, password);
        }
    }

    public void authResult(boolean isAuth) {
        if (isAuth) { view.authSucess(); }
        else { view.authFaliure(); }
    }
}
