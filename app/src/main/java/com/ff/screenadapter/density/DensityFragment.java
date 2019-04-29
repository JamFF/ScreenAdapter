package com.ff.screenadapter.density;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ff.screenadapter.R;

/**
 * description: Fragment中使用Density
 * author: FF
 * time: 2019-04-29 17:10
 */
public class DensityFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (getActivity() != null) {
//            Density.setDensity(getActivity().getApplication(), getActivity());
//        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_density, container, false);
    }

    @Override
    public void onDetach() {
//        if (getActivity() != null) {
//            Density.resetDensity(getActivity().getApplication(), getActivity());
//        }
        super.onDetach();
    }
}
