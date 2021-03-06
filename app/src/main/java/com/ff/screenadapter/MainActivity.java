package com.ff.screenadapter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ff.screenadapter.cutout.DisplayCutoutActivity;
import com.ff.screenadapter.density.DensityActivity;
import com.ff.screenadapter.density.DensityFragment;
import com.ff.screenadapter.percent.GooglePercentFragment;
import com.ff.screenadapter.percent.MyPercentFragment;
import com.ff.screenadapter.pixel.PixelFragment;
import com.ff.screenadapter.pixel.utils.UIUtils;

public class MainActivity extends AppCompatActivity implements MainFragment.OnListItemClickListener {

    private FrameLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRoot = new FrameLayout(this);
        mRoot.setId(View.generateViewId());
        mRoot.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(mRoot);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(mRoot.getId(), new MainFragment(), MainFragment.class.getName())
                    .commit();
        }
        UIUtils.INSTANCE.init(getApplicationContext());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        UIUtils.INSTANCE.notifyInstance(getApplicationContext());
    }

    @Override
    public void onListItemClick(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new PixelFragment();
                break;
            case 1:
                fragment = new GooglePercentFragment();
                break;
            case 2:
                fragment = new MyPercentFragment();
                break;
            case 3:
                startActivity(new Intent(this, DensityActivity.class));
                return;
            case 4:
                fragment = new DensityFragment();
                break;
            case 5:
                startActivity(new Intent(this, DisplayCutoutActivity.class));
                return;
            default:
                return;

        }
        getSupportFragmentManager().beginTransaction()
                .replace(mRoot.getId(), fragment, fragment.getClass().getName())
                .addToBackStack(null)
                .commit();
    }
}
