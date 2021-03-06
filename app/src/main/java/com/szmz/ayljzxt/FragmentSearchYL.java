package com.szmz.ayljzxt;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.search.ActHistoryList;
import com.szmz.ywbl.dzda.ActYwbl_dzda_person;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 */

public class FragmentSearchYL extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void bindDatas() {

    }

    @OnClick({R.id.ll_search_sj, R.id.ll_search_dx})
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.ll_search_sj:
                trans(ActYZS_HistroyList.class);
                break;
            case R.id.ll_search_dx:

                trans(ActYzs_people_List.class);
                break;
        }
    }
}
