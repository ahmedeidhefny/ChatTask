package com.ahmed.eid.chattask.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ahmed.eid.chattask.R;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]
            {
                    R.string.tab_recent,
                    R.string.tab_favorites
            };

    private ArrayList<Fragment> mFragments = new ArrayList();

    private final Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragments(Fragment fragment){
        mFragments.add(fragment);
    }
}