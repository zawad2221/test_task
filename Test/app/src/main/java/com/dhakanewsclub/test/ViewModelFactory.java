package com.dhakanewsclub.test;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;


    public ViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MainViewModel(mApplication, mParam);
    }
}
