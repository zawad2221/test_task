package com.dhakanewsclub.test;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.dhakanewsclub.test.model.Category;
import com.dhakanewsclub.test.model.CategoryData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class Repository {
    public static Repository instance;
    public static Repository getInstance(){
        if(instance==null){
            instance = new Repository();
        }
        return instance;
    }

    public MutableLiveData<List<Category>> getCategory(){
        MutableLiveData<List<Category>> responseData = new MutableLiveData<>();
        Api api = getApi();
        Call<CategoryData> call = api.getCategory();
        call.enqueue(new Callback<CategoryData>() {
            @Override
            public void onResponse(Call<CategoryData> call, Response<CategoryData> response) {
                if(response.isSuccessful()){
                    responseData.setValue(response.body().categories);
                    Log.d("DEBUGING","got data "+response.body().categories.size());
                }
                Log.d("DEBUGING","got data");
            }

            @Override
            public void onFailure(Call<CategoryData> call, Throwable t) {
                Log.d("DEBUGING","got data failded"+t.getMessage());
            }
        });
        return responseData;
    }

    private Api getApi(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://www.test.api.liker.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit.create(Api.class);
    }
}
