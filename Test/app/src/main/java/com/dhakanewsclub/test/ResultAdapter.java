package com.dhakanewsclub.test;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dhakanewsclub.test.databinding.ResultItemBinding;

import java.util.List;

class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder>{
    List<String> name;

    public ResultAdapter(List<String> name) {
        this.name = name;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResultViewHolder(
                ResultItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        final String n = name.get(position);
        holder.mResultItemBinding.selectedName.setText(n);
        Log.d("DEBUGING","adding result");

    }

    @Override
    public int getItemCount() {
        if (name==null)
        return 0;
        else return name.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{
        ResultItemBinding mResultItemBinding;
        public ResultViewHolder(@NonNull ResultItemBinding resultItemBinding) {
            super(resultItemBinding.getRoot());
            mResultItemBinding = resultItemBinding;
        }
    }
}
