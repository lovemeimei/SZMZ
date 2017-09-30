package com.szmz.ahdxt.jg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.response.CommResponse;
import com.szmz.entity.response.HD_JG_MGRY2;
import com.szmz.entity.response.HD_JG_MGRY2;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.Md5Util;
import com.szmz.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * 监管敏感人员
 */
public class ActJG_ListMGRY2 extends ActListBase {


    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<HD_JG_MGRY2.ResultBean,MViewHolder> adapter;
    List<HD_JG_MGRY2.ResultBean> items = new ArrayList<>();
    
    private String id;


    @Override
    protected int getLayoutId() {
        return R.layout.comm_list;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("敏感人员");

        id = getIntent().getStringExtra("id");

        adapter = new BaseListAdapter<HD_JG_MGRY2.ResultBean, MViewHolder>(this,R.layout.list_item_jg_mgry) {
            @Override
            protected void refreshView(int postion, final HD_JG_MGRY2.ResultBean item, MViewHolder holer) {

                holer.tvSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ActJG_ListMGRY2.this,ActJG_MGRY.class);
                        intent.putExtra("item",item);
                        startActivity(intent);
                    }
                });
                holer.tvSHENHE.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MaterialDialog dialog = new MaterialDialog.Builder(ActJG_ListMGRY2.this)
                                .title("提示")
                                .content("是否进行核对")
                                .onNegative(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                        doSH(item.getApplyId(),"0");
                                    }
                                })
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                    doSH(item.getApplyId(),"1");
                                    }
                                })
                                .negativeText("取消")
                                .positiveText("确定")
                                .build();
                        dialog.show();
                    }
                });

                holer.tvName.setText(item.getApplyName());
                holer.tvType.setText(item.getIdCardNo());
            }

            @Override
            protected MViewHolder getHolder(View v) {

                MViewHolder holder = new MViewHolder();
                holder.tvType = (TextView)v.findViewById(R.id.tv_type);
                holder.tvName = (TextView)v.findViewById(R.id.tv_name);
                holder.tvSub = (TextView)v.findViewById(R.id.tv_submit);
                holder.tvSHENHE = (TextView)v.findViewById(R.id.tv_shenhe);
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


        if (isMore) {
            currentPage++;
        } else {
            currentPage = 1;
        }

        //sysadmin 510401
        String params = getParams("sysadmin", id);

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;" +
                "charset=UTF-8"), params.getBytes());

        Call<HD_JG_MGRY2> call = App.getApiProxy().getJG_MGRY_List2(body);

        ApiUtil<HD_JG_MGRY2> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_JG_MGRY2>() {

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefreshing();
            }

            @Override
            public void doSuccess(HD_JG_MGRY2 result) {

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

    class MViewHolder{
        TextView tvName;
        TextView tvType;
        TextView tvSub;
        TextView tvSHENHE;
    }

    String getParams(String userid, String batchId) {

        String md5key = Md5Util.getMd5(userid + batchId  + currentPage + "20");
        StringBuilder sb = new StringBuilder();
        sb.append("userId=");
        sb.append(userid);
        sb.append("&");
        sb.append("batchId=");
        sb.append(batchId);
        sb.append("&");
        sb.append("CurrentPage=");
        sb.append(currentPage);
        sb.append("&");
        sb.append("PageSize=20&");
        sb.append("Md5Key=");
        sb.append(md5key);
        return sb.toString();
    }
    String getParamsSH(String userid, String batchId,String applyId,String approvalCode) {

        String md5key = Md5Util.getMd5(userid + batchId  + applyId + approvalCode);
        StringBuilder sb = new StringBuilder();
        sb.append("userId=");
        sb.append(userid);
        sb.append("&");
        sb.append("batchId=");
        sb.append(batchId);
        sb.append("&");
        sb.append("applyId=");
        sb.append(applyId);
        sb.append("&");
        sb.append("approvalCode=");
        sb.append(approvalCode);
        sb.append("&");
        sb.append("Md5Key=");
        sb.append(md5key);
        return sb.toString();
    }

    private void doSH(String applyId,String approvalCode){
        //sysadmin 510401
        String params = getParamsSH("sysadmin", id,applyId,approvalCode);

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;" +
                "charset=UTF-8"), params.getBytes());

        Call<CommResponse> call = App.getApiProxy().getJG_MGRY_SH(body);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<CommResponse>() {

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefreshing();
            }

            @Override
            public void doSuccess(CommResponse result) {
                        super.doSuccess(result);
                loadInfo(false);
            }
        }, false);
        apiUtil.excute();
    }
}
