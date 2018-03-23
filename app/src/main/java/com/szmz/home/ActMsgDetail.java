package com.szmz.home;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.CommMsgSave;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/11 0011上午 10:25
 */

public class ActMsgDetail extends ActBase{

    @BindView(R.id.tv_yzs_history_1)
    MyLayoutView view1;
    @BindView(R.id.tv_yzs_history_2)
    MyLayoutView view2;
    @BindView(R.id.tv_yzs_history_3)
    MyLayoutView view3;
    @BindView(R.id.tv_yzs_history_4)
    MyLayoutView view4;

    CommMsgSave item = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_msg_detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("消息");
        setLeftVisible(true);

        item =(CommMsgSave)getIntent().getSerializableExtra("item");
        if (item==null)
            return;
//        setTitle(item.getType());

        view1.doSetContent(item.getTitle());
        view2.doSetContent(item.getSender());
        view3.doSetContent(item.getType());
        view4.doSetContent(item.getContent());
    }
}
