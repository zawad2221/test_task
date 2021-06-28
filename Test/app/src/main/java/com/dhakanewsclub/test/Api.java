package com.dhakanewsclub.test;

import com.dhakanewsclub.test.model.Category;
import com.dhakanewsclub.test.model.CategoryData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface Api {
    @GET("get_categories")
    Call<CategoryData> getCategory(
    );
}
