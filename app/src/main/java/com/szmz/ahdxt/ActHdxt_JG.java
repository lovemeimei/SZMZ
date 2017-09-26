package com.szmz.ahdxt;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.ahdxt.grcx.ActGrcx_DataList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 监管模块
 */
public class ActHdxt_JG extends ActBase {


    @BindView(R.id.bgdyLayout)
    LinearLayout bgdyLayout;
    @BindView(R.id.ywblLayout)
    LinearLayout ywblLayout;
    @BindView(R.id.yczcLayout)
    LinearLayout yczcLayout;
    @BindView(R.id.mgryLayout)
    LinearLayout mgryLayout;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("监管");
    }

    @OnClick({
            R.id.bgdyLayout, R.id.ywblLayout, R.id.yczcLayout, R.id.mgryLayout
    })
    public void doClick(View v) {
        Intent intent = new Intent(this, ActGrcx_DataList.class);

        switch (v.getId()) {
            case R.id.bgdyLayout:
                doToast("报告打印监管");
                break;
            case R.id.ywblLayout:
                doToast("业务办理监管");
                break;
            case R.id.yczcLayout:
                doToast("异常操作监管");
                break;
            case R.id.mgryLayout:
                doToast("敏感人员监管");
                break;

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__jg;
    }


}
