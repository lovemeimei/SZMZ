package com.szmz.ayljzxt;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.YZS_History_Detail_Req;
import com.szmz.entity.response.YZSSQR_jd_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.TextUtil;
import com.szmz.widget.MyLayoutView;
import com.szmz.widget.StepProgressView;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/29 0029下午 3:30
 */

public class ActYZS_JDDetail extends ActBase{

    @BindView(R.id.stepView)
    StepProgressView stepView;

    @BindView(R.id.ll_stepview)
    LinearLayout llStepView;
    @BindView(R.id.tv_yzs_history_1)
    MyLayoutView view1;
    @BindView(R.id.tv_yzs_history_2)
    MyLayoutView view2;
    @BindView(R.id.tv_yzs_history_3)
    MyLayoutView view3;
    @BindView(R.id.tv_yzs_history_4)
    MyLayoutView view4;

    private String id;
    private YZSSQR_jd_Res.ResultBean item;

    private String[] stepString = {"审批中"};
    @Override
    protected int getLayoutId() {
        return R.layout.activity_yzs_jd_detail2;
    }

    @Override
    protected void initUI() {
        super.initUI();
        id = getIntent().getStringExtra("id");
//        stepView.setStepDesc(new String[]{"审批中"})
        setLeftVisible(true);
        setTitle("进度信息");;
//        view1.doSetContent();
    }

    private void getInfo(){

        YZS_History_Detail_Req req = new YZS_History_Detail_Req(id);

        Call<YZSSQR_jd_Res> call = App.getApiProxyYZS().getYZS_jdDetail_SQR(req);

        ApiUtil<YZSSQR_jd_Res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<YZSSQR_jd_Res>(){
            @Override
            public void doSuccess(YZSSQR_jd_Res result) {
                super.doSuccess(result);
                if (result!=null)
                    item = result.Result.get(0);
                if (item!=null)
                    setInfo();
            }
        },true);

        apiUtil.excute();

    }

    private void  setInfo(){
        String all = item.getAllNode();
        if (TextUtils.isEmpty(all)){
            llStepView.setVisibility(View.GONE);
        }else {
            stepString = all.split(",");
            stepView.setStepDesc(stepString);
            String c = item.getCurrentNode();
            for (int i=0;i<stepString.length;i++){
                if (stepString[i].equals(c)){

                    stepView.setCurStepIndex(i);
                }
            }
        }

        view1.doSetContent(item.getNAME());
        view2.doSetContent(item.getCardID());
        view3.doSetContent(item.getTypeName());
        view4.doSetContent(item.getFLOW_RESULT_NAME());
    }

}
