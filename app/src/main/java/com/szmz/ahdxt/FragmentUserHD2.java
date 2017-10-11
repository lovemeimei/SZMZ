package com.szmz.ahdxt;

import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.more.ActModifyPW;
import com.szmz.more.ActModifyUserInfo;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 */

public class FragmentUserHD2 extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_hd;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({
            R.id.miv_grzl, R.id.miv_xgmm, R.id.miv_wdyw, R.id.miv_zlgl
    })
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.miv_grzl:
                trans(ActModifyUserInfo.class);
                break;
            case R.id.miv_xgmm:
                trans(ActModifyPW.class);
                break;
            case R.id.miv_wdyw:
                trans(ActHdxt_GRCX.class);
                break;
            case R.id.miv_zlgl:
                trans(ActHdxt_ZLGL.class);
                break;

        }
    }
}
