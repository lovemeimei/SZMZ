package com.szmz.fragment;

import android.content.Intent;
import android.view.View;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.search.ActHistoryList;
import com.szmz.search.ActListSYJZE;
import com.szmz.ywbl.dzda.ActYwbl_dzda_person;

import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 *
 *救助系统困难群众 数据查询
 */

public class FragmentSearchXX_C extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_c_xxcx;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({R.id.ll_search_sj, R.id.ll_search_dx})
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.ll_search_sj:
                trans(ActHistoryList.class);
                break;
            case R.id.ll_search_dx:
                //救助余额

                Intent intent = new Intent(getContext(),ActListSYJZE.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;

        }
    }
}
