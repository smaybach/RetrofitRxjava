package com.baway.liuheng.mywork.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lenovo on 2017/11/8.
 */

public class TabAdapter extends FragmentPagerAdapter {
    Context context;
    List<Fragment> list;
    String[] ls={"商品","旅游攻略"};

    public TabAdapter(FragmentManager fm, Context context, List<Fragment> list) {
        super(fm);
        this.context = context;
        this.list = list;
    }

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ls[position];
    }
}
