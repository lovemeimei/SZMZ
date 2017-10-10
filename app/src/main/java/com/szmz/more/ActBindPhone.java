package com.szmz.more;

import android.view.View;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/10 0010下午 4:53
 */

public class ActBindPhone extends ActBase{

    @BindView(R.id.tv_phonenum)
    TextView tvPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.act_bindphone;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("绑定手机号");

    }

    @OnClick(R.id.btn_submit)
    public void doClick(View v){
        trans(ActModifyPhone.class);
    }
}
