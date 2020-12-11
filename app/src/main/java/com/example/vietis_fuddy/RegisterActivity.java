package com.example.vietis_fuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import Interface.IView;
import Viewmodel.AccountActivityViewModel;


public class RegisterActivity extends AppCompatActivity implements IView {

    private AccountActivityViewModel accountActivityViewModel;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private LinearLayout btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        txtEmail = findViewById(R.id.edtSignupEmail);
        txtPassword=findViewById(R.id.edtSignupPassword);
        txtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.llSignUp);
        accountActivityViewModel = new AccountActivityViewModel();
    }

    private String getEmail() {
        return txtEmail.getText().toString().trim();
    }

    private String getPassword() {
        return txtPassword.getText().toString().trim();
    }


    public void register() {
        if(txtPassword.getText().toString().equals(txtConfirmPassword.getText().toString()))
            accountActivityViewModel.register(getEmail(), getPassword());
        else{
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setupUI() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
}
