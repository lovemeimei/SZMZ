package com.szmz.ahdxt.jg;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.SystemConst;
import com.szmz.entity.response.HD_JG_YWBL2;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.Md5Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * 监管报告打印
 */
public class ActJG_Listywbl2 extends ActListBase {


    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<HD_JG_YWBL2.ResultBean, MViewHolder> adapter;
    List<HD_JG_YWBL2.ResultBean> items = new ArrayList<>();

    @BindView(R.id.tv_jg_search1)
    TextView tvSearch1;
    @BindView(R.id.tv_jg_search2)
    TextView tvSearch2;
    @BindView(R.id.et_jg_search1)
    EditText etSearch1;
    @BindView(R.id.et_jg_search2)
    TextView etSearch2;
    String name = "";
    String code = "";
    private String id;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_act_jc__list2;

    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("申请信息");
        setRightVisible(true);
        setRightShow("搜索");

        id = getIntent().getStringExtra("id");


        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadInfo(false);
            }
        });

        adapter = new BaseListAdapter<HD_JG_YWBL2.ResultBean, MViewHolder>(this, R.layout.list_item_jg_dybg) {
            @Override
            protected void refreshView(int postion, final HD_JG_YWBL2.ResultBean item, MViewHolder holer) {

                holer.tvSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ActJG_Listywbl2.this, ActJG_YWBL.class);
                        intent.putExtra("item", item);
                        startActivity(intent);
                    }
                });

                holer.tvName.setText(item.getApplyName());
                holer.tvType.setText(item.getIdCardNo());
            }

            @Override
            protected MViewHolder getHolder(View v) {

                MViewHolder holder = new MViewHolder();
                holder.tvType = (TextView) v.findViewById(R.id.tv_type);
                holder.tvName = (TextView) v.findViewById(R.id.tv_name);
                holder.tvSub = (TextView) v.findViewById(R.id.tv_submit);
                return holder;

            }
        };
        lv.setAdapter(adapter);

        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

        loadInfo(false);
    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

        loadInfo(true);
    }


    void loadInfo(boolean isMore) {
        name = etSearch1.getText().toString().trim();
        code = etSearch2.getText().toString().trim();
        if (isMore) {

            currentPage++;
        } else {
            currentPage = 1;
        }

        //sysadmin 510401
        String params = getParams(App.getInstance().getLoginUser().getAccountHD(), id, name, code);

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), params.getBytes());

        Call<HD_JG_YWBL2> call = App.getApiProxy().getJG_ywblList2(body);

        ApiUtil<HD_JG_YWBL2> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_JG_YWBL2>() {

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefreshing();
                refresh.finishRefreshLoadMore();
            }

            @Override
            public void doSuccess(HD_JG_YWBL2 result) {

                items = result.Result;

                if (items != null && items.size() > 0) {

                    if (currentPage == 1) {
                        adapter.clearListData();
                    }
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();


                } else {
                    adapter.clearListData();
                    adapter.notifyDataSetChanged();
                }

                if (isHasNextPage(currentPage, pageSize, result.TotalNum)) {
                    refresh.setLoadMore(true);
                } else {
                    refresh.setLoadMore(false);
                }
            }
        }, false);
        apiUtil.excute();

    }


    class MViewHolder {
        TextView tvName;
        TextView tvType;
        TextView tvSub;
    }

    String getParams(String userid, String batchId, String applyName, String idCardNo) {

        String md5key = Md5Util.getMd5(userid + batchId + applyName +idCardNo+ currentPage + "20");
        StringBuilder sb = new StringBuilder();
        sb.append("userId=");
        sb.append(userid);
        sb.append("&");
        sb.append("batchId=");
        sb.append(batchId);
        sb.append("&");
        sb.append("applyName=");
        sb.append(applyName);
        sb.append("&");
        sb.append("idCardNo=");
        sb.append(idCardNo);
        sb.append("&");
        sb.append("CurrentPage=");
        sb.append(currentPage);
        sb.append("&");
        sb.append("PageSize=20&");
        sb.append("Md5Key=");
        sb.append(md5key);
        return sb.toString();
    }
}
