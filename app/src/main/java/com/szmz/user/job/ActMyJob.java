package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.OnClick;

/**
 * 我的工作
 */
public class ActMyJob extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.act_my_job;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("我的业务");
    }

    @OnClick({R.id.ll_user_job_db, R.id.ll_user_job_yb, R.id.ll_user_job_zt, R.id.ll_user_job_all})
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.ll_user_job_db:
                //待办
                trans(ActMyJobdb.class);
                break;
            case R.id.ll_user_job_yb:
                //已办
                trans(ActMyJobYB.class);
                break;
            case R.id.ll_user_job_zt:
                //在途
                break;
            case R.id.ll_user_job_all:
                //全部
                break;
        }
    }
}
