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
 * 资料管理
 */
public class ActHdxt_ZLGL extends ActBase {

    @BindView(R.id.hdzcLayout)
    LinearLayout hdzcLayout;
    @BindView(R.id.bmzdLayout)
    LinearLayout bmzdLayout;
    @BindView(R.id.hdywzlLayout)
    LinearLayout hdywzlLayout;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("资料管理");
    }

    @OnClick({
            R.id.hdzcLayout, R.id.bmzdLayout, R.id.hdywzlLayout
    })
    public void doClick(View v) {
        Intent intent = new Intent(this, ActGrcx_DataList.class);

        switch (v.getId()) {
            case R.id.hdzcLayout:
                doToast("核对政策");
                break;
            case R.id.bmzdLayout:
                doToast("保密制度");
                break;
            case R.id.hdywzlLayout:
                doToast("核对业务资料");
                break;


        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__zlgl;
    }


}
