package com.szmz.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;

public class ActSearchDetail extends ActBase {

    @BindView(R.id.tv_search_xm)
    TextView tvName;


    @BindView(R.id.tv_search_xb)
    TextView tvSex;


    @BindView(R.id.tv_search_sfzh)
    TextView tvCard;


    @BindView(R.id.tv_search_jtzz)
    TextView tvAdress;

    @BindView(R.id.tv_search_jzlx)
    TextView tvType;

    @BindView(R.id.tv_search_zpyy)
    TextView tvReason;


    @BindView(R.id.tv_search_sqsj)
    TextView tvTime;

    @BindView(R.id.tv_search_jjje)
    TextView tvMoney;


    @BindView(R.id.tv_search_jtrk)
    TextView tvPeopleNum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_search_detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("历史记录");
    }
}
