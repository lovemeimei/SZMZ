package com.szmz.ahdxt;

import android.content.Intent;
import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.ahdxt.asqr.ActHdxt_HDZC_SQR;
import com.szmz.ahdxt.asqr.ActHdxt_XXTZ_SQR;
import com.szmz.ahdxt.xxtz.ActXxtz_List;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHomeHD2 extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_hd2;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({R.id.ml_xxtz,R.id.ml_hdzc})
    public void doClick(View v) {

        switch (v.getId()) {

            case R.id.ml_xxtz:
                trans(ActHdxt_XXTZ_SQR.class);
                break;
            case R.id.ml_hdzc:
                trans(ActHdxt_HDZC_SQR.class);
                break;

        }
    }
}
