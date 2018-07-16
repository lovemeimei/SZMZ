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

public class FragmentJobGS extends BaseFragment {


    @BindView(R.id.xzLayout)
    LinearLayout xzLayout;//乡镇权限布局
    @BindView(R.id.qxLayout)
    LinearLayout qxLayout;//区县权限布局
    @BindView(R.id.qtLayout)
    LinearLayout qtLayout;//其他权限布局


    @Override
    protected void bindDates(View v) {
        super.bindDates(v);
        User loginUser = App.getInstance().getLoginUser();
        if ("10101005".equals(loginUser.getRegionLevel())) {
            xzLayout.setVisibility(View.VISIBLE);
            qxLayout.setVisibility(View.GONE);
            qtLayout.setVisibility(View.GONE);
        } else if ("10101004".equals(loginUser.getRegionLevel())) {
            xzLayout.setVisibility(View.GONE);
            qxLayout.setVisibility(View.VISIBLE);
            qtLayout.setVisibility(View.GONE);
        } else {
            xzLayout.setVisibility(View.GONE);
            qxLayout.setVisibility(View.GONE);
            qtLayout.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({
            R.id.dchsLayout, R.id.mzpyLayout, R.id.shgsLayout, R.id.lxsjhiLayout1, R.id.dzdaLayout1,
            R.id.rhccLayout, R.id.spgshiLayout, R.id.dzdaLayout2, R.id.lxsjhiLayout2,
            R.id.dzdaLayout3,R.id.layout_jzfx,R.id.dbpc_layout,R.id.dbpc_layout2
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
            case R.id.dzdaLayout1:
                intent = new Intent(getActivity(), ActYwbl_dzda_person.class);
                startActivity(intent);
                break;
            case R.id.lxsjhiLayout1:
                intent = new Intent(getActivity(), ActLxsj.class);
                startActivity(intent);
                break;
            case R.id.rhccLayout:
                intent = new Intent(getActivity(), ActRhcc.class);
                startActivity(intent);
                break;
            case R.id.spgshiLayout:
                intent = new Intent(getActivity(), ActSpgs.class);
                startActivity(intent);
                break;
            case R.id.dzdaLayout2:
                intent = new Intent(getActivity(), ActYwbl_dzda_person.class);
                startActivity(intent);
                break;
            case R.id.lxsjhiLayout2:
                intent = new Intent(getActivity(), ActLxsj.class);
                startActivity(intent);
                break;
            case R.id.dzdaLayout3:
                intent = new Intent(getActivity(), ActYwbl_dzda_person.class);
                startActivity(intent);
                break;
            case R.id.dbpc_layout:
            case R.id.dbpc_layout2:
                //低保评测
                intent = new Intent(getActivity(), ActWebView.class);
                String url = "file:///android_asset/views/dealLowEvaluation.html";
                intent.putExtra("url",url);
                startActivity(intent);
                break;
            case R.id.layout_jzfx:
                //救助发现
                intent = new Intent(getActivity(), ActWebView.class);
                String url2 = "file:///android_asset/views/dealHelpFind.html";
                intent.putExtra("url",url2);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl2;
    }

    @Override
    protected void bindDatas() {

    }


}
