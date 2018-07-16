package com.szmz.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.barcode.decoding.Intents;
import com.orhanobut.logger.Logger;
import com.szmz.ActWebView;
import com.szmz.App;
import com.szmz.entity.ScanCode;
import com.szmz.entity.User;
import com.szmz.home.ActMsgList;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.home.ActMsgListNormal;
import com.szmz.more.ActCodeLogin;
import com.szmz.more.ActCodeQZ;
import com.szmz.utils.GsonUtil;
import com.szmz.utils.UIUtil;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHome extends BaseFragment {


    @BindView(R.id.tv_name_gs)
    TextView tvName;

    @BindView(R.id.iv_scan_top)
    ImageView ivScan;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_jz;
    }

    @Override
    protected void bindDatas() {
        User loginUser = App.getInstance().getLoginUser();
        tvName.setText(loginUser.getRealName());


        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan();
            }
        });
    }

    @OnClick({R.id.ll_dbywtz, R.id.ll_spyj, R.id.ll_fcsx})
    public void doClick(View v) {
        switch (v.getId()) {

            case R.id.ll_dbywtz:
                trans(ActMsgList.class,"待办业务","");
                break;
            case R.id.ll_spyj:
                trans(ActMsgListNormal.class,"审批意见","");
                break;
            case R.id.ll_fcsx:
                trans(ActMsgListNormal.class,"复查事项","");
                break;
        }
    }

    private void scan(){
        try{
            Intent intent = new Intent(Intents.Scan.ACTION);
            intent.putExtra(Intents.Scan.MODE, "QR_CODE_MODE");
            intent.putExtra(Intents.Scan.CHARACTER_SET, "GB2312");
            this.startActivityForResult(intent, REQUEST_CAPTURE);
        }catch (Exception e){
            e.printStackTrace();
            UIUtil.doToast("打开摄像头失败");
        }
    }

    private static final int REQUEST_CAPTURE = 1024;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Logger.d("aa");
        if (requestCode==REQUEST_CAPTURE){
            if (resultCode ==RESULT_OK){
                String resultStr = data.getStringExtra(Intents.Scan.RESULT);

                if (resultStr.startsWith("http://")){
                    //证件验证
                    Intent intent = new Intent(getContext(),ActWebView.class);
                    intent.putExtra("url",resultStr);
                    startActivity(intent);
                }else {
                    ScanCode code = GsonUtil.deser(resultStr,ScanCode.class);
                    if (code!=null && code.getAction().equals("login")){
                        //登录
                        Intent intent = new Intent(getContext(), ActCodeLogin.class);
                        intent.putExtra("msg",resultStr);
                        startActivity(intent);
                    }else if (code!=null && code.getAction().equals("seal")){
                        //签章
                        Intent intent = new Intent(getContext(), ActCodeQZ.class);
                        intent.putExtra("msg",resultStr);
                        startActivity(intent);
                    }
                }

            }
        }

        super.onActivityResult(requestCode,resultCode,data);


    }
}
