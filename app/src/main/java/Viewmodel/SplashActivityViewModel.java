package Viewmodel;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import Repository.INotiRepository;
import Repository.NotificationRepository;

public class SplashActivityViewModel extends ViewModel implements INotiRepository {
    private MutableLiveData<String> msg = new MutableLiveData<>();

    public LiveData<String> getMsg() {
        return msg;
    }

    public void deviceRegister(String tokenKey) {
        NotificationRepository.getInstance(this).deviceRegister(tokenKey);
    }

    @Override
    public void getNotiMessage(String msg, Exception error) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                SplashActivityViewModel.this.msg.setValue(error == null ? msg : "");
            }
        });
    }

    @Override
    public void getAllNotiList() {
    }
}
