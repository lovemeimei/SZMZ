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
import com.szmz.SystemConst;
import com.szmz.entity.User;
import com.szmz.entity.request.Comm_modifyUserInfoSQR_Req;
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

public class ActModifyUserInfo_SQR extends ActBase{

    MaterialDialog sexDialog=null;
    @BindView(R.id.btn_submit)
    Button btnSub;
    @BindView(R.id.et_user_name)
    EditText etName;
    @BindView(R.id.et_user_card)
    EditText etCard;
    @BindView(R.id.et_user_tel)
    EditText etPhone;
    @BindView(R.id.et_user_address)
    EditText etAddress;
    @BindView(R.id.tv_user_sex)
    TextView etSex;
    String sex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_userinfo_sqr;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setLeftVisible(true);

            setRightVisible(true);
            setRightShow("编辑") ;

        setTitle("个人资料");

        btnSub.setVisibility(View.GONE);
        createDialog();

        etName.setEnabled(false);
        etAddress.setEnabled(false);
        etCard.setEnabled(false);
        etPhone.setEnabled(false);
        etSex.setEnabled(false);

        etName.setText(getUser().getRealName());
        etAddress.setText(getUser().getAdderss());
        etPhone.setText(getUser().getPhone());
        etCard.setText(getUser().getIdCode());
        sex = getUser().getSex();
        if (sex.equals("1")){
            etSex.setText("男");
        }else {
            etSex.setText("女");
        }


        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRightVisible(false);
                btnSub.setVisibility(View.VISIBLE);
                etName.setEnabled(true);
                etAddress.setEnabled(true);
                etCard.setEnabled(true);
                etPhone.setEnabled(true);
                etSex.setEnabled(true);
            }
        });
    }
    @OnClick({R.id.btn_submit,R.id.tv_user_sex})
    public void doClick(View v){
        switch (v.getId()){
            case R.id.btn_submit:
                modifyUserInfo();
                break;
            case R.id.tv_user_sex:
                sexDialog.show();
                break;
        }
    }

    private void createDialog(){
        sexDialog = new MaterialDialog.Builder(this)
                .alwaysCallSingleChoiceCallback()
                .items("男","女")
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        etSex.setText(text);
                        if (which==0){
                            sex="1";
                        }else {
                            sex="0";
                        }
                        return true;
                    }
                }).build();
    }

    private void modifyUserInfo(){

        String name = etName.getText().toString().trim();
        final String address = etAddress.getText().toString().trim();
        final String idCard = etCard.getText().toString().trim();
        final String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            doToast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(address)){
            doToast("请输入家庭住址");
            return;
        }
        if (TextUtils.isEmpty(phone)){
            doToast("请输入手机号");
            return;
        }
        if (!TextUtil.isMobileNumber(phone)){
            doToast("请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(idCard)){
            doToast("请输入身份证号码");
            return;
        }
        if (!TextUtil.isPersonCode(idCard)){
            doToast("请输入正确的身份证号码");
            return;
        }

        Comm_modifyUserInfoSQR_Req req = new Comm_modifyUserInfoSQR_Req(getUser().getUserName(),getUser().getPw(),name,sex,idCard,"",address);

        Call<CommResponse> call = App.getApiProxyComSQR().modifyUserInfoSQR(req);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                super.doSuccess(result);
                doToast("修改成功！");
                User user = App.getInstance().getLoginUser();
                user.setRealName(etName.getText().toString());
                user.setSex(sex);
                user.setIdCode(idCard);
                user.setAdderss(address);
                user.setPhone(phone);
                App.getInstance().login(user);
                myAnimFinish();
            }
        },true);

        apiUtil.excute();

    }
}
