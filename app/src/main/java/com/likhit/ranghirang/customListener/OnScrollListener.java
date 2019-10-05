package com.likhit.ranghirang.customListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class OnScrollListener extends RecyclerView.OnScrollListener {

    public static final int PAGE_START = 1;

    private GridLayoutManager gridLayoutManager;


    public OnScrollListener(GridLayoutManager gridLayoutManager) {
        this.gridLayoutManager = gridLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = gridLayoutManager.getChildCount();
        int totalItemCount = gridLayoutManager.getItemCount();
        int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount &&
                    firstVisibleItemPosition >= 0 &&
                    totalItemCount >= getTotalPageCount()) {
                loadMoreItems();
            }
        }

    }

    public abstract void loadMoreItems();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();

    public abstract int getTotalPageCount();
}
