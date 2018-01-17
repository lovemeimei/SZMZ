package com.szmz.search;

import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.JZ_Search_workerDetail_Req;
import com.szmz.entity.response.JZ_Search_worker_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class ActSearchDetail extends ActBase {

    @BindView(R.id.tv_search_xm)
    TextView tvName;


    @BindView(R.id.tv_search_xb)
    TextView tvSex;


    @BindView(R.id.tv_search_sfzh)
    TextView tvCard;


    @BindView(R.id.tv_search_jtzz)
    TextView tvAdress;

    @BindView(R.id.tv_search_jzlx)
    TextView tvType;

    @BindView(R.id.tv_search_zpyy)
    TextView tvReason;


    @BindView(R.id.tv_search_sqsj)
    TextView tvTime;

    @BindView(R.id.tv_search_jjje)
    TextView tvMoney;


    @BindView(R.id.tv_search_jtrk)
    TextView tvPeopleNum;
    JZ_Search_worker_Res.ResultBean item;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_search_detail;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("历史记录");
        item = (JZ_Search_worker_Res.ResultBean) getIntent().getSerializableExtra("ResultBean");
        if (item != null) {
            doGetInfo(item);
        }
    }

    private void doGetInfo(JZ_Search_worker_Res.ResultBean item) {
        final JZ_Search_workerDetail_Req req = new JZ_Search_workerDetail_Req(item.id, getUser().getIdJZ());

        Call<JZ_Search_worker_Res> call = App.getApiProxyJZ().getJZ_SearchList_Detail(req);

        ApiUtil<JZ_Search_worker_Res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<JZ_Search_worker_Res>() {
            @Override
            public void doSuccess(JZ_Search_worker_Res result) {

                if (result != null) {
                    List<JZ_Search_worker_Res.ResultBean> list = result.Result;
                    if (list != null && list.size() > 0) {
                        doSetValue(list.get(0));
                    }
                }
            }

            @Override
            public void doAfter() {
                super.doAfter();
            }
        }, true);

        apiUtil.excute();
    }

    private void doSetValue(JZ_Search_worker_Res.ResultBean info) {
        tvName.setText(info.name);
        tvSex.setText(info.sex);
        tvCard.setText(info.idcard);
        tvAdress.setText(info.address);
        tvType.setText(info.salvationType);
        tvReason.setText(info.poorReason);
        tvTime.setText(info.applydate);
        tvMoney.setText(info.ensuremoney);
        tvPeopleNum.setText(info.population);
    }
}
