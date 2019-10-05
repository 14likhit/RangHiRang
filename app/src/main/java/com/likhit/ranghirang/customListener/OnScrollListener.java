package com.likhit.ranghirang.customListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class OnScrollListener extends RecyclerView.OnScrollListener {

    public static final int PAGE_START = 1;

    private LinearLayoutManager linearLayoutManager;


    public OnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = linearLayoutManager.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

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
