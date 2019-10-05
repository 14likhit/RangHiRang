package com.likhit.ranghirang.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.likhit.ranghirang.data.model.Color;

import java.util.List;

public class ColorsListAdapter extends RecyclerView.Adapter<ColorsListAdapter.ColorListViewHolder> {

    private List<Color> colors;

    private LayoutInflater layoutInflater;

    @NonNull
    @Override
    public ColorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ColorListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(colors!=null && colors.size()>0){
            return colors.size();
        }
        return 0;
    }

    public class ColorListViewHolder extends RecyclerView.ViewHolder {
        public ColorListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
