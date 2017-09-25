package com.szmz.fragment;

import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHome_C extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_jz_c;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({
            R.id.ll_SPJD,R.id.ll_zjff,R.id.ll_fctz,R.id.ll_hdjg,R.id.ll_spjg,R.id.ll_yhzc
    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.ll_SPJD:
                //审批进度
                break;
            case R.id.ll_zjff:
                //资金发放
                break;
            case R.id.ll_fctz:
                //复查通知
                break;
            case R.id.ll_hdjg:
                //核对结果
                break;
            case R.id.ll_spjg:
                //审批i结果
                break;
            case R.id.ll_yhzc:
                //优惠政策
                break;
        }
    }
}
