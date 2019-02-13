package com.bawie.shopmkk.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawie.shopmkk.ui.fragment.CircleFragment;
import com.bawie.shopmkk.ui.fragment.HomeFragment;
import com.bawie.shopmkk.ui.fragment.MineFragment;
import com.bawie.shopmkk.ui.fragment.OrderFragment;
import com.bawie.shopmkk.ui.fragment.ShopcartFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/12/6
 * author:KK(别来无恙)
 * function:
 */
public class ViewAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();
    
    public ViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return getInstance(i);
    }

    @Override
    public int getCount() {
        return 5;
    }

    private Fragment getInstance(int i){
        if (mFragmentList.size() > i){
            return mFragmentList.get(i);
        }
        Fragment fragment = null;
        switch (i){
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new CircleFragment();
                break;
            case 2:
                fragment = new ShopcartFragment();
                break;
            case 3:
                fragment = new OrderFragment();
                break;
            case 4:
                fragment = new MineFragment();
                break;
        }
        mFragmentList.add(fragment);
        return fragment;
    }
}
