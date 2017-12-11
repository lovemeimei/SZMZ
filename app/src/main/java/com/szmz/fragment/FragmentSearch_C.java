package com.szmz.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.materiallistview.MaterialRefreshLayout;
import com.materiallistview.MaterialRefreshListener;
import com.szmz.ActJZ_SQR_JD_Detail;
import com.szmz.App;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.entity.Jz_sqr_jd;
import com.szmz.entity.request.BaseRequest;
import com.szmz.entity.request.JZ_SQR_JD_RE;
import com.szmz.entity.response.JZ_Comm_JZLX_RES;
import com.szmz.entity.response.JZ_SQR_JD_RES;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.MyListPageAdapter;
import com.szmz.widget.SearchEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 * *救助系统困难群众 进度查询
 */

public class FragmentSearch_C extends BaseFragment {
    @BindView(R.id.refresh)
    public MaterialRefreshLayout refresh;
    @BindView(R.id.jslbTv)
    TextView jslbTv;
    @BindView(R.id.search_ed)
    SearchEditText searchEd;
    @BindView(R.id.dsLayout)
    LinearLayout dsLayout;
    @BindView(R.id.fLayout)
    LinearLayout fLayout;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.noDataLayout)
    LinearLayout noDataLayout;
    private MyListPageAdapter adapter;
    private String keyWords = "";
    protected int CurrentPage = 1;
    protected int PageSize = 20;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_c_jd;
    }

    @Override
    protected void bindDatas() {

    }

    @Override
    protected void bindDates(View v) {
        super.bindDates(v);
        if (searchEd != null) {
            searchEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId,
                                              KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH
                            || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                        doLoadData();
                        return true;
                    }
                    return false;
                }
            });
        }
        adapter = new MyListPageAdapter<Jz_sqr_jd>(getActivity(), R.layout.list_item_user_check) {
            @Override
            protected void refreshView(int position, final Jz_sqr_jd item, View listItemView) {
                TextView tv_content = (TextView) listItemView.findViewById(R.id.tv_content);
                tv_content.setText(item.getTypeName());
                TextView tv_btn = (TextView) listItemView.findViewById(R.id.tv_btn);
                tv_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ActJZ_SQR_JD_Detail.class);
                        intent.putExtra("JZ_SQR_JD", item);
                        startActivity(intent);


                    }
                });

            }
        };
        listView.setAdapter(adapter);
        refresh.setLoadMore(false);
        refresh.finishRefreshLoadMore();
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                doMore(false);
            }


            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                doMore(true);
            }
        });
//        getJZtype();
//        jslbTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (pvOptions != null)
//                    pvOptions.show();
//            }
//        });
        doLoadData();
    }


    protected void doLoadData() {
        refresh.autoRefresh();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private void doMore(boolean isMore) {
        if (isMore) {
            CurrentPage++;
        } else {
            CurrentPage = 1;
        }
        keyWords = searchEd.getText().toString().trim();
        doGetData(typecode, keyWords, CurrentPage);
    }


    private void doGetData(String typecode, String keyWords, final int CurrentPage) {


        JZ_SQR_JD_RE request = new JZ_SQR_JD_RE(typecode, keyWords, CurrentPage);


        Call<JZ_SQR_JD_RES> call = App.getApiProxyJZ().get_JZ_SQR_JD_LIST(request);


        ApiUtil<JZ_SQR_JD_RES> apiUtil = new ApiUtil<>(getActivity(), call, new SimpleApiListener<JZ_SQR_JD_RES>() {
            @Override
            public void doSuccess(JZ_SQR_JD_RES result) {

                List<Jz_sqr_jd> items = result.Result;
                if (items != null && items.size() > 0) {
                    if (CurrentPage == 1) {
                        adapter.clearListData();
                    }
                    adapter.setListData(items);
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.GONE);
                    if (isHasNextPage(CurrentPage, PageSize, result.TotalNum)) {
                        refresh.setLoadMore(true);
                    } else {
                        refresh.setLoadMore(false);
                    }
                } else {
                    adapter.clearListData();
                    adapter.setListData(new ArrayList<Jz_sqr_jd>());
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void doAfter() {
                refresh.finishRefreshing();
                refresh.finishRefreshLoadMore();
            }
        }, false);

        apiUtil.excute();


    }

    public boolean isHasNextPage(int currentPage, int pageNumber, int totalItems) {
        int lastIndex = currentPage * pageNumber;
        if (lastIndex >= totalItems)
            return false;
        return true;
    }

    private List<JZ_Comm_JZLX_RES.ResultBean> options1Items = new ArrayList<>();
    private OptionsPickerView pvOptions;
    private String typecode = "";

    /**
     * 获取救助类型
     */
    private void getJZtype() {

        final BaseRequest req = new BaseRequest();

        Call<JZ_Comm_JZLX_RES> call = App.getApiProxyJZ().getJZLX(req);

        ApiUtil<JZ_Comm_JZLX_RES> apiUtil = new ApiUtil<>(getActivity(), call, new SimpleApiListener<JZ_Comm_JZLX_RES>() {

            @Override
            public void doSuccess(JZ_Comm_JZLX_RES result) {
                super.doSuccess(result);
                options1Items = result.Result;
                if (options1Items != null && options1Items.size() > 0) {
                    initPickerView();
                    refresh.autoRefresh();
                }

            }
        }, false);

        apiUtil.excute();
    }

    private void initPickerView() {

        JZ_Comm_JZLX_RES.ResultBean item = new JZ_Comm_JZLX_RES().new ResultBean();
        item.setType("全部");
        item.setId("");

        options1Items.add(0, item);
        pvOptions = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getType();
                jslbTv.setText(tx);
                typecode = options1Items.get(options1).getId();
                doMore(false);
            }

        })
                .setTitleText("请选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();

        pvOptions.setPicker(options1Items);//一级选择器
    }
}
