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

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.ayljzxt.ActYZS_JDDetail;
import com.szmz.ayljzxt.FragmentSearchYL_C;
import com.szmz.entity.response.YZSSQR_jd_Res;
import com.szmz.utils.BaseListAdapter;
import com.szmz.widget.SearchEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationListFragment extends BaseFragment implements TextView.OnEditorActionListener{
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    // TODO: Rename and change types of parameters
    private int mParam1;


    @BindView(R.id.refresh)
    public MaterialRefreshLayout refresh;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.search_ed)
    SearchEditText searchEditText;

    protected int currentPage = 1;
    @BindView(R.id.noDataLayout)
    protected LinearLayout noDataLayout;
    @BindView(R.id.textView)
    protected TextView textView;
    private BaseListAdapter<YZSSQR_jd_Res.ResultBean,LocationListFragment.MViewHolder> adapter;




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_location_list;
    }

    @Override
    protected void bindDatas() {

//        adapter = new BaseListAdapter<YZSSQR_jd_Res.ResultBean, LocationListFragment.MViewHolder>(getContext(),R.layout.list_item_user_check) {
//
//            @Override
//            protected void refreshView(int postion, final YZSSQR_jd_Res.ResultBean item, final LocationListFragment.MViewHolder holer) {
//                holer.tvName.setText(item.getTypeName()+"\t\t\t\t"+item.getFLOW_RESULT_NAME()+"\t\t\n"+item.getADD_TIME());
//
//                holer.tvBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent = new Intent(getContext(),ActYZS_JDDetail.class);
//                        intent.putExtra("id",item.getId());
//                        intent.putExtra("type",item.getType());
//                        startActivity(intent);
//                    }
//                });
//            };


        adapter = new BaseListAdapter<YZSSQR_jd_Res.ResultBean, MViewHolder>(getContext(),R.layout.comm_list) {
            @Override
            protected void refreshView(int postion, YZSSQR_jd_Res.ResultBean item, MViewHolder holer) {

            }

            @Override
            protected MViewHolder getHolder(View converView) {

                return new MViewHolder(converView);
            }
        };

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

    protected void doLoadData() {
        refresh.autoRefresh();
    }

    class MViewHolder{
        public MViewHolder(View viewroot) {
            ButterKnife.bind(viewroot);
        }

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.btn_done)
        TextView tvBtn;
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH){

            doLoadData();
        }
        return true;
    }
}
