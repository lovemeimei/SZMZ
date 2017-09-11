package com.szmz.fragment;

import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 */

public class FragmentJob extends BaseFragment {


    @OnClick({
            R.id.dchsLayout, R.id.mzpyLayout, R.id.shgsLayout, R.id.rhccLayout, R.id.dzdaLayout, R.id.spgshiLayout
    })
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.dchsLayout:

                break;
            case R.id.mzpyLayout:

                break;
            case R.id.shgsLayout:

                break;
            case R.id.rhccLayout:

                break;
            case R.id.dzdaLayout:

                break;
            case R.id.spgshiLayout:

                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl;
    }

    @Override
    protected void bindDatas() {

    }


}
