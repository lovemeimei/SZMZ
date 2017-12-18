package com.szmz.home;


import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.CommMsgSave;
import com.szmz.entity.response.JZ_Todolist;
import com.szmz.widget.MyLayoutView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/11 0011上午 10:25
 */

public class ActMsgDetail_JZ extends ActBase{

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
    JZ_Todolist.ResultBean item = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_msg_detail_jz;
    }

    @Override
    protected void initUI() {
        super.initUI();

        item =(JZ_Todolist.ResultBean)getIntent().getSerializableExtra("item");
        setTitle("详情");
        setLeftVisible(true);
        view1.doSetContent(item.getTitle());
        view2.doSetContent(item.getTaskId());
        view3.doSetContent(item.getProcessDefinitionName());
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(item.getTaskCreateTime());
        view4.doSetContent(sdf.format(date));

        view6.doSetContent(item.getDescription());
//        view5.doSetContent(item.getProcessTaskDueTime());
        view7.doSetContent(item.getTaskName());
    }
}
