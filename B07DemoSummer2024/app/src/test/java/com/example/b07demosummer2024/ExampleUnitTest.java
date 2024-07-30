package com.example.b07demosummer2024;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    LoginModel model;

    @Mock
    LoginView view;

    @Mock
    EditText insertUsername, insertPassword;

    @Mock
    Editable edit;

    @Mock
    View baseView;

    @Test
    public void checkEmptyUsername() {
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.checkDB("", "");
        verify(view).authFaliure();
    }

    @Test
    public void checkValidCredentials() {
        LoginPresenter presenter = new LoginPresenter(view, model);
        doAnswer(invocation -> {
            presenter.authResult(true);
            return null;
        }).when(model).checkAuth(presenter, "user1", "password1");
        presenter.checkDB("user1", "password1");
        verify(view).authSucess();
    }

    @Test
    public void checkInvalidCredentials() {
        LoginPresenter presenter = new LoginPresenter(view, model);
        doAnswer(invocation -> {
            presenter.authResult(false);
            return null;
        }).when(model).checkAuth(presenter, "user12", "password1");
        presenter.checkDB("user12", "password1");
        verify(view).authFaliure();
    }
}