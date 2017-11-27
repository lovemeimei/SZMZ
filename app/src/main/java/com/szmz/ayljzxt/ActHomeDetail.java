package com.szmz.ayljzxt;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.response.YZS_todoList_Res;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/24 0024上午 9:09
 */

public class ActHomeDetail extends ActBase{

    YZS_todoList_Res.ResultBean item;

    @BindView(R.id.tv_yzs_home_1)
    MyLayoutView view1;
    @BindView(R.id.tv_yzs_home_2)
    MyLayoutView view2;
    @BindView(R.id.tv_yzs_home_3)
    MyLayoutView view3;
    @BindView(R.id.tv_yzs_home_4)
    MyLayoutView view4;
    @BindView(R.id.tv_yzs_home_5)
    MyLayoutView view5;
    @BindView(R.id.tv_yzs_home_6)
    MyLayoutView view6;
    @BindView(R.id.tv_yzs_home_7)
    MyLayoutView view7;
    @BindView(R.id.tv_yzs_home_8)
    MyLayoutView view8;

    @Override
    protected int getLayoutId() {
        return R.layout.act_yzs_home_detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("详情");
        item = (YZS_todoList_Res.ResultBean)getIntent().getSerializableExtra("item");
        if (item==null)
            return;
        view1.doSetContent(item.getTitle());
        view2.doSetContent(item.getFlowName());
        view3.doSetContent(item.getNodeName());
        view4.doSetContent(item.getLab());
        view5.doSetContent(item.getStarterName());
        view6.doSetContent(item.getRDT());
        view7.doSetContent(item.getADT());
        view8.doSetContent(item.getSDT());
    }
}
