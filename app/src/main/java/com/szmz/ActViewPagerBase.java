package com.szmz;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * ViewPager基类
 * Created by bz on 2016/5/16.
 */
public abstract class ActViewPagerBase extends ActBase implements ViewPager.OnPageChangeListener {
    protected int currentPager = 0;

    protected abstract String[] getTitles();

    protected abstract List<View> getPagerView();

    public ViewPager viewPager = null;
    public MyPagerAdapter pagerAdapter = null;
    private PagerTabStrip pagerTabStrip;

    public class MyPagerAdapter extends PagerAdapter {
        public MyPagerAdapter(Context context) {
            super();
        }

        @Override
        public int getCount() {
            return getPagerView().size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(getPagerView().get(position));
            return getPagerView().get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getTitles()[position];
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        currentPager = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void initUI() {
        super.initUI();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            viewPager.setOnPageChangeListener(this);
        }
        pagerAdapter = new MyPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);


        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setTabIndicatorColor(ContextCompat.getColor(context,R.color.blue));
        pagerTabStrip.setTextColor(ContextCompat.getColor(context,R.color.blue));
        

    }
}
