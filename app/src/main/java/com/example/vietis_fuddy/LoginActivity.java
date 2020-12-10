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

import com.example.vietis.Data.entity.User;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Data.view_model.LoginActivityViewModel;
import com.example.vietis.Utilities.common.UserApp;

public class LoginActivity extends AppCompatActivity implements IView {

    private TextView txtSignUpAccount;
    private EditText edtSigninEmail;
    private EditText edtSigninPassword;
    private LinearLayout llSignin;
    private LoginActivityViewModel loginActivityViewModel;

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
        loginActivityViewModel = new LoginActivityViewModel();
    }

    @Override
    public void setupUI() {
        llSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                Log.d("onclick","asdasdasda");

            }
        });
        txtSignUpAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.vietis.activities.LoginActivity.this, RegisterActivity.class));
            }
        });
    }



    public void navigateToHomeActivity(User user){

//        Intent intent = new Intent(LoginActivity.this, Home2Activity.class);
        //   intent.putExtra("userid", user.getId());
        //  this.startActivity(intent);

        Intent intent = new Intent(com.example.vietis.activities.LoginActivity.this, HomeAppActivity.class);
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
        loginActivityViewModel.login(getEmail(), getPassword());
        loginActivityViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user.getId() !=0){
                    navigateToHomeActivity(user);
                }
            }
        });
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //do your code snippet here.
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
}