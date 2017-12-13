package com.szmz.more;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.User;
import com.szmz.entity.request.JZ_Comm_modifyInfo;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.TextUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/8 0008上午 9:46
 */

public class ActModifyUserInfo extends ActBase {

    MaterialDialog sexDialog = null;
    @BindView(R.id.btn_submit)
    Button btnSub;

    @BindView(R.id.et_user_name)
    EditText etName;
    @BindView(R.id.et_user_email)
    EditText etEmail;
    @BindView(R.id.et_user_tel)
    EditText etPhone;
    @BindView(R.id.et_user_depart)
    TextView etDepart;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_userinfo;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setRightVisible(true);
        setRightShow("编辑");
        setTitle("个人资料");
        btnSub.setVisibility(View.GONE);
        createDialog();

        etName.setEnabled(false);
        etEmail.setEnabled(false);
        etPhone.setEnabled(false);

        etName.setText(App.getInstance().getLoginUser().getRealName());
        etDepart.setText(App.getInstance().getLoginUser().getDepartName());
        etEmail.setText(App.getInstance().getLoginUser().getEmail());
        etPhone.setText(App.getInstance().getLoginUser().getOfficePhone());

        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRightVisible(false);
                btnSub.setVisibility(View.VISIBLE);
                etPhone.setEnabled(true);
                etEmail.setEnabled(true);
            }
        });
    }

    @OnClick({R.id.btn_submit})
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                modifyUserInfo();
                break;
//            case R.id.tv_user_sex:
//                sexDialog.show();
//                break;
        }
    }

    private void createDialog() {
        sexDialog = new MaterialDialog.Builder(this)
                .alwaysCallSingleChoiceCallback()
                .items("男", "女")
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {

                        return true;
                    }
                }).build();
    }

    private void modifyUserInfo() {

        String email = etEmail.getText().toString().trim();
        String officePhone = etPhone.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            doToast("邮箱不能为空");
            return;
        }

        if (!TextUtil.isEMailAddress(email)) {
            doToast("请输入正确的邮箱格式");
            return;
        }
        if (TextUtils.isEmpty(officePhone)) {
            doToast("电话不能为空");
            return;
        }

        JZ_Comm_modifyInfo req = new JZ_Comm_modifyInfo(App.getInstance().getLoginUser().getPersonId(), email, officePhone);

        Call<CommResponse> call = App.getApiProxyCom().modifyInfo(req);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<CommResponse>() {
            @Override
            public void doSuccess(CommResponse result) {
                doToast("修改成功");
                User user = App.getInstance().getLoginUser();
                user.setEmail(etEmail.getText().toString().trim());
                user.setOfficePhone(etPhone.getText().toString().trim());
                App.getInstance().login(user);
                myAnimFinish();
            }
        }, true);

        apiUtil.excute();

    }
}
