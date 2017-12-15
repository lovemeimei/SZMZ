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
 * 个人查询
 */
public class ActHdxt_GRCX extends ActBase {

    @BindView(R.id.wddbLayout)
    LinearLayout wddbLayout;
    @BindView(R.id.wdybLayout)
    LinearLayout wdybLayout;
    @BindView(R.id.thwtLayout)
    LinearLayout thwtLayout;
    @BindView(R.id.zzthwtLayout)
    LinearLayout zzthwtLayout;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("我的业务");
    }

    @OnClick({
            R.id.wddbLayout, R.id.wdybLayout, R.id.thwtLayout, R.id.zzthwtLayout
    })
    public void doClick(View v) {
        Intent intent = new Intent(this, ActGrcx_DataList.class);

        switch (v.getId()) {
            case R.id.wddbLayout:
                intent.putExtra("Type", 1);
                startActivity(intent);
                break;
            case R.id.wdybLayout:
                intent.putExtra("Type", 2);
                startActivity(intent);
                break;
            case R.id.thwtLayout:
                intent.putExtra("Type", 3);
                startActivity(intent);
                break;
            case R.id.zzthwtLayout:
                intent.putExtra("Type", 4);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__grcx;
    }


}
