package com.szmz.search;

import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.JZ_SQR_HistoryDetailReq;
import com.szmz.entity.request.JZ_SQR_historyList_req;
import com.szmz.entity.request.JZ_Search_workerDetail_Req;
import com.szmz.entity.response.JZ_SQR_histroy_res;
import com.szmz.entity.response.JZ_SQR_histroy_res.ResultBean;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class ActSQR_SearchDetail extends ActBase {

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
    JZ_SQR_histroy_res.ResultBean item;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_search_detail;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("历史记录详情");
        item = (JZ_SQR_histroy_res.ResultBean) getIntent().getSerializableExtra("item");
        if (item != null) {
            doGetInfo(item);
        }
    }

    private void doGetInfo(JZ_SQR_histroy_res.ResultBean item) {
        final JZ_SQR_HistoryDetailReq req = new JZ_SQR_HistoryDetailReq(item.getId(), getUser().getIdJZ());

        Call<JZ_SQR_histroy_res> call = App.getApiProxyJZ().JZSQR_historyDetail(req);

        ApiUtil<JZ_SQR_histroy_res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<JZ_SQR_histroy_res>() {
            @Override
            public void doSuccess(JZ_SQR_histroy_res result) {

                if (result != null) {
                    List<JZ_SQR_histroy_res.ResultBean> list = result.Result;
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

    private void doSetValue(JZ_SQR_histroy_res.ResultBean info) {
        tvName.setText(info.getName());
        tvSex.setText(info.getSex());
        tvCard.setText(info.getIdcard());
        tvAdress.setText(info.getAddress());
        tvType.setText(info.getSalvationType());
        tvReason.setText(info.getPoorReason());
        tvTime.setText(info.getApplyDate());
        tvMoney.setText(info.getEnsuremoney());
        tvPeopleNum.setText(info.getPopulation());
    }
}
