package com.szmz.fragment;

import android.view.View;

import com.szmz.ActMsgList;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.more.ActModifyPW;
import com.szmz.more.ActModifyUserInfo;
import com.szmz.user.check.ActCheckList;
import com.szmz.user.job.ActMyJob;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 *
 */

public class FragmentUse_C extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_c;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({
            R.id.ll_user_userinfo, R.id.ll_user_pw
    })
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.ll_user_userinfo:
                trans(ActModifyUserInfo.class);
                break;
            case R.id.ll_user_pw:
                trans(ActModifyPW.class);
                break;
        }
    }
}
