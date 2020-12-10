package com.example.vietis_fuddy;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.vietis.Data.inteface.IView;
import com.example.vietis.Data.view_model.SplashActivityViewModel;
import com.example.vietis.Utilities.notifications.MyFirebaseMessagingService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashActivity extends AppCompatActivity implements IView {

    private static final String TAG = "Notification";
    private final SplashActivityViewModel splashActivityViewModel = new SplashActivityViewModel();

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    MyFirebaseMessagingService.CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is channel 1 ");
            channel1.enableLights(true);
            channel1.setLightColor(Color.CYAN);
            channel1.enableVibration(true);
            channel1.setVibrationPattern(new long[]{100, 400, 200, 400});
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                    return;
                }
                String token = task.getResult();
                splashActivityViewModel.deviceRegister(token);
                splashActivityViewModel.getMsg().observe(com.example.vietis.activities.SplashActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String msg) {
                        if(!msg.equals("")){
                            Toast.makeText(com.example.vietis.activities.SplashActivity.this,msg,Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(com.example.vietis.activities.SplashActivity.this,"failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        createNotificationChannels();
        setContentView(R.layout.activity_splash);
        mappingUI();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(com.example.vietis.activities.SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 3000);
    }

    @Override
    public void mappingUI() {

    }

    @Override
    public void setupUI() {

    }
}