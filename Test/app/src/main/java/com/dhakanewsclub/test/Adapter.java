package com.dhakanewsclub.test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhakanewsclub.test.databinding.ListGroupBinding;
import com.dhakanewsclub.test.model.Category;
import com.dhakanewsclub.test.model.SubCategory;

import java.util.List;

class Adapter extends BaseExpandableListAdapter {

    public interface OnClick{
        public void onCategoryClick(int position, boolean add);
        public void onSubClick(int groupPosition, int position, boolean add);
    }

    Context mContext;
    List<Category> mCategoryList;
    OnClick mOnClick;

    public Adapter(Context context, List<Category> categoryList, OnClick onClick) {
        this.mContext = context;
        this.mCategoryList = categoryList;
        this.mOnClick = onClick;
    }

    @Override
    public int getGroupCount() {
        //Log.d("DEBUGING","got data size"+mCategoryList.size());

        if(mCategoryList==null)return 0;
        else return mCategoryList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mCategoryList.get(i).getSubcatg().size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return mCategoryList.get(i).getSubcatg().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        final Category category = mCategoryList.get(i);
        if(view==null){

            LayoutInflater layoutInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView textView = view.findViewById(R.id.categoryName);
        textView.setText(category.getCategory_name());
//        Log.d("DEBUGING","seting data in view: "+ i);
        ImageView add = view.findViewById(R.id.categoryAddImage);
        ImageView checked = view.findViewById(R.id.categoryCheckedImage);
        if(category.isSelected){
            hideAdd(checked,add);
        }
        else {
            hideCancel(checked,add);
        }
        add.setOnClickListener(view1 -> {
            hideAdd(checked,add);
            mOnClick.onCategoryClick(i,true);
        });
        checked.setOnClickListener(view1 -> {
            hideCancel(checked,add);
            mOnClick.onCategoryClick(i,false);
        });

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final SubCategory subCategory = mCategoryList.get(i).getSubcatg().get(i1);
        if(view==null){

            LayoutInflater layoutInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView textView = view.findViewById(R.id.subCategory);
        textView.setText(subCategory.getSub_category_name());
        ImageView add = view.findViewById(R.id.addImage);
        ImageView checked = view.findViewById(R.id.checkedImage);
        Log.d("DEBUGING","getting child");
        if(subCategory.isAdded){
            hideAdd(checked,add);
        }
        else {
            hideCancel(checked,add);
        }

        add.setOnClickListener(view1 -> {
            hideAdd(checked,add);
            mOnClick.onSubClick(i,i1,true);
            Log.d("DEBUGING","on click on add sub cat"+ i1);
        });
        checked.setOnClickListener(view1 -> {
            hideCancel(checked, add);
            mOnClick.onSubClick(i,i1,false);
            Log.d("DEBUGING","on click on cancel sub cat"+ i1);
        });
        return view;
    }
    private void hideAdd(ImageView checked, ImageView add){
        checked.setVisibility(View.VISIBLE);
        add.setVisibility(View.GONE);
    }
    private void hideCancel(ImageView checked, ImageView add){
        add.setVisibility(View.VISIBLE);
        checked.setVisibility(View.GONE);
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
