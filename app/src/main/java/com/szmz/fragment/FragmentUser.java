package com.szmz.fragment;

import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 */

public class FragmentUser  extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({
            R.id.ll_user_userinfo,R.id.ll_user_pw,R.id.ll_user_msg,R.id.ll_user_ywjd,R.id.ll_user_job
    })
    public void doClick(View v){
        switch (v.getId()){
            case R.id.ll_user_userinfo:

                break;
            case R.id.ll_user_pw:

                break;
            case R.id.ll_user_msg:

                break;
            case R.id.ll_user_ywjd:

                break;
            case R.id.ll_user_job:

                break;
        }
    }
}
