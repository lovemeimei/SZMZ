package com.szmz.user.check;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;

/**
 * 定位信息查询
 */
public class ActLocationInfo extends ActBase {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private String[] titles = {"工作人员","业务办理"};
    private Fragment[] fragments= new Fragment[2];

    @Override
    protected int getLayoutId() {
        return R.layout.activity_location_info;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setTitle("定位信息");

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new MyViewPagerAdatper(getSupportFragmentManager()));

    }

    class MyViewPagerAdatper extends FragmentPagerAdapter {

        public MyViewPagerAdatper(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return LocationListFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }

}
