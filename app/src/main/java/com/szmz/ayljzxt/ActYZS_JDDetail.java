package com.szmz.ayljzxt;

import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.widget.MyLayoutView;
import com.szmz.widget.StepProgressView;

import butterknife.BindView;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/29 0029下午 3:30
 */

public class ActYZS_JDDetail extends ActBase{

//    @BindView(R.id.stepView)
//    StepProgressView stepView;

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

    private String[] stepString = {"审批中","审批中","审批中","审批中"};
    @Override
    protected int getLayoutId() {
        return R.layout.activity_yzs_jd_detail1;
    }

    @Override
    protected void initUI() {
        super.initUI();
//        stepView.setStepDesc(new String[]{"审批中"})
        setLeftVisible(true);
        setTitle("进度信息");;
//        view1.doSetContent();
    }
}
