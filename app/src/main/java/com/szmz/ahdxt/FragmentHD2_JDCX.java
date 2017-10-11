package com.szmz.ahdxt;

import android.content.Intent;
import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.ahdxt.asqr.ActGrcx_JDCK_List;
import com.szmz.ahdxt.asqr.ActGrcx_SQXXCK_List;
import com.szmz.ahdxt.xxtz.ActXxtz_List;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHD2_JDCX extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__grcx__sqr;
    }

    @Override
    protected void bindDatas() {

    }
    @OnClick({
            R.id.jdckLayout, R.id.sqxxckLayout
    })
    public void doClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.jdckLayout:
                intent = new Intent(getContext(), ActGrcx_JDCK_List.class);
                startActivity(intent);
                break;
            case R.id.sqxxckLayout:
                intent = new Intent(getContext(), ActGrcx_SQXXCK_List.class);
                startActivity(intent);
                break;


        }
    }
}
