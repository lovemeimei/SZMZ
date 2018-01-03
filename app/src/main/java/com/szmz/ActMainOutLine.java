package com.szmz;

import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.support.v4.app.FragmentManager;

import com.szmz.fragment.FragmentJob;

import butterknife.BindView;

public class ActMainOutLine extends ActBase {


    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    FragmentJob fragmentJob = new FragmentJob();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_main_out_line;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setRightVisible(false);
        setLeftVisible(false);
        setTitle("社会救助办理");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragmentJob);
        fragmentTransaction.commit();
    }
}
