package com.ff.screenadapter.percent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ff.screenadapter.R;

/**
 * description: 使用谷歌的百分比适配
 * author: FF
 * time: 2019-04-28 17:55
 */
public class GooglePercentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_google_percent, container, false);
    }
}
