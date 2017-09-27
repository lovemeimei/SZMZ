package com.szmz.ahdxt.zlgl;

import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;

/**
 * 资料管理数据详细
 */
public class ActZlgl_Detial extends ActBase {


    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("详细信息");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_zlgl__detial;
    }


}
