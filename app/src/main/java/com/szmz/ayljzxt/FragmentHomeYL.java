package com.szmz.ayljzxt;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.barcode.decoding.Intents;
import com.materiallistview.MaterialRefreshLayout;
import com.materiallistview.MaterialRefreshListener;
import com.orhanobut.logger.Logger;
import com.szmz.ActMsgDetail;
import com.szmz.ActWebView;
import com.szmz.App;
import com.szmz.entity.ScanCode;
import com.szmz.entity.User;
import com.szmz.entity.request.YZS_todoList_Req;
import com.szmz.entity.response.YZS_todoList_Res;
import com.szmz.home.ActMsgList;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.more.ActCodeLogin;
import com.szmz.more.ActCodeQZ;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.GsonUtil;
import com.szmz.utils.MyBaseListAdapter;
import com.szmz.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

import static android.app.Activity.RESULT_OK;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHomeYL extends BaseFragment {

    @BindView(R.id.tv_name_gs)
    TextView tvName;

    @BindView(R.id.iv_scan_top)
    ImageView ivScan;

    @BindView(R.id.refresh)
    public MaterialRefreshLayout refresh;
    protected int currentPage = 1;
    @BindView(R.id.lv)
    ListView lv;

    private List<YZS_todoList_Res.ResultBean> items = new ArrayList<>();

    protected LinearLayout noDataLayout;
    protected TextView textView;

    private BaseListAdapter<YZS_todoList_Res.ResultBean,MViewHolder> adapter;

    class MViewHolder {

        TextView tvName;
    }
    public  void doRefresh(MaterialRefreshLayout materialRefreshLayout){
        currentPage=1;
        getList();
    };

    public  void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout){

        currentPage++;
        getList();
    };

    private void getList(){

        YZS_todoList_Req req = new YZS_todoList_Req(App.getInstance().getLoginUser().getAccountYZS(),currentPage);

        Call<YZS_todoList_Res> call = App.getApiProxyYZS().getYZS_TodoList(req);

        ApiUtil<YZS_todoList_Res> apiUtil = new ApiUtil<>(getContext(),call,new SimpleApiListener<YZS_todoList_Res>(){

            @Override
            public void doSuccess(YZS_todoList_Res result) {

                items = result.Result;
                if (items!=null && items.size()>0){
                    if (currentPage==1){
                        adapter.clearListData();
                    }
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();
                }else {
                    adapter.clearListData();
                    noDataLayout.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                }

                if (isHasNextPage(currentPage,20,result.TotalNum)){
                    refresh.setLoadMore(true);
                }else {
                    refresh.setLoadMore(false);
                }
            }

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefresh();
                refresh.finishRefreshLoadMore();
            }
        },false);

        apiUtil.excute();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yzs_home_gs;
    }

    @Override
    protected void bindDates(View v) {
        super.bindDates(v);
        noDataLayout = (LinearLayout)v.findViewById(R.id.noDataLayout);
        textView = (TextView)v.findViewById(R.id.textView);

        User loginUser = App.getInstance().getLoginUser();
        tvName.setText(loginUser.getRealName());

        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan();
            }
        });
    }

    @Override
    protected void bindDatas() {

        refresh.setLoadMore(false);
        refresh.finishRefreshLoadMore();
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                doRefresh(materialRefreshLayout);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                doRefreshLoadMore(materialRefreshLayout);
            }
        });


        adapter = new BaseListAdapter<YZS_todoList_Res.ResultBean, MViewHolder>(getContext(),R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, final YZS_todoList_Res.ResultBean item, MViewHolder holer) {

                holer.tvName.setText(item.getTitle());
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(),ActHomeDetail.class);
                        intent.putExtra("item",item);
                        startActivity(intent);
                    }
                });

            }

            @Override
            protected MViewHolder getHolder(View converView) {

                MViewHolder holder = new MViewHolder();
                holder.tvName =(TextView) converView.findViewById(R.id.tv_name);

                return holder;
            }
        };
        lv.setAdapter(adapter);
        doLoadData();
    }

    protected void doLoadData() {
        refresh.autoRefresh();
    }

    public static boolean isHasNextPage(int currentPage, int pageNumber, int totalItems) {
        int lastIndex = currentPage * pageNumber;
        if (lastIndex >= totalItems)
            return false;
        return true;
    }

    private void scan(){
        try{
            Intent intent = new Intent(Intents.Scan.ACTION);
            intent.putExtra(Intents.Scan.MODE, "QR_CODE_MODE");
            intent.putExtra(Intents.Scan.CHARACTER_SET, "GB2312");
            startActivityForResult(intent, REQUEST_CAPTURE);
        }catch (Exception e){
            e.printStackTrace();
            UIUtil.doToast("打开摄像头失败");
        }
    }

    private static final int REQUEST_CAPTURE = 1025;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

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
