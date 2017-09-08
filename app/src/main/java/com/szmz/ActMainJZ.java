package com.szmz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.barcode.decoding.Intents;
import com.szmz.fragment.FragmentHome;
import com.szmz.fragment.FragmentJob;
import com.szmz.fragment.FragmentSearch;
import com.szmz.fragment.FragmentStatistical;
import com.szmz.fragment.FragmentUser;
import com.szmz.utils.UIUtil;

import butterknife.BindView;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:38
 */

public class ActMainJZ extends ActBase implements TabHost.OnTabChangeListener {

    @BindView(R.id.tabhost)
    FragmentTabHost mTabHost;

    String mTextArray[] = {"tab1", "tab2", "tab3", "tab4","tab5"};
    Class[] mfragmets = {FragmentHome.class, FragmentSearch.class, FragmentJob.class, FragmentStatistical.class, FragmentUser.class};
    int[] mDrawable = {R.drawable.slt_main_home_jz, R.drawable.slt_main_search_jz, R.drawable.slt_main_job_jz, R
            .drawable.slt_main_tj_jz,R.drawable.slt_main_user_jz};

    @Override
    protected void initUI() {
        super.initUI();
        UIUtil.doToast("aa");
        initTabHost();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_jz;
    }

    @Override
    protected void onResume() {
        super.onResume();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        int count = mfragmets.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
                    .setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, mfragmets[i], null);
        }
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.setOnTabChangedListener(this);
    }


    private View getTabItemView(int index) {

        View mView = getLayoutInflater().inflate(R.layout.comm_tab_item, null);
        ImageView imageView = (ImageView) mView.findViewById(R.id.iv_tab);
        imageView.setImageResource(mDrawable[index]);
        TextView tvName = (TextView) mView.findViewById(R.id.tv_tab);
        switch (index) {
            case 0:
                tvName.setText("首页");
                break;
            case 1:
                tvName.setText("数据查询");
                break;
            case 2:
                tvName.setText("业务办理");
                break;
            case 3:
                tvName.setText("统计分析");
                break;
            case 4:
                tvName.setText("我的");
                break;
        }

        return mView;
    }

    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }

        if (tabId.equals("tab1")) {
            tvTitle.setText("首页");
        } else if (tabId.equals("tab2")) {
            tvTitle.setText("数据查询");

        } else if (tabId.equals("tab3")) {
            tvTitle.setText("业务办理");

        } else if (tabId.equals("tab4")) {
            tvTitle.setText("统计分析");

        }else if (tabId.equals("tab5")){
            tvTitle.setText("我的");
        }

    }

//    private static final int REQUEST_CAPTURE = 1024;
//    try{
//        Intent intent = new Intent(Intents.Scan.ACTION);
//        intent.putExtra(Intents.Scan.MODE, "QR_CODE_MODE");
//        intent.putExtra(Intents.Scan.CHARACTER_SET, "GB2312");
//        MainActivity.this.startActivityForResult(intent, REQUEST_CAPTURE);
//    }catch (Exception e){
//        Toast.makeText(MainActivity.this,"打开摄像头失败",Toast.LENGTH_SHORT).show();
//    }
}
