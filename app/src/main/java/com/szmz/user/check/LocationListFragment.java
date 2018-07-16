package com.szmz.user.check;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.materiallistview.MaterialRefreshLayout;
import com.materiallistview.MaterialRefreshListener;
import com.szmz.App;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.ahdxt.BaseListFragment;
import com.szmz.ayljzxt.ActYZS_JDDetail;
import com.szmz.ayljzxt.FragmentSearchYL_C;
import com.szmz.entity.HdxtGrcxInfo;
import com.szmz.entity.request.JZ_GetLocation_List_req;
import com.szmz.entity.request.JZ_GetLocation_Users_req;
import com.szmz.entity.response.JZ_GetLocatinList_res;
import com.szmz.entity.response.JZ_GetLocation_Users_res;
import com.szmz.entity.response.YZSSQR_jd_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.DateUtil;
import com.szmz.widget.SearchEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationListFragment extends BaseListFragment<JZ_GetLocatinList_res.ResultBean>{
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    // TODO: Rename and change types of parameters
    private int mParam1;


    @BindView(R.id.tv_search_title)
    TextView tvUser;


    private List<JZ_GetLocation_Users_res.ResultBean> userItems = new ArrayList<>();
    JZ_GetLocation_Users_res.ResultBean user;
    MaterialDialog userDialog;
    MaterialDialog typeDialog;
    private Map<String ,String> typeValues = new HashMap<>();

    String dictId="";
    String keyWords="";


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_location_list;
    }

    @Override
    protected void doRefreshView(int p, JZ_GetLocatinList_res.ResultBean item, View view) {

        TextView tvTitle =(TextView) view.findViewById(R.id.tv_title);
        TextView time = (TextView)view.findViewById(R.id.tv_time);
        TextView address = (TextView)view.findViewById(R.id.tv_address);


        tvTitle.setText(item.getName());
        String mtime = DateUtil.getInstance().format(item.getSignTime());
        time.setText(mtime);
        address.setText(item.getAddress());
    }

    @Override
    protected int getListItemID() {
        return R.layout.list_item_location;
    }


    @Override
    protected void doMore(boolean isMore) {


        keyWords = searchEd.getText().toString().trim();
        String name = "";
        String type="";
        if (mParam1 == 0) {
            type="1";
        }else {
            type="2";
        }
        if (user != null) {
            name = user.getRealName();
        }
        final JZ_GetLocation_List_req req = new JZ_GetLocation_List_req(App.getInstance().getLoginUser().getIdJZ(),dictId,type,name,keyWords,1);
        Call<JZ_GetLocatinList_res> call = App.getApiProxyJZ().getPositionInfoList(req);
        ApiUtil<JZ_GetLocatinList_res> apiUtil = new ApiUtil<>(getContext(),call,new SimpleApiListener<JZ_GetLocatinList_res>(){
            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefresh();;
                refresh.finishRefreshLoadMore();
            }

            @Override
            public void doSuccess(JZ_GetLocatinList_res res) {
                List<JZ_GetLocatinList_res.ResultBean> result = res.Result;

                if (result != null && result.size() > 0) {
                    if (CurrentPage == 1) {
                        adapter.clearListData();
                    }

                    adapter.setListData(result);
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.GONE);
                    if (isHasNextPage(CurrentPage, PageSize, 20)) {
                        refresh.setLoadMore(true);
                    } else {
                        refresh.setLoadMore(false);
                    }
                } else {
                    adapter.clearListData();
                    adapter.setListData(new ArrayList<JZ_GetLocatinList_res.ResultBean>());
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.VISIBLE);
                }
            }
        },false);

        apiUtil.excute();
    }

    @Override
    protected void bindDates(View v) {
        super.bindDates(v);

        if (mParam1==1){
            typeValues.put("入户调查","20203028");
            typeValues.put("民主评议","20203029");
            typeValues.put("审核公示","20203030");
            typeValues.put("入户抽查","20203031");
            typeValues.put("审批公示","20203032");
            typeDialog = new MaterialDialog.Builder(getContext()).title("请选择")
                    .items(typeValues.keySet())
                    .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                            tvUser.setText(text);
                            dictId=typeValues.get(text);
                            doMore(false);
                            return true;
                        }
                    }).build();
        }


        refresh.autoRefresh();
    }


    @Override
    protected void doListItemOnClick(View view, JZ_GetLocatinList_res.ResultBean item) {

        Intent intent = new Intent(getContext(),ActLocationDeatai.class);
        intent.putExtra("id",item.getUserId());
        String mtime = DateUtil.getInstance().format(item.getSignTime());
        intent.putExtra("time",mtime);
        startActivity(intent);
    }

    private void initDialogUsers(){
        List<String> users =new ArrayList<>();
        for (JZ_GetLocation_Users_res.ResultBean item :userItems){
            users.add(item.getRealName());
        }
        userDialog =new MaterialDialog.Builder(getContext()).title("请选择")
                .items(users).itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {

                        tvUser.setText(text);
                        user = userItems.get(which);
                        doMore(false);
                        return true;
                    }
                }).build();
    }
    @Override
    protected void bindDatas() {
        final JZ_GetLocation_Users_req req = new JZ_GetLocation_Users_req(App.getInstance().getLoginUser().getIdJZ());

        Call<JZ_GetLocation_Users_res> call = App.getApiProxyJZ().getPositionUsers(req);
        ApiUtil<JZ_GetLocation_Users_res> apiUtil = new ApiUtil<>(getContext(),call,new SimpleApiListener(){
            @Override
            public void doSuccess(Object result) {
                JZ_GetLocation_Users_res res =(JZ_GetLocation_Users_res) result;
                if (res!=null&& res.Result!=null){
                    userItems = res.Result;
                    initDialogUsers();
                }

            }
        },true);
        apiUtil.excute();
    }

    @OnClick({R.id.tv_search_title})
    public void doClic(View v){
        if (mParam1==0){
            if (userDialog!=null){
                if (userDialog.isShowing()){
                    userDialog.dismiss();
                }else {userDialog.show();}
            }
        }else {
            if (typeDialog!=null){
                if (typeDialog.isShowing()){
                    typeDialog.dismiss();
                }else {typeDialog.show();}
            }
        }

    }

    public static LocationListFragment newInstance(int param1) {
        LocationListFragment fragment = new LocationListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

}
