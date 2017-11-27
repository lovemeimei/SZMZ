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
import com.szmz.ahdxt.ActDictList;
import com.szmz.entity.response.HD_JG_MGRY;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.Md5Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * 监管敏感人员
 */
public class ActJG_ListMGRY extends ActListBase {


    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<HD_JG_MGRY.ResultBean, MViewHolder> adapter;
    List<HD_JG_MGRY.ResultBean> items = new ArrayList<>();

    @BindView(R.id.tv_jg_search1)
    TextView tvSearch1;
    @BindView(R.id.tv_jg_search2)
    TextView tvSearch2;
    @BindView(R.id.et_jg_search1)
    EditText etSearch1;
    @BindView(R.id.et_jg_search2)
    TextView etSearch2;
    private String code = "";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_jc__list;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("敏感申请信息监管");
        setRightVisible(true);
        setRightShow("搜索");

        tvSearch1.setText("批次名称");
        tvSearch2.setText("业务类型");


        adapter = new BaseListAdapter<HD_JG_MGRY.ResultBean, MViewHolder>(this, R.layout.list_item_jg_dybg) {
            @Override
            protected void refreshView(int postion, final HD_JG_MGRY.ResultBean item, MViewHolder holer) {

                holer.tvSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, ActJG_ListMGRY2.class);
                        intent.putExtra("id", item.getBatchId());
                        startActivity(intent);
                    }
                });

                holer.tvName.setText(item.getBatchName());
                holer.tvType.setText(item.getBizCategory());
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
        String batchName = etSearch1.getText().toString().trim();


        if (isMore) {
            currentPage++;
        } else {
            currentPage = 1;
        }

        //sysadmin 510401
        String params = getParams(App.getInstance().getLoginUser().getAccountHD(), batchName, code);

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;" +
                "charset=UTF-8"), params.getBytes());

        Call<HD_JG_MGRY> call = App.getApiProxy().getJG_MGRY_List(body);

        ApiUtil<HD_JG_MGRY> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_JG_MGRY>() {

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefreshing();
                refresh.finishRefreshLoadMore();
            }

            @Override
            public void doSuccess(HD_JG_MGRY result) {

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

    @OnClick({R.id.tv_title_right, R.id.et_jg_search2})
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_right:
                loadInfo(false);
                break;
            case R.id.et_jg_search2:
                startActivityForResult(new Intent(ActJG_ListMGRY.this, ActDictList.class), 0);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            code = data.getStringExtra("code");
            etSearch2.setText(name);
        }
    }

    String getParams(String userid, String batchName, String bizCategoryCode) {

        String md5key = Md5Util.getMd5(userid + batchName + bizCategoryCode + currentPage + "20");
        StringBuilder sb = new StringBuilder();
        sb.append("userId=");
        sb.append(userid);
        sb.append("&");
        sb.append("batchName=");
        sb.append(batchName);
        sb.append("&");
        sb.append("bizCategoryCode=");
        sb.append(bizCategoryCode);
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
