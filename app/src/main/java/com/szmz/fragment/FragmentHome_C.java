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

public class FragmentHome_C extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_jz_c;
    }

    @Override
    protected void bindDatas() {

    }

    //1)	审批进度  10204030
//2)	资金发放  10204040
//            3)	复查提醒  10204050
//            4)	信访通知  10204060
//            5)	系统通知  10204070

    @OnClick({
            R.id.ll_SPJD, R.id.ll_zjff, R.id.ll_fctz, R.id.ll_hdjg, R.id.ll_yhzc
    })
    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.ll_SPJD:
                //审批进度
                trans(ActMsgList.class, "审批进度", "");
                break;
            case R.id.ll_zjff:
                //资金发放
                trans(ActMsgList.class, "资金发放", "");
                break;
            case R.id.ll_fctz:
                //复查通知
                trans(ActMsgList.class, "复查提醒", "");

                break;
            case R.id.ll_hdjg:
                //核对结果
                trans(ActMsgList.class, "信访通知", "");

                break;

            case R.id.ll_yhzc:
                trans(ActMsgList.class, "系统通知", "");

                //优惠政策
                break;
        }
    }
}
