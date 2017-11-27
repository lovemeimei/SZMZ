package com.szmz.ayljzxt;

import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 统计分析
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:37
 */

public class FragmentStatisticalYL extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tjfx_yzs;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({R.id.ll_tj_1,R.id.ll_tj_2,R.id.ll_tj_3})
    public void doClick(View v){

        switch (v.getId()){
            case R.id.ll_tj_1:
                trans(ActYZS_tj1.class);
                break;
            case R.id.ll_tj_2:
                trans(ActYZS_tj2.class);
                break;
            case R.id.ll_tj_3:
                trans(ActYZS_tj3.class);
                break;
        }
    }
}
