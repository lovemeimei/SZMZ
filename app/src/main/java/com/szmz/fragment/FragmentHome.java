package com.szmz.fragment;

import android.view.View;
import android.widget.RelativeLayout;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.utils.UIUtil;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHome extends BaseFragment{


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_jz;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({R.id.ll_dbywtz,R.id.ll_spyj,R.id.ll_fcsx})
    public void doClick(View v){
        switch (v.getId()){

            case R.id.ll_dbywtz:
                UIUtil.doToast("aa");
                break;
            case R.id.ll_spyj:
                UIUtil.doToast("bb");
                break;
            case R.id.ll_fcsx:
                UIUtil.doToast("cc");
                break;
        }
    }
}
