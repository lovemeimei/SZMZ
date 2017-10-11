package com.szmz.ahdxt;

import android.content.Intent;
import android.view.View;

import com.szmz.ActMsgList;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.ahdxt.xxtz.ActXxtz_List;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHomeHD extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_hd;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({R.id.ml_xxtz, R.id.ml_db, R.id.ml_thwt,R.id.ml_mgryjg})
    public void doClick(View v) {

        Intent intent = new Intent(getContext(), ActXxtz_List.class);
        switch (v.getId()) {

            case R.id.ml_xxtz:
                intent.putExtra("Type", 1);
                startActivity(intent);
                break;
            case R.id.ml_db:
                intent.putExtra("Type", 2);
                startActivity(intent);
                break;
            case R.id.ml_thwt:
                intent.putExtra("Type", 3);
                startActivity(intent);
                break;
            case R.id.ml_mgryjg:
                intent.putExtra("Type", 4);
                startActivity(intent);
                break;
        }
    }
}
