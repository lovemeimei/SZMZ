package com.szmz.ahdxt;

import android.content.Intent;
import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.ahdxt.xxtz.ActXxtz_List;

import butterknife.OnClick;

/**
 * 核对系统工作人员首页
 */

public class FragmentHomeHD extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_hd;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({R.id.ml_xxtz, R.id.ml_db, R.id.ml_thwt, R.id.ml_mgryjg})
    public void doClick(View v) {

        Intent intent = new Intent(getContext(), ActXxtz_List.class);
        switch (v.getId()) {

            case R.id.ml_xxtz://核对待处理
                intent.putExtra("Type", 1);
                startActivity(intent);
                break;
            case R.id.ml_db://复核待处理
                intent.putExtra("Type", 2);
                startActivity(intent);
                break;
            case R.id.ml_thwt://超时提醒
                intent.putExtra("Type", 3);
                startActivity(intent);
                break;
            case R.id.ml_mgryjg://敏感名单审核提醒
                intent.putExtra("Type", 4);
                startActivity(intent);
                break;
        }
    }
}
