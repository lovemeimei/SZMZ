package com.szmz.fragment;

import android.text.TextUtils;
import android.view.View;

import com.szmz.App;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.more.ActBindPhone;
import com.szmz.more.ActBindPhone_Worker;
import com.szmz.more.ActModifyPW;
import com.szmz.more.ActModifyUserInfo;
import com.szmz.more.ActModifyUserInfo_SQR;
import com.szmz.more.ActMsgSet;

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
            R.id.miv_grzl, R.id.miv_xgmm,R.id.miv_bindphone,R.id.miv_msg
    })
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.miv_grzl:
                if (App.getInstance().getLoginUser().getType()==1){
                    trans(ActModifyUserInfo.class);
                }else {
                    trans(ActModifyUserInfo_SQR.class);
                }
                break;
            case R.id.miv_xgmm:
                trans(ActModifyPW.class);
                break;
            case R.id.miv_bindphone:
                if (TextUtils.isEmpty(App.getInstance().getLoginUser().getPhone())){
                    trans(ActBindPhone_Worker.class);
                }else {
                    trans(ActBindPhone.class);
                }
                break;
            case R.id.miv_msg:
                trans(ActMsgSet.class);
                break;
        }
    }
}
