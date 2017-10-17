package com.szmz.fragment;

import android.view.View;

import com.szmz.home.ActMsgList;
import com.szmz.BaseFragment;
import com.szmz.R;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHome extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_jz;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({R.id.ll_dbywtz, R.id.ll_spyj, R.id.ll_fcsx})
    public void doClick(View v) {
        switch (v.getId()) {

            case R.id.ll_dbywtz:
                trans(ActMsgList.class,"待办业务","");
                break;
            case R.id.ll_spyj:
                trans(ActMsgList.class,"审批意见","");
                break;
            case R.id.ll_fcsx:
                trans(ActMsgList.class,"复查事项","");
                break;
        }
    }
}
