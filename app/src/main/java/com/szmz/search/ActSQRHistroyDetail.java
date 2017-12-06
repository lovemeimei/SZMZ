package com.szmz.search;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.response.JZ_SQR_histroy_res;
import com.szmz.entity.response.YZSSQR_JZYE_Res;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/1 0001下午 3:21
 */

public class ActSQRHistroyDetail extends ActBase{

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
    JZ_SQR_histroy_res.ResultBean item = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jzsqr_history_detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("信息详情");
        setLeftVisible(true);

        item =( JZ_SQR_histroy_res.ResultBean) getIntent().getSerializableExtra("item");

        setInfo();
    }

    private void setInfo(){

        view1.doSetContent(item.getName());
        view2.doSetContent(item.getSex());

        view3.doSetContent(item.getIdcard());
        view4.doSetContent(item.getAddress());
        view5.doSetContent(item.getSalvationType());
        view6.doSetContent(item.getPoorReason());
        view7.doSetContent(item.getApplyDate());
        view8.doSetContent(item.getPopulation());
        view9.doSetContent(item.getEnsuremoney());
    }
}

