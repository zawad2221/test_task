package com.dhakanewsclub.test;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dhakanewsclub.test.model.Category;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    Repository mRepository;

    private MutableLiveData<List<Category>> category;
    public List<String> result = new ArrayList<>();
    public int page;

    public MainViewModel(Application application, String param) {

    }

    public void initCategory(){
        initRepository();
        category = mRepository.getCategory();
    }
    public MutableLiveData<List<Category>> getCategory(){
        return category;
    }

    private void initRepository(){
        if (mRepository==null){
            mRepository = Repository.getInstance();
        }
    }

}
