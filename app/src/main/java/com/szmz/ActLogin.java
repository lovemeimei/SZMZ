package com.szmz;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.szmz.entity.request.phoneLoginRequest;
import com.szmz.entity.response.phoneLoginR;
import com.szmz.net.ApiService;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 登录
 */
public class ActLogin extends ActBase {

    @BindView(R.id.et_username)
    EditText etUser;
    @BindView(R.id.et_pw)
    EditText etPW;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initUI() {
        super.initUI();
    }

    @OnClick(R.id.btn_submit)
    public void doSubmit(View v){
//        trans(ActMain.class);

        phoneLoginRequest request = new phoneLoginRequest("admin","123456");
        Call<phoneLoginR> call = App.getApiProxy().login(request);
//        Call<phoneLoginR> call = App.getApiProxy().login2("admin","123456","admin123456");
        ApiUtil<phoneLoginR> apiUtil = new ApiUtil<phoneLoginR>(context,call,new SimpleApiListener<phoneLoginR>(){
            @Override
            public void doSuccess(phoneLoginR result) {
                super.doSuccess(result);
            }
        },true);
        apiUtil.excute();
    }
}
