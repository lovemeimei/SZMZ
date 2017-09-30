package com.szmz.ahdxt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActBase;
import com.szmz.ActListBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.response.HD_dict;
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


public class ActDictList extends ActListBase {


    @BindView(R.id.lv)
    ListView lv;

    List<HD_dict.ResultBean> items = new ArrayList<>();

    private BaseListAdapter<HD_dict.ResultBean,MViewHolder> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.comm_list;
    }



    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("请选择");

        adapter = new BaseListAdapter<HD_dict.ResultBean, MViewHolder>(this,R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, final HD_dict.ResultBean item, MViewHolder holer) {

                holer.tvName.setText(item.getDictName());
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("code",item.getDictCode());
                        intent.putExtra("name",item.getDictName());
                        setResult(RESULT_OK,intent);
                        myAnimFinish();
                    }
                });
            }

            @Override
            protected MViewHolder getHolder(View v) {
                MViewHolder holder = new MViewHolder();

                holder.tvName = (TextView)v.findViewById(R.id.tv_name);

                return holder;
            }
        };
        lv.setAdapter(adapter);

        refresh.setLoadMore(false);
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

        String params = getParams("510401","bussinessType");

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), params.getBytes());

        Call<HD_dict> call = App.getApiProxy().getDictYWLX(body);

        ApiUtil<HD_dict> apiUtil = new ApiUtil<>(this,call,new SimpleApiListener<HD_dict>(){

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefreshing();
            }

            @Override
            public void doSuccess(HD_dict result) {
                items = result.Result;
                if (items!=null && items.size()>0){
                    adapter.clearListData();
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();
                }
            }
        },false);


        apiUtil.excute();


    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

    }

    class MViewHolder{
        TextView tvName;
    }

    private String getParams(String userid,String dictClassify) {

        String md5key = Md5Util.getMd5(userid + dictClassify);
        StringBuilder sb = new StringBuilder();
        sb.append("userId=");
        sb.append(userid);
        sb.append("&");
        sb.append("dictClassify=");
        sb.append(dictClassify);
        sb.append("&");
        sb.append("Md5Key=");
        sb.append(md5key);
        return sb.toString();
    }

}
