package com.dhakanewsclub.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.dhakanewsclub.test.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mActivityMainBinding;
    CategoryListFragment mCategoryListFragment;
    SelectionResultFragment mSelectionResultFragment;

    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());
        mMainViewModel=new ViewModelProvider(this,new ViewModelFactory(getApplication(),"test")).get(MainViewModel.class);


        //showListPage();
        showResultPage();
        mActivityMainBinding.save.setOnClickListener(view -> {
            showResultPage();
        });
        mActivityMainBinding.back.setOnClickListener(view -> {
            onBackPressed();
        });
        mActivityMainBinding.start.setOnClickListener(view -> {
            showListPage();
        });

    }
    private void showListPage(){
        if(mCategoryListFragment==null) mCategoryListFragment = new CategoryListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mCategoryListFragment).commit();
        mMainViewModel.page=1;
        setVisibilityOfTopBar(View.VISIBLE);
        setVisibilityOfStart(View.GONE);
    }
    private void showResultPage(){
        mMainViewModel.page=2;
        if (mSelectionResultFragment==null) mSelectionResultFragment= new SelectionResultFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mSelectionResultFragment).commit();
        setVisibilityOfTopBar(View.GONE);
        setVisibilityOfStart(View.VISIBLE);


    }

    private void setVisibilityOfTopBar(int visibility){
        mActivityMainBinding.cardView.setVisibility(visibility);
    }
    private void setVisibilityOfStart(int visibility){
        mActivityMainBinding.start.setVisibility(visibility);
    }

    @Override
    public void onBackPressed() {
        if(mMainViewModel.page==1)super.onBackPressed();
        if(mMainViewModel.page==2)  showListPage();
    }
}