package com.dhakanewsclub.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhakanewsclub.test.databinding.FragmentCategoryListBinding;
import com.dhakanewsclub.test.model.Category;
import com.dhakanewsclub.test.model.SubCategory;

import java.util.List;


public class CategoryListFragment extends Fragment {
    FragmentCategoryListBinding mFragmentCategoryListBinding;
    MainViewModel mMainViewModel;


    public CategoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentCategoryListBinding = FragmentCategoryListBinding.inflate(inflater,container,false);
        return mFragmentCategoryListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainViewModel=new ViewModelProvider(getActivity(),new ViewModelFactory(getActivity().getApplication(),"test")).get(MainViewModel.class);

        if(mMainViewModel.getCategory()==null){
            mMainViewModel.initCategory();
            observeCategory();
        }
        else {
            initListView();
        }



    }

    private void observeCategory(){
        mMainViewModel.getCategory().observe(this.requireActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                initListView();
                //Log.d("DEBUGING","got data response "+categories.size());

            }
        });
    }
    private void initListView(){
        Adapter adapter = new Adapter(getContext(), mMainViewModel.getCategory().getValue(), new Adapter.OnClick() {
            @Override
            public void onCategoryClick(int position, boolean add) {
                Log.d("DEBUGING","cat click "+position);
                if(add){
                    mMainViewModel.getCategory().getValue().get(position).isSelected=true;
                    addItemOnResultList(mMainViewModel.getCategory().getValue().get(position).getCategory_name());
                }
                else {
                    mMainViewModel.getCategory().getValue().get(position).isSelected=false;

                    deleteResultItem(mMainViewModel.getCategory().getValue().get(position).getCategory_name());
                }
                int index=0;
                for(SubCategory s: mMainViewModel.getCategory().getValue().get(position).getSubcatg()){
                    if(add){
                        addItemOnResultList(s.getSub_category_name());
                        mMainViewModel.getCategory().getValue().get(position).getSubcatg().get(index).isAdded=true;
                    }
                    else {
                        mMainViewModel.getCategory().getValue().get(position).getSubcatg().get(index).isAdded=false;
                        deleteResultItem(s.getSub_category_name());
                    }
                    index++;
                }
                updateList(position);

                //mFragmentCategoryListBinding.listView.setSelectedGroup(position);

            }

            @Override
            public void onSubClick(int groupPosition, int position, boolean add) {
                Log.d("DEBUGING","sub cat click group "+groupPosition+" sub"+position);
                if(add){
                    addItemOnResultList(mMainViewModel.getCategory().getValue().get(groupPosition).getSubcatg().get(position).getSub_category_name());
                    mMainViewModel.getCategory().getValue().get(groupPosition).getSubcatg().get(position).isAdded=true;
                }
                else {
                    deleteResultItem(mMainViewModel.getCategory().getValue().get(groupPosition).getSubcatg().get(position).getSub_category_name());
                    mMainViewModel.getCategory().getValue().get(groupPosition).isSelected=false;
                    deleteResultItem(mMainViewModel.getCategory().getValue().get(groupPosition).getCategory_name());
                    mMainViewModel.getCategory().getValue().get(groupPosition).getSubcatg().get(position).isAdded=false;

                    updateList(groupPosition);
                }
            }
        });
        mFragmentCategoryListBinding.listView.setAdapter(adapter);



    }
    private void updateList(int position){
        mFragmentCategoryListBinding.listView.collapseGroup(position);
        mFragmentCategoryListBinding.listView.expandGroup(position);
    }
    private void addItemOnResultList(String item){
        boolean add=true;
        for(String result: mMainViewModel.result){
            if(result==item){
                add=false;
                break;
            }
        }
        if(add) mMainViewModel.result.add(item);

    }
    private void deleteResultItem(String item){
        int index=0;
        for(String result: mMainViewModel.result){
            if(result==item){
                mMainViewModel.result.remove(index);
                break;
            }

            index++;
        }
    }

}