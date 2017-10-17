package com.szmz.more;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.utils.TextUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/7 0007下午 4:49
 */
public class ActModifyPhone extends ActBase {

    @BindView(R.id.et_phonenum)
    EditText etPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_modify_phone;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("更改手机号码");
    }

    @OnClick(R.id.btn_submit)
    public void doClick(View v) {

        if (TextUtils.isEmpty(etPhone.getText().toString().trim())){
            doToast("请输入手机号");
            return;
        }
        if (!TextUtil.isMobileNumber(etPhone.getText().toString().trim())){
            doToast("不是有效的手机号");
            return;
        }
        Intent intent =new Intent(context,ActModifyPhone2.class);
        intent.putExtra("num",etPhone.getText().toString().trim());
        startActivity(intent);
        myAnimFinish();
    }
}
