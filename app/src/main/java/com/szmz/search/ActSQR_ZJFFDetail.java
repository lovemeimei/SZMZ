package com.szmz.search;

import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.JZ_Search_workerDetail_Req;
import com.szmz.entity.response.JZSQR_zjff_res;
import com.szmz.entity.response.JZ_SQR_histroy_res;
import com.szmz.entity.response.JZ_Search_worker_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.widget.MyLayoutView;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class ActSQR_ZJFFDetail extends ActBase {


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
    JZSQR_zjff_res.ResultBean item = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jzsqr_zjff_detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("资金发放详情");
        setLeftVisible(true);

        item =( JZSQR_zjff_res.ResultBean) getIntent().getSerializableExtra("item");

        setInfo();
    }

    private void setInfo(){

        view1.doSetContent(item.getGrantType());
        view2.doSetContent(item.getGrantDate());

        view3.doSetContent(item.getGrantMode());
        view4.doSetContent(item.getRescueType());
        view5.doSetContent(item.getTypeTag());
        view6.doSetContent(item.getPersonNum()+"");
        view7.doSetContent(item.getEnsureMoney()+"元");
        view8.doSetContent(item.getGrantStatus());
        view9.doSetContent(item.getReason());
    }
}
