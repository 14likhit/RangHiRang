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
import com.likhit.ranghirang.databinding.LayoutProgressBinding;

import java.util.List;

public class ColorsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int COLORS = 0;
    private static final int LOADING = 1;

    private List<Color> colors;

    private LayoutInflater layoutInflater;

    private boolean isLoadingAdded = false;

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case COLORS:
                viewHolder = new ColorsListAdapter.ColorListViewHolder(layoutInflater.inflate(R.layout.layout_color_item, parent, false));
                break;
            case LOADING:
                viewHolder = new ColorsListAdapter.LoadingViewHolder(layoutInflater.inflate(R.layout.layout_progress, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Color color = colors.get(position);

        switch (getItemViewType(position)) {
            case COLORS:
                ColorListViewHolder colorListViewHolder = (ColorListViewHolder) holder;
                colorListViewHolder.binding.setColor(color);
                break;
            case LOADING:
                break;
        }

    }

    @Override
    public int getItemCount() {
        if (colors != null && colors.size() > 0) {
            return colors.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == colors.size() - 1 && isLoadingAdded) ? LOADING : COLORS;
    }

    //Helper Methods

    public void add(Color color) {
        colors.add(color);
        notifyItemInserted(colors.size() - 1);
    }

    public void addAll(List<Color> colors) {
        for (Color color : colors) {
            add(color);
        }
    }

    public void remove(Color color) {
        int position = colors.indexOf(color);
        if (position >= 0) {
            colors.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Color());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = colors.size() - 1;
        Color color = getItem(position);

        if (color != null) {
            colors.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Color getItem(int position) {
        return colors.get(position);
    }

    public class ColorListViewHolder extends RecyclerView.ViewHolder {
        private LayoutColorItemBinding binding;

        public ColorListViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        private LayoutProgressBinding binding;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
