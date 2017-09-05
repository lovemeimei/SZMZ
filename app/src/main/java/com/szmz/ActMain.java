package com.szmz;

import android.support.v4.app.FragmentTabHost;

import com.szmz.fragment.FragmentHome;
import com.szmz.fragment.FragmentJob;
import com.szmz.fragment.FragmentSearch;
import com.szmz.fragment.FragmentStatistical;
import com.szmz.fragment.FragmentUser;

import butterknife.BindView;

public class ActMain extends ActBase {


    @Override
    protected void initUI() {
        super.initUI();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_main;
    }
}
