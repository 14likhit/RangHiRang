package com.likhit.ranghirang.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.likhit.ranghirang.R;
import com.likhit.ranghirang.base.BaseActivity;
import com.likhit.ranghirang.customListener.OnScrollListener;
import com.likhit.ranghirang.data.model.Color;
import com.likhit.ranghirang.data.model.ColorList;
import com.likhit.ranghirang.databinding.ActivityColorsBinding;
import com.likhit.ranghirang.sharedPreference.PreferenceHelper;
import com.likhit.ranghirang.utils.ActivityLauncher;

import java.util.ArrayList;
import java.util.List;

import static com.likhit.ranghirang.customListener.OnScrollListener.PAGE_START;

/**
 * Home Activity to show list of colors.
 */
public class ColorsListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

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

        //ViewModel Initialisation
        colorsListViewModel = ViewModelProviders.of(this).get(ColorsListViewModel.class);

        //Adding Observer
        colorsListViewModel.colorListMutableLiveData.observe(this, new Observer<ColorList>() {
            @Override
            public void onChanged(ColorList colorList) {
                updateView(colorList);
            }
        });

        setupToolbar(getString(R.string.app_name), false, true);

        initView();
    }

    private void initView() {
        binding.swiprRefreshLayout.setOnRefreshListener(this);
        if (adapter == null) {
            adapter = new ColorsListAdapter();
        }
        if (colorsDetailList == null) {
            colorsDetailList = new ArrayList<>();
        }

        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        binding.colorListRecyclerView.setLayoutManager(linearLayoutManager);
        binding.colorListRecyclerView.setAdapter(adapter);

        //Implemented Custom Scroll Listener
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

        binding.retryLayout.retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.retryLayout.errorLayout.setVisibility(View.GONE);
                binding.swiprRefreshLayout.setRefreshing(true);
                getColors();
            }
        });

        getColors();

    }

    //make request call
    private void getColors() {
        colorsListViewModel.getColors(currentPage);
    }

    private void updateView(ColorList colorList) {
        binding.swiprRefreshLayout.setRefreshing(false);
        if (colorList != null) {
            this.colorList = colorList;
            if (colorList.getColors() != null && colorList.getColors().size() > 0) {
                if (currentPage == 1) {
                    totalPage = colorList.getTotalPages();
                }
                if (adapter.getColors() != null) {
                    if (adapter.getColors().size() > 0) {
                        adapter.removeLoadingFooter();
                    }
                    adapter.addAll(colorList.getColors());
                    this.colorsDetailList = adapter.getColors();
                } else if (colorList.getColors() != null) {
                    adapter.setColors(colorList.getColors());
                    this.colorsDetailList = colorList.getColors();
                    adapter.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(this, "Unable to load", Toast.LENGTH_SHORT).show();
            }
            isLoading = false;
            if (currentPage < totalPage) adapter.addLoadingFooter();
            else isLastPage = true;
        } else {
            binding.retryLayout.errorLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        getColors();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            PreferenceHelper.getInstance().saveUserLoggedIn(false);
            ActivityLauncher.launchLoginActivity(ColorsListActivity.this);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
