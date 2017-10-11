package com.szmz.ahdxt;

import android.content.Intent;
import android.view.View;

import com.szmz.ActMsgList;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.ahdxt.grcx.ActGrcx_DataList;
import com.szmz.ahdxt.jg.ActJG_ListBGDY;
import com.szmz.ahdxt.jg.ActJG_ListMGRY;
import com.szmz.ahdxt.jg.ActJG_ListYCCZ;
import com.szmz.ahdxt.jg.ActJG_Listywbl;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHD_JG extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__jg;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({
            R.id.bgdyLayout, R.id.ywblLayout, R.id.yczcLayout, R.id.mgryLayout
    })
    public void doClick(View v) {
        Intent intent = new Intent(getContext(), ActGrcx_DataList.class);

        switch (v.getId()) {
            case R.id.bgdyLayout:
                trans(ActJG_ListBGDY.class);
                break;
            case R.id.ywblLayout:
//                doToast("业务办理监管");
                trans(ActJG_Listywbl.class);
                break;
            case R.id.yczcLayout:
//                doToast("异常操作监管");
                trans(ActJG_ListYCCZ.class);
                break;
            case R.id.mgryLayout:
//                doToast("敏感人员监管");
                trans(ActJG_ListMGRY.class);
                break;

        }
    }
}
