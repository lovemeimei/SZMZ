package com.szmz.ahdxt;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.barcode.decoding.Intents;
import com.orhanobut.logger.Logger;
import com.szmz.ActBase;
import com.szmz.ActWebView;
import com.szmz.R;
import com.szmz.SystemConst;
import com.szmz.entity.ScanCode;
import com.szmz.more.ActCodeLogin;
import com.szmz.more.ActCodeQZ;
import com.szmz.utils.GsonUtil;
import com.szmz.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 核对系统主界面
 */
public class ActHdxtMain extends ActBase {

    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(android.R.id.tabs)
    TabWidget tabs;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;
    private List<Fragment> mFragmentList;
    private String mTextArray[] = {"tab1", "tab2", "tab3", "tab4"};
    private Class[] mClass = {FragmentHomeHD.class, FragmentHD_JG.class, FragmentHD_TJ.class, FragmentUserHD.class};
    private Fragment mFragment[] = {new FragmentHomeHD(), new FragmentHD_JG(), new FragmentHD_TJ(), new FragmentUserHD()};
    int[] mDrawable = {R.drawable.slt_main_home_jz, R.drawable.slt_nav_xxcx, R.drawable.slt_main_tj_jz, R.drawable.slt_main_user_jz};
    private String[] mTitles = {"首页", "监管", "统计", "我的"};

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("核对系统");
        mFragmentList = new ArrayList<>();
        tvTitleRightScan.setVisibility(View.GONE);
        
        tabhost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabhost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mFragment.length; i++) {
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(mTitles[i]).setIndicator(getTabView(i));
            tabhost.addTab(tabSpec, mClass[i], null);
            mFragmentList.add(mFragment[i]);
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTitle(mTitles[tabhost.getCurrentTab()]);
                viewPager.setCurrentItem(tabhost.getCurrentTab());
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    setTitle("核对系统");
                } else {
                    setTitle(mTitles[position]);
                }
                tabhost.setCurrentTab(position);
                if (position == 0) {
                    tvTitleRightScan.setVisibility(View.GONE);
                } else {
                    tvTitleRightScan.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tvTitleRightScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan();
            }
        });
    }

    private View getTabView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.comm_tab_item, null);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        image.setImageResource(mDrawable[index]);
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText(mTitles[index]);
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_jz;
    }


    private void scan() {
        try {
            Intent intent = new Intent(Intents.Scan.ACTION);
            intent.putExtra(Intents.Scan.MODE, "QR_CODE_MODE");
            intent.putExtra(Intents.Scan.CHARACTER_SET, "GB2312");
            this.startActivityForResult(intent, REQUEST_CAPTURE);
        } catch (Exception e) {
            e.printStackTrace();
            UIUtil.doToast("打开摄像头失败");
        }
    }

    private static final int REQUEST_CAPTURE = 1024;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==REQUEST_CAPTURE){
            if (resultCode ==RESULT_OK){
                String resultStr = data.getStringExtra(Intents.Scan.RESULT);

                if (resultStr.startsWith("http://")){
                    //证件验证
                    Intent intent = new Intent(context,ActWebView.class);
                    intent.putExtra("url",resultStr);
                    startActivity(intent);
                }else{
                    ScanCode code = GsonUtil.deser(resultStr,ScanCode.class);
                    if (code!=null && code.getAction().equals("login")){
                        //登录
                        Intent intent = new Intent(context, ActCodeLogin.class);
                        intent.putExtra("msg",resultStr);
                        startActivity(intent);
                    }else if (code!=null && code.getAction().equals("seal")){
                        //签章
                        Intent intent = new Intent(context, ActCodeQZ.class);
                        intent.putExtra("msg",resultStr);
                        startActivity(intent);
                    }
                }
            }
        }

        super.onActivityResult(requestCode,resultCode,data);


    }
}
