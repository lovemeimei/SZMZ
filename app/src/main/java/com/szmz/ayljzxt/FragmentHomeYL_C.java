package com.szmz.ayljzxt;

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

public class FragmentHomeYL_C extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_yzs_c;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({
            R.id.ll_SPJD,R.id.ll_zjff
    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.ll_SPJD:
                //审批进度
                trans(ActMsgList.class,"审批进度","");
                break;
            case R.id.ll_zjff:
                //资金发放
                trans(ActMsgList.class,"系统消息","");
                break;

        }
    }
}
