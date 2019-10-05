package com.likhit.ranghirang.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.likhit.ranghirang.R;
import com.likhit.ranghirang.base.BaseActivity;
import com.likhit.ranghirang.customListener.OnScrollListener;
import com.likhit.ranghirang.data.model.Color;
import com.likhit.ranghirang.data.model.ColorList;
import com.likhit.ranghirang.databinding.ActivityColorsBinding;

import java.util.ArrayList;
import java.util.List;

public class ColorsListActivity extends BaseActivity {

    private static final String TAG = "ColorsListActivity";

    private ActivityColorsBinding binding;

    private ColorList colorList;
    private List<Color> colorsDetailList;
    private ColorsListAdapter adapter;

    private ColorsListViewModel colorsListViewModel;

    private LinearLayoutManager linearLayoutManager;

    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 2;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_colors);

        colorsListViewModel = ViewModelProviders.of(this).get(ColorsListViewModel.class);

        colorsListViewModel.colorListMutableLiveData.observe(this, new Observer<ColorList>() {
            @Override
            public void onChanged(ColorList colorList) {
                updateView(colorList);
            }
        });

        initView();
    }

    private void initView() {
        if (adapter == null) {
            adapter = new ColorsListAdapter();
        }
        if (colorsDetailList == null) {
            colorsDetailList = new ArrayList<>();
        }

        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        binding.colorListRecyclerView.setLayoutManager(linearLayoutManager);
        binding.colorListRecyclerView.setAdapter(adapter);

        binding.colorListRecyclerView.addOnScrollListener(new OnScrollListener(linearLayoutManager) {

            @Override
            public void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getColors();
                    }
                }, 1000);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public int getTotalPageCount() {
                return totalPage;
            }


        });

        getColors();

    }

    private void getColors() {
        colorsListViewModel.getColors(currentPage);
    }

    private void updateView(ColorList colorList) {
        if (colorList != null) {
            this.colorList = colorList;
            if (colorList.getColors() != null && colorList.getColors().size() > 0) {
                if (currentPage == 1) {
                    totalPage = colorList.getTotalPages();
                }
                if (adapter.getColors() != null) {
                    adapter.addAll(colorList.getColors());
                    adapter.addLoadingFooter();
                    this.colorsDetailList = adapter.getColors();
                } else if (colorList.getColors() != null) {
                    adapter.setColors(colorList.getColors());
                    this.colorsDetailList = colorList.getColors();
                    adapter.addLoadingFooter();
                    adapter.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(this, "Unable to load", Toast.LENGTH_SHORT).show();
            }
            adapter.removeLoadingFooter();
            isLoading = false;
            if (currentPage >= totalPage) isLastPage = true;
        }
    }
}
