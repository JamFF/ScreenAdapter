package com.ff.screenadapter.density;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * description: Density基类
 * author: FF
 * time: 2019-04-29 17:08
 */
public abstract class BaseDensityActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Density.setDensity(getApplication(), this);
    }

    @Override
    protected void onDestroy() {
        Density.resetDensity(getApplication(), this);
        super.onDestroy();
    }
}
