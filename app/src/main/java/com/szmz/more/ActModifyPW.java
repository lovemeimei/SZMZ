package com.szmz.more;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.Comm_SQR_modifyPW_Req;
import com.szmz.entity.request.ModifyPW;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiListener;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.TextUtil;
import com.szmz.utils.UIUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

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
        setTitle("修改密码");
        setLeftVisible(true);
    }

    @OnClick(R.id.btn_submit)
    public void doClick(View view) {

        if (!doCheck())
            return;

        if(getUser().getType()==1){

            ModifyPW request = new ModifyPW(App.getInstance().getLoginUser().getUserName(),etOld.getText().toString().trim(),etNew.getText().toString().trim());


            Call<CommResponse> commResponseCall = App.getApiProxyCom().modifyPW(request);

            ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, commResponseCall,new SimpleApiListener<CommResponse>(){
                @Override
                public void doSuccess(CommResponse result) {

                    UIUtil.doToast("操作成功！");
                    myAnimFinish();
                }
            },true);

            apiUtil.excute();
        }else {

            Comm_SQR_modifyPW_Req request = new Comm_SQR_modifyPW_Req(getUser().getUserName(),etOld.getText().toString().trim(),etNew.getText().toString().trim());


//            Call<CommResponse> commResponseCall = App.getApiProxyComSQR().modifyPWSQR_XJ(request);
            Call<CommResponse> commResponseCall = App.getApiProxyComSQR().modifyPWSQR(request);

            ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, commResponseCall,new SimpleApiListener<CommResponse>(){
                @Override
                public void doSuccess(CommResponse result) {

                    UIUtil.doToast("操作成功！");
                    myAnimFinish();
                }
            },true);

            apiUtil.excute();
        }


    }

    private boolean doCheck(){

        if (TextUtils.isEmpty(etNew.getText().toString().trim())){
            UIUtil.doToast("请输入新密码");
            return false;
        }
        if (!TextUtil.isVailPw(etNew.getText().toString().trim())){
            UIUtil.doToast("密码为6-20位数字、字母或字符，至少包括数字和字符");
            return false;
        }
        if (TextUtils.isEmpty(etNew2.getText().toString().trim())){
            UIUtil.doToast("请输入确认密码");
            return false;
        }
        if (TextUtils.isEmpty(etOld.getText().toString().trim())){
            UIUtil.doToast("请输入旧密码");
            return false;
        }
        if (etNew.getText().toString().trim().equals(TextUtils.isEmpty(etNew2.getText().toString().trim()))){
            UIUtil.doToast("新密码两次输入不一致");
            return false;
        }
        return true;
    }

}