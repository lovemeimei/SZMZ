package com.szmz.ahdxt;

import android.view.View;
import android.widget.LinearLayout;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.ahdxt.tjfx.ActTJ_QYTJ;
import com.szmz.ahdxt.tjfx.ActTJ_YEQS;
import com.szmz.ahdxt.tjfx.ActTjfx_HDBGZS;
import com.szmz.ahdxt.tjfx.ActTjfx_HDDXZRS;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 统计分析
 */
public class ActHdxt_TJFX extends ActBase {


    @BindView(R.id.hddxzsLayout)
    LinearLayout hddxzsLayout;
    @BindView(R.id.hdbgzsLayout)
    LinearLayout hdbgzsLayout;
    @BindView(R.id.hdywqsLayout)
    LinearLayout hdywqsLayout;
    @BindView(R.id.qyrctjLayout)
    LinearLayout qyrctjLayout;
    @BindView(R.id.gxdwhdrcLayout)
    LinearLayout gxdwhdrcLayout;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("统计分析");

    }

    @OnClick({
            R.id.hddxzsLayout, R.id.hdbgzsLayout, R.id.hdywqsLayout, R.id.qyrctjLayout, R.id.gxdwhdrcLayout
    })
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.hddxzsLayout:
//                doToast("核对对象总数");
                trans(
                        ActTjfx_HDDXZRS.class
                );
                break;
            case R.id.hdbgzsLayout:
//                doToast("核对报告总数");
                trans(
                        ActTjfx_HDBGZS.class
                );
                break;
            case R.id.hdywqsLayout:
//                doToast("核对业务趋势");
                trans(ActTJ_YEQS.class);
                break;
            case R.id.qyrctjLayout:
//                doToast("区域人次统计");
                trans(
                        ActTJ_QYTJ.class
                );
                break;
            case R.id.gxdwhdrcLayout:
                doToast("共享单位核对人次");
                break;

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__tjfx;
    }


}
