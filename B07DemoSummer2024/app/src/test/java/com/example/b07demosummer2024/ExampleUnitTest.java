package com.example.b07demosummer2024;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
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
        presenter.checkDB("user1","password1");
        verify(view).authSucess();
    }

    @Test
    public void checkInvalidCredentials() {
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.checkDB("notcorrectusername","notcorrectpassword");
        verify(view).authFaliure();
    }
//
    @Test
    public void checkDBClick() {
        when(insertUsername.getText()).thenReturn(edit);
        when(insertPassword.getText()).thenReturn(edit);
        when(edit.toString()).thenReturn("Test");
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.checkDB(insertUsername.getText().toString(), insertPassword.getText().toString());
        verify(view);
    }
//
}
