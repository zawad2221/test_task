package com.dhakanewsclub.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhakanewsclub.test.databinding.FragmentSelectionResultBinding;


public class SelectionResultFragment extends Fragment {
    FragmentSelectionResultBinding mFragmentSelectionResultBinding;
    ResultAdapter mResultAdapter;
    private MainViewModel mMainViewModel;


    public SelectionResultFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentSelectionResultBinding = FragmentSelectionResultBinding.inflate(inflater, container,false);
        return mFragmentSelectionResultBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainViewModel=new ViewModelProvider(getActivity(),new ViewModelFactory(getActivity().getApplication(),"test")).get(MainViewModel.class);
        iniRecyclerView();

    }
    private void iniRecyclerView(){
        mResultAdapter = new ResultAdapter(mMainViewModel.result);
        mFragmentSelectionResultBinding.resultRecycler.setAdapter(mResultAdapter);
        mFragmentSelectionResultBinding.resultRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mFragmentSelectionResultBinding.resultRecycler.setHasFixedSize(true);
    }

}