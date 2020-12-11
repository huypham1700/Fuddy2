package com.example.vietis_fuddy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import Entity.User;
import Interface.IView;
import Ultilities.UserApp;
import Viewmodel.AccountActivityViewModel;

public class LoginActivity extends AppCompatActivity implements IView {

    private TextView txtSignUpAccount;
    private EditText edtSigninEmail;
    private EditText edtSigninPassword;
    private LinearLayout llSignin;
    private AccountActivityViewModel accountActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mappingUI();
        setupUI();



    }

    @Override
    public void mappingUI() {
        txtSignUpAccount = findViewById(R.id.txtSignUpAccount);
        edtSigninEmail = findViewById(R.id.edtSigninEmail);
        edtSigninPassword = findViewById(R.id.edtSigninPassword);
        llSignin= findViewById(R.id.llSignIn);
        accountActivityViewModel = new AccountActivityViewModel();
    }

    @Override
    public void setupUI() {
        llSignin.setOnClickListener(v -> {
            login();
            Log.d("onclick","asdasdasda");

        });
        txtSignUpAccount.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    public void navigateToHomeActivity(User user){
        Intent intent = new Intent(LoginActivity.this, HomeAppActivity.class);
        UserApp.user.setTokenKey(user.getTokenKey());
        UserApp.user.setAddress(user.getAddress());
        UserApp.user.setName(user.getName());
        UserApp.user.setId(user.getId());
        UserApp.user.setImageId(user.getImageId());
        UserApp.user.setPhoneNumber(user.getPhoneNumber());
        UserApp.user.setImageURL(user.getImageURL());
        this.startActivity(intent);

    }

    public String getEmail() {
        return edtSigninEmail.getText().toString().trim();
    }

    public String getPassword() {
        return edtSigninPassword.getText().toString().trim();
    }

    private void login() {
        accountActivityViewModel.login(getEmail(), getPassword());
        accountActivityViewModel.getUser().observe(this, user -> {
            if(user.getId() !=0){
                navigateToHomeActivity(user);
            }
        });
    }

}