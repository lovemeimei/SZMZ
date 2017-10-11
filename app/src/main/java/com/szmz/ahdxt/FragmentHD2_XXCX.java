package com.szmz.ahdxt;

import android.content.Intent;
import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.ahdxt.asqr.ActXXTZ_List_SQR;
import com.szmz.ahdxt.xxtz.ActXxtz_List;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHD2_XXCX extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__xxtz__sqr;
    }

    @Override
    protected void bindDatas() {

    }
    @OnClick({
            R.id.sqztLayout, R.id.bgmxLayout
    })
    public void doClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.sqztLayout:
                intent = new Intent(getContext(), ActXXTZ_List_SQR.class);
                intent.putExtra("Type", 1);
                startActivity(intent);
                break;
            case R.id.bgmxLayout:
                intent = new Intent(getContext(), ActXXTZ_List_SQR.class);
                intent.putExtra("Type", 2);
                startActivity(intent);
                break;

        }
    }
}
