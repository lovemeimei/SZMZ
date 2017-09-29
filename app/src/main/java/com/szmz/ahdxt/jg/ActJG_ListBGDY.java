package com.szmz.ahdxt.jg;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.HD_JG_BGDY_RES;
import com.szmz.entity.SearchJZYE;
import com.szmz.entity.TestMode;
import com.szmz.net.ApiService;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.search.ActListSYJZE;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.Md5Util;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * 监管报告打印
 */
public class ActJG_ListBGDY extends ActListBase {


    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<HD_JG_BGDY_RES.ResultBean,MViewHolder> adapter;
    List<TestMode> items = new ArrayList<>();

    @BindView(R.id.tv_jg_search1)
    TextView tvSearch1;
    @BindView(R.id.tv_jg_search2)
    TextView tvSearch2;
    @BindView(R.id.et_jg_search1)
    EditText etSearch1;
    @BindView(R.id.et_jg_search2)
    TextView etSearch2;

    @BindView(R.id.et_jg_search3)
    TextView etSearch3;
    private String code,startTime,endTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_jcbgdy__list;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("打印报告监管");
        setRightVisible(true);
        setRightShow("搜索");

        adapter = new BaseListAdapter<HD_JG_BGDY_RES.ResultBean, MViewHolder>(this,R.layout.list_item_jg_dybg) {
            @Override
            protected void refreshView(int postion, final HD_JG_BGDY_RES.ResultBean item, MViewHolder holer) {

                holer.tvSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ActJG_ListBGDY.this,ActJg_BGDYJG.class);
                        intent.putExtra("object",item);
                        startActivity(intent);
                    }
                });

                holer.tvName.setText(item.getReportCode());
                holer.tvType.setText(item.getBizCategory());
            }

            @Override
            protected MViewHolder getHolder(View v) {

                MViewHolder holder = new MViewHolder();
                holder.tvType = (TextView)v.findViewById(R.id.tv_type);
                holder.tvName = (TextView)v.findViewById(R.id.tv_name);
                holder.tvSub = (TextView)v.findViewById(R.id.tv_submit);
                return holder;

            }
        };
        lv.setAdapter(adapter);

        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadInfo(false);
            }
        });
    }

    @OnClick({ R.id.et_jg_search2 , R.id.et_jg_search3 })
    void doClick(View v){
        switch (v.getId()){
            case R.id.et_jg_search2:

               Calendar cal=Calendar.getInstance();

                DatePickerDialog dialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            etSearch2.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                },cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
                dialog1.show();
                break;
            case R.id.et_jg_search3:
                Calendar ca2=Calendar.getInstance();

                DatePickerDialog dialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        etSearch3.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                },ca2.get(Calendar.YEAR),ca2.get(Calendar.MONTH),ca2.get(Calendar.DAY_OF_MONTH));
                dialog2.show();
                break;
        }
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

        loadInfo(false);
    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
        loadInfo(true);
    }

    private void loadInfo(final boolean isMore){

        code = etSearch1.getText().toString().trim();
        startTime = etSearch2.getText().toString().trim();
        endTime = etSearch3.getText().toString().trim();
        String params = "";
        if (isMore) {
            currentPage++;
        } else {
            currentPage = 1;
        }
        //sysadmin 510401
        params = getParams("sysadmin",code,startTime,endTime, currentPage);

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), params.getBytes());

        Call<HD_JG_BGDY_RES> call = App.getApiProxy().getJG_BGDYlist(body);

        ApiUtil<HD_JG_BGDY_RES> apiUtil = new ApiUtil<>(this,call,new SimpleApiListener<HD_JG_BGDY_RES>(){
            @Override
            public void doSuccess(HD_JG_BGDY_RES result) {

                List<HD_JG_BGDY_RES.ResultBean> items = result.Result;
                if (items!=null && items.size()>0){
                    if (currentPage==1){
                        adapter.clearListData();
                    }
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();

                }else {

                }
                if (isHasNextPage(currentPage, pageSize, result.TotalNum)) {
                    refresh.setLoadMore(true);
                } else {
                    refresh.setLoadMore(false);
                }
            }

            @Override
            public void doAfter() {

                refresh.finishRefreshing();
            }
        },false);

        apiUtil.excute();
    }

    private String getParams(String userid,String reportCode,String startTime,String endTime,  int currentPage) {

        String md5key = Md5Util.getMd5(userid + reportCode+startTime+endTime+currentPage + "20");
        StringBuilder sb = new StringBuilder();
        sb.append("userId=");
        sb.append(userid);
        sb.append("&");
        sb.append("reportCode=");
        sb.append(reportCode);
        sb.append("&");
        sb.append("startTime=");
        sb.append(startTime);
        sb.append("&");
        sb.append("endTime=");
        sb.append(endTime);
        sb.append("&");
        sb.append("CurrentPage=");
        sb.append(currentPage);
        sb.append("&");
        sb.append("PageSize=20&");
        sb.append("Md5Key=");
        sb.append(md5key);
        return sb.toString();
    }

    class MViewHolder{
        TextView tvName;
        TextView tvType;
        TextView tvSub;
    }
}
