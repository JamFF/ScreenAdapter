package com.ff.screenadapter.pixel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ff.screenadapter.R;
import com.ff.screenadapter.pixel.utils.ViewCalculateUtil;

/**
 * description: 像素适配
 * author: FF
 * time: 2019-04-26 19:02
 */
public class PixelFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pixel, container, false);

        // 代码像素适配
        TextView tvText3 = view.findViewById(R.id.tv_3);
        TextView tvText4 = view.findViewById(R.id.tv_4);
        ViewCalculateUtil.setViewLinearLayoutParam(tvText3, 540, 100, 0, 0, 0, 0);
        ViewCalculateUtil.setViewLinearLayoutParam(tvText4, 1080, 100, 0, 0, 0, 0);
        ViewCalculateUtil.setTextSize(tvText3, 30);
        return view;
    }
}
