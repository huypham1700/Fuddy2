package Viewmodel;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import Entity.User;
import Repository.IUserRepository;
import Repository.UserRepository;

public class AccountActivityViewModel extends ViewModel implements IUserRepository {
    private MutableLiveData<User> user = new MutableLiveData<>();
    public LiveData<User> getUser(){
        return user;
    }

    public void login(String email, String password){
        UserRepository.getInstance(this).login(email,password);
    }
    public void register(String email, String password){
        UserRepository.getInstance(this).register(email,password);
    }
    @Override
    public void afterLogin(User user, Exception error) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                AccountActivityViewModel.this.user.setValue(error == null ? user : User.builder().build());
            }
        });
    }

    @Override
    public void afterRegister(User user, Exception error) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                AccountActivityViewModel.this.user.setValue(error == null ? user : User.builder().build());
            }
        });
    }

    @Override
    public void getSettingData(User user, Exception error) {

    }
}
