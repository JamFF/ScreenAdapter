package com.ff.screenadapter.density;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ff.screenadapter.R;

/**
 * description: Activity中使用Density
 * author: FF
 * time: 2019-04-29 17:10
 */
public class DensityActivity extends BaseDensityActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_density);
    }
}
