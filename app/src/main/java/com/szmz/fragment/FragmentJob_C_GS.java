package com.szmz.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.szmz.ActWebView;
import com.szmz.App;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.entity.User;
import com.szmz.ywbl.ActDchs;
import com.szmz.ywbl.ActLxsj;
import com.szmz.ywbl.ActMzpy;
import com.szmz.ywbl.ActRhcc;
import com.szmz.ywbl.ActShgs;
import com.szmz.ywbl.ActSpgs;
import com.szmz.ywbl.dzda.ActYwbl_dzda_person;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 */

public class FragmentJob_C_GS extends BaseFragment {





    @Override
    protected void bindDates(View v) {
        super.bindDates(v);
        User loginUser = App.getInstance().getLoginUser();


    }

    @OnClick({
            R.id.layout_jzfx, R.id.layout_wyjz
    })
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.layout_jzfx:
                Intent intent = new Intent(getActivity(), ActWebView.class);
                String url = "file:///android_asset/views/dealHelpFind.html";
                intent.putExtra("url",url);
                startActivity(intent);
                break;
            case R.id.layout_wyjz:
                Intent intent2 = new Intent(getActivity(), ActWebView.class);
                String url2 = "file:///android_asset/views/dealHelp.html";
                intent2.putExtra("url",url2);
                startActivity(intent2);
                break;



        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_c;
    }

    @Override
    protected void bindDatas() {

    }


}
