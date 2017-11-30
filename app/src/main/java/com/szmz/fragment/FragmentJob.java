package com.szmz.fragment;

import android.content.Intent;
import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.ywbl.ActDchs;
import com.szmz.ywbl.ActMzpy;
import com.szmz.ywbl.ActRhcc;
import com.szmz.ywbl.ActShgs;
import com.szmz.ywbl.ActSpgs;
import com.szmz.ywbl.dzda.ActYwbl_dzda_person;

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
        Intent intent;

        switch (v.getId()) {
            case R.id.dchsLayout:
                intent = new Intent(getActivity(), ActDchs.class);
                startActivity(intent);
                break;
            case R.id.mzpyLayout:
                intent = new Intent(getActivity(), ActMzpy.class);
                startActivity(intent);
                break;
            case R.id.shgsLayout:
                intent = new Intent(getActivity(), ActShgs.class);
                startActivity(intent);
                break;
            case R.id.rhccLayout:
                intent = new Intent(getActivity(), ActRhcc.class);
                startActivity(intent);
                break;
            case R.id.dzdaLayout:
                intent = new Intent(getActivity(), ActYwbl_dzda_person.class);
                startActivity(intent);
                break;
            case R.id.spgshiLayout:
                intent = new Intent(getActivity(), ActSpgs.class);
                startActivity(intent);
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
