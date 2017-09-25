package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.OnClick;

/**
 * 表单列表
 */
public class ActYBBDList extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybbdlist;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("已办任务");
    }
    @OnClick({
            R.id.tv_dbbd_jbxx,R.id.tv_dbbd_fymx,R.id.tv_dbbd_jzzl,R.id.tv_dbbd_spqy,R.id.tv_dbbd_yljz,R.id.tv_dbbd_zhxx
    })
    public void doClick(View v){
        switch (v.getId()){
            case  R.id.tv_dbbd_jbxx:
                //基本
                trans(ActYBBD_JBXX.class);
                break;
            case  R.id.tv_dbbd_zhxx:
                //账号
                trans(ActYBBD_ZHXX.class);
                break;
            case  R.id.tv_dbbd_yljz:
                //医疗救助
                trans(ActYBBD_YLJZXX.class);
                break;
            case  R.id.tv_dbbd_jzzl:
                //救助资料
                trans(ActYBBD_JZZL.class);
                break;
            case  R.id.tv_dbbd_fymx:
                //费用
                trans(ActYBBD_FYMX.class);
                break;
            case  R.id.tv_dbbd_spqy:
                //审批
                trans(ActYBBD_SPQY.class);
                break;
        }
    }
}
