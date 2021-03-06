package com.szmz.fragment;

import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.home.ActMsgList;
import com.szmz.more.ActModifyPW;
import com.szmz.more.ActModifyUserInfo;
import com.szmz.user.check.ActJZ_DC;
import com.szmz.user.check.ActLocationInfo;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 */

public class FragmentUser_GS extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_gs;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({
            R.id.ll_user_userinfo, R.id.ll_user_pw, R.id.ll_user_msg, R.id.ll_user_ywjd, R.id.ll_user_job
    })
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.ll_user_userinfo:
                trans(ActModifyUserInfo.class);
                break;
            case R.id.ll_user_pw:
                trans(ActModifyPW.class);
                break;
            case R.id.ll_user_msg:
                trans(ActMsgList.class,"我的消息","");
                break;
            case R.id.ll_user_ywjd:
                trans(ActJZ_DC.class);
                break;
            case R.id.ll_user_job:
                trans(ActLocationInfo.class);
                break;
        }
    }
}
