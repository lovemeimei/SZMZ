package com.szmz.ayljzxt;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActBase;
import com.szmz.ActListBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.SearchJZYE_Detal;
import com.szmz.entity.response.YZSSQR_JZYE_Res;
import com.szmz.utils.BaseListAdapter;
import com.szmz.widget.MyLayoutView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 救助余额查询
 */

public class ActYZSSQR_SYJZE_Detail extends ActBase {


    @BindView(R.id.tv_yzs_history_1)
    MyLayoutView view1;
    @BindView(R.id.tv_yzs_history_2)
    MyLayoutView view2;
    @BindView(R.id.tv_yzs_history_3)
    MyLayoutView view3;
    @BindView(R.id.tv_yzs_history_4)
    MyLayoutView view4;
    @BindView(R.id.tv_yzs_history_5)
    MyLayoutView view5;
    @BindView(R.id.tv_yzs_history_6)
    MyLayoutView view6;
    @BindView(R.id.tv_yzs_history_7)
    MyLayoutView view7;
    @BindView(R.id.tv_yzs_history_8)
    MyLayoutView view8;
    @BindView(R.id.tv_yzs_history_9)
    MyLayoutView view9;
   YZSSQR_JZYE_Res.ResultBean item = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_yzs__jzye__detail2;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setTitle("费用明细");
        setLeftVisible(true);

        item =( YZSSQR_JZYE_Res.ResultBean) getIntent().getSerializableExtra("item");

        setInfo();
    }

    private void setInfo(){

        view1.doSetContent(item.getAdminstrative_NAME());
        view2.doSetContent(item.getAccountAdminstrative_NAME());

        view3.doSetContent(item.getNAME());
        view4.doSetContent(item.getPERSON_ASSORT_NAME());
        view5.doSetContent(App.getInstance().getLoginUser().getIdCode());
        view6.doSetContent(item.getANNUAL());
        view7.doSetContent(item.getCATEGORY_NAME());
        view8.doSetContent(item.getTHIS_YEAR_BALANCE());
        view9.doSetContent(item.getId());
    }
}
