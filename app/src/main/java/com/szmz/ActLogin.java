package com.szmz;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.szmz.entity.User;
import com.szmz.entity.request.phoneLoginRequest;
import com.szmz.entity.response.phoneLoginR;
import com.szmz.net.ApiService;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.UIUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
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


        if (!doCheck()){
            return;
        }

        trans(ActMain.class);


//        final phoneLoginRequest request = new phoneLoginRequest(etUser.getText().toString().trim(),etPW.getText().toString().trim());
//        Call<phoneLoginR> call = App.getApiProxy().login(request);
//        ApiUtil<phoneLoginR> apiUtil = new ApiUtil<phoneLoginR>(context,call,new SimpleApiListener<List<phoneLoginR.ResultBean>>(){
//            @Override
//            public void doSuccess(List<phoneLoginR.ResultBean> result) {
//                super.doSuccess(result);
//
//              if (result!=null && result.size()>0){
//                  phoneLoginR.ResultBean bean = result.get(0);
//
//                  User user = new User();
//                  user.setUserName(etUser.getText().toString().trim());
//                  user.setPw(etPW.getText().toString().trim());
//                  user.setId(bean.getSystemMsg().getSystemID());
//                  //user.setRealName(bean.getPersonal().getRealName());
//                  App.getInstance().login(user);
//                  trans(ActMain.class);
//              }
//
//
//            }
//        },true);
//        apiUtil.excute();
    }

    private boolean doCheck(){
        if (TextUtils.isEmpty(etUser.getText().toString())){
            UIUtil.doToast("请输入用户名");
            return false;
        }
        if (TextUtils.isEmpty(etPW.getText().toString())){
            UIUtil.doToast("请输入密码");
            return false;
        }
        return true;
    }
}
