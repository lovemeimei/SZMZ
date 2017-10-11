package com.szmz.ahdxt;

import android.content.Intent;
import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.ahdxt.grcx.ActGrcx_DataList;
import com.szmz.ahdxt.jg.ActJG_ListBGDY;
import com.szmz.ahdxt.jg.ActJG_ListMGRY;
import com.szmz.ahdxt.jg.ActJG_ListYCCZ;
import com.szmz.ahdxt.jg.ActJG_Listywbl;
import com.szmz.ahdxt.tjfx.ActTJ_GXDWHDRC;
import com.szmz.ahdxt.tjfx.ActTJ_QYTJ;
import com.szmz.ahdxt.tjfx.ActTJ_YEQS;
import com.szmz.ahdxt.tjfx.ActTjfx_HDBGZS;
import com.szmz.ahdxt.tjfx.ActTjfx_HDDXZRS;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHD_TJ extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__tjfx;
    }

    @Override
    protected void bindDatas() {

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
//                doToast("共享单位核对人次");
                trans(
                        ActTJ_GXDWHDRC.class
                );
                break;

        }
    }
}
