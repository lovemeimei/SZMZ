package com.szmz.ayljzxt;

import android.content.Intent;
import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.ywbl.dzda.ActYwbl_dzda_person;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 */

public class FragmentJobYL extends BaseFragment {


    @OnClick({
            R.id.dchsLayout, R.id.mzpyLayout, R.id.shgsLayout, R.id.rhccLayout, R.id.dzdaLayout, R.id.spgshiLayout
    })
    public void doClick(View v) {
        Intent intent = new Intent(getActivity(), ActYwbl_dzda_person.class);

        switch (v.getId()) {
            case R.id.dchsLayout:
                intent.putExtra("Type", 1);
                startActivity(intent);
                break;
            case R.id.mzpyLayout:
                intent.putExtra("Type", 2);
                startActivity(intent);
                break;
            case R.id.shgsLayout:
                intent.putExtra("Type", 3);
                startActivity(intent);
                break;
            case R.id.rhccLayout:
                intent.putExtra("Type", 4);
                startActivity(intent);
                break;
            case R.id.dzdaLayout:
                intent.putExtra("Type", 0);
                startActivity(intent);
                break;
            case R.id.spgshiLayout:
                intent.putExtra("Type", 5);
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
