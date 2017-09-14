package com.szmz.more;

import android.view.View;
import android.widget.EditText;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/8 0008上午 9:33
 */
public class ActModifyPW extends ActBase {


    @BindView(R.id.et_old_pw)
    EditText etOld;
    @BindView(R.id.et_new_pw)
    EditText etNew;
    @BindView(R.id.et_new2_pw)
    EditText etNew2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_pw;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
    }

    @OnClick(R.id.btn_submit)
    public void doClick(View view) {

    }
}