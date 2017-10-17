package com.szmz.more;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.JZ_Comm_modifyInfo;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/8 0008上午 9:46
 */

public class ActModifyUserInfo extends ActBase{

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
        etAddress.setEnabled(false);
        etCard.setEnabled(false);
        etPhone.setEnabled(false);
        etSex.setEnabled(false);


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
                        return true;
                    }
                }).build();
    }

    private void modifyUserInfo(){

        JZ_Comm_modifyInfo req = new JZ_Comm_modifyInfo(App.getInstance().getLoginUser().getPersonId(),"emaile","officePhone");

        Call<CommResponse> call = App.getApiProxyCom().modifyInfo(req);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                super.doSuccess(result);
            }
        },true);

        apiUtil.excute();

    }
}
