package com.szmz.ahdxt;

import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.ahdxt.asqr.ActHdzc_List_SQR;
import com.szmz.more.ActBindPhone;
import com.szmz.more.ActModifyPW;
import com.szmz.more.ActModifyUserInfo_SQR;
import com.szmz.more.ActMsgSet;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 */

public class FragmentUserHD2 extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_hd2;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({
            R.id.miv_grzl, R.id.miv_xgmm, R.id.miv_bindphone, R.id.miv_msg, R.id.miv_hdzc
    })
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.miv_grzl:
                trans(ActModifyUserInfo_SQR.class);
                break;
            case R.id.miv_xgmm:
                trans(ActModifyPW.class);
                break;
            case R.id.miv_bindphone:
                trans(ActBindPhone.class);
                break;
            case R.id.miv_msg:
                trans(ActMsgSet.class);
                break;
            case R.id.miv_hdzc:
                trans(ActHdzc_List_SQR.class);
                break;
        }
    }
}
