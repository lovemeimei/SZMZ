package com.szmz.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.tjfx.ActJZ_1;
import com.szmz.tjfx.ActJZ_3;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 统计分析
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:37
 */

public class FragmentStatistical  extends BaseFragment {

    @BindView(R.id.ll_tj_1)
    LinearLayout ll1;
    @BindView(R.id.ll_tj_3)
    LinearLayout ll3;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tjfx;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({R.id.ll_tj_1,R.id.ll_tj_3})
    void doClick(View v){
        switch (v.getId()){
            case R.id.ll_tj_1:
                trans(ActJZ_1.class);
                break;
            case R.id.ll_tj_3:
                trans(ActJZ_3.class);
                break;
        }
    }
}
