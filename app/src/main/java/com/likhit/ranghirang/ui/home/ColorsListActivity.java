package com.likhit.ranghirang.ui.home;

import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.likhit.ranghirang.R;
import com.likhit.ranghirang.base.BaseActivity;
import com.likhit.ranghirang.data.model.Color;
import com.likhit.ranghirang.data.model.ColorList;
import com.likhit.ranghirang.databinding.ActivityColorsBinding;

import java.util.ArrayList;
import java.util.List;

public class ColorsListActivity extends BaseActivity {

    private static final String TAG = "ColorsListActivity";

    private ActivityColorsBinding binding;

    private ColorList colorList;
    private List<Color> colorsDetaiList;
    private ColorsListAdapter adapter;

    private ColorsListViewModel colorsListViewModel;

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
        if (colorsDetaiList == null) {
            colorsDetaiList = new ArrayList<>();
        }

        binding.colorListRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.colorListRecyclerView.setAdapter(adapter);

        colorsListViewModel.getColors(1);

    }

    private void updateView(ColorList colorList) {
        if (colorList != null) {
            this.colorList = colorList;
            if (colorList.getColors() != null && colorList.getColors().size() > 0) {
                this.colorsDetaiList = colorList.getColors();
                adapter.setColors(colorsDetaiList);
                adapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(this, "Unable to load", Toast.LENGTH_SHORT).show();
        }
    }
}
