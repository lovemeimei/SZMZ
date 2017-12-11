package com.szmz.ayljzxt;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.response.YZSSQR_HomeList_Res;
import com.szmz.entity.response.YZS_todoList_Res;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/24 0024上午 9:09
 */

public class ActHomeDetail_sqr extends ActBase{

    YZSSQR_HomeList_Res.ResultBean item;

    @BindView(R.id.tv_yzs_home_1)
    MyLayoutView view1;
    @BindView(R.id.tv_yzs_home_2)
    MyLayoutView view2;
    @BindView(R.id.tv_yzs_home_3)
    MyLayoutView view3;

    @Override
    protected int getLayoutId() {
        return R.layout.act_yzs_home_detail_sqr;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("详情");
        item = (YZSSQR_HomeList_Res.ResultBean)getIntent().getSerializableExtra("item");
        if (item==null)
            return;
        view1.doSetContent(item.getNOTE_TITLE());
        view2.doSetContent(item.getADD_TIME());
        view3.doSetContent(item.getNOTE_CONTENT());
    }
}
