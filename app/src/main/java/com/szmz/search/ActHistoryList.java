package com.szmz.search;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.BaseRequest;
import com.szmz.entity.request.JZ_Search_worker_Req;
import com.szmz.entity.response.JZ_Comm_JZLX_RES;
import com.szmz.entity.response.JZ_Search_worker_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.TextUtil;
import com.szmz.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/11 0011下午 3:08
 */

public class ActHistoryList extends ActListBase {

    @BindView(R.id.lv)
    ListView lv;

    @BindView(R.id.tv_search_title)
    TextView tvSearchView;

    private String typeID = "";
    private String keyWords ="";

    BaseListAdapter<JZ_Search_worker_Res.ResultBean, ActHistoryList.MViewHolder> adapter;

    private List<JZ_Search_worker_Res.ResultBean> items = new ArrayList<>();


    @Override
    protected void initUI() {
        super.initUI();
        UIUtil.hideInputMethod(this);

        setLeftVisible(true);
        setTitle("历史记录");

        adapter = new BaseListAdapter<JZ_Search_worker_Res.ResultBean, ActHistoryList.MViewHolder>(this, R.layout.list_item_history) {
            @Override
            protected void refreshView(int postion, final JZ_Search_worker_Res.ResultBean item, ActHistoryList.MViewHolder holer) {


                holer.tvName.setText(item.name + "_" + item.type);
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        trans(ActSearchDetail.class);
                        Intent intent = new Intent(ActHistoryList.this, ActSearchDetail.class);
                        intent.putExtra("ResultBean", item);
                        startActivity(intent);
                    }
                });
            }

            @Override
            protected ActHistoryList.MViewHolder getHolder(View converView) {

                ActHistoryList.MViewHolder holder = new ActHistoryList.MViewHolder();
                holder.tvName = (TextView) converView.findViewById(R.id.tv_name);

                return holder;
            }
        };
        lv.setAdapter(adapter);
        adapter.setItems(items);

        getJZtype();
        tvSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pvOptions != null)
                    pvOptions.show();
            }
        });

        searchEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    String str = searchEd.getText().toString();
                   if (TextUtils.isEmpty(str)){
                        UIUtil.doToast("请输入姓名或者身份证号");
                       return true;
                   }else {
                       keyWords = str;
                       doLoadData();
                   }

                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comm_list_slt;
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

        currentPage = 1;
        getList();
    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
        currentPage++;
        getList();
    }

    class MViewHolder {
        TextView tvName;
    }

    private void getList() {
        final JZ_Search_worker_Req req = new JZ_Search_worker_Req(keyWords, getUser().getIdJZ(), "", "", typeID, currentPage);

        Call<JZ_Search_worker_Res> call = App.getApiProxyJZ().getJZ_SearchList(req);

        ApiUtil<JZ_Search_worker_Res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<JZ_Search_worker_Res>() {
            @Override
            public void doSuccess(JZ_Search_worker_Res result) {

                items = result.Result;
                if (items != null && items.size() > 0) {
                    noDataLayout.setVerticalGravity(View.GONE);
                    if (currentPage == 1) {
                        adapter.clearListData();
                    }
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();
                } else {
                    noDataLayout.setVisibility(View.VISIBLE);
                    adapter.clearListData();
                    adapter.notifyDataSetChanged();
                }
                if (isHasNextPage(currentPage, pageSize, result.TotalNum)) {
                    refresh.setLoadMore(true);
                } else {
                    refresh.setLoadMore(false);
                }
            }

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefresh();
                refresh.finishRefreshLoadMore();
            }
        }, false);

        apiUtil.excute();
    }

    //******************************************************************?/


    private List<JZ_Comm_JZLX_RES.ResultBean> options1Items = new ArrayList<>();
    private OptionsPickerView pvOptions;

    /**
     * 获取救助类型
     */
    private void getJZtype() {

        final BaseRequest req = new BaseRequest();

        Call<JZ_Comm_JZLX_RES> call = App.getApiProxyJZ().getJZLX(req);

        ApiUtil<JZ_Comm_JZLX_RES> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<JZ_Comm_JZLX_RES>() {

            @Override
            public void doSuccess(JZ_Comm_JZLX_RES result) {
                super.doSuccess(result);
                options1Items = result.Result;
                if (options1Items != null && options1Items.size() > 0)
                    initPickerView();
            }
        }, false);

        apiUtil.excute();
    }

    private void initPickerView() {

        JZ_Comm_JZLX_RES.ResultBean item = new JZ_Comm_JZLX_RES().new ResultBean();
        item.setType("全部");
        item.setId("");

        options1Items.add(0, item);
        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getType();
                tvSearchView.setText(tx);
                typeID = options1Items.get(options1).getId();
                getList();
            }

        })
                .setTitleText("请选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
//                .setBgColor(Color.BLACK)
//                .setTitleBgColor(Color.DKGRAY)
//                .setTitleColor(Color.LTGRAY)
//                .setCancelColor(Color.YELLOW)
//                .setSubmitColor(Color.YELLOW)
//                .setTextColorCenter(Color.LTGRAY)
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setLabels("省", "市", "区")
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();

        //pvOptions.setSelectOptions(1,1);
        pvOptions.setPicker(options1Items);//一级选择器
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }
}
