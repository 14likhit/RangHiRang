package com.likhit.ranghirang.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.likhit.ranghirang.R;
import com.likhit.ranghirang.data.model.Color;
import com.likhit.ranghirang.databinding.LayoutColorItemBinding;

import java.util.List;

public class ColorsListAdapter extends RecyclerView.Adapter<ColorsListAdapter.ColorListViewHolder> {

    private List<Color> colors;

    private LayoutInflater layoutInflater;

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    @NonNull
    @Override
    public ColorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new ColorsListAdapter.ColorListViewHolder(layoutInflater.inflate(R.layout.layout_color_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ColorListViewHolder holder, int position) {
        Color color = colors.get(position);

        holder.binding.colorTextView.setText(color.getName());
    }

    @Override
    public int getItemCount() {
        if (colors != null && colors.size() > 0) {
            return colors.size();
        }
        return 0;
    }

    public class ColorListViewHolder extends RecyclerView.ViewHolder {
        private LayoutColorItemBinding binding;

        public ColorListViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
