package com.szmz.ywbl.dzda;

import android.view.View;
import android.widget.LinearLayout;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.YwblDzdaFamilyMember;
import com.szmz.entity.YwblDzdaMemberDeformityInfo;
import com.szmz.entity.YwblDzdaMemberIncomeInfo;
import com.szmz.entity.YwblDzdaMemberInsureInfo;
import com.szmz.entity.YwblDzdaMemberSalaryInfo;
import com.szmz.utils.MyListPageAdapter;
import com.szmz.widget.ListViewInScrollView;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 家庭成员信息详细
 */
public class ActYwbl_dzda_jtcyxx_detail extends ActBase {


    @BindView(R.id.sfzhView)
    MyLayoutView sfzhView;
    @BindView(R.id.nameView)
    MyLayoutView nameView;
    @BindView(R.id.xbView)
    MyLayoutView xbView;
    @BindView(R.id.csrqView)
    MyLayoutView csrqView;
    @BindView(R.id.yhzgxView)
    MyLayoutView yhzgxView;
    @BindView(R.id.sfbzdxView)
    MyLayoutView sfbzdxView;
    @BindView(R.id.ryfljzlbView)
    MyLayoutView ryfljzlbView;
    @BindView(R.id.ysrView)
    MyLayoutView ysrView;
    @BindView(R.id.mzView)
    MyLayoutView mzView;
    @BindView(R.id.hyzkView)
    MyLayoutView hyzkView;
    @BindView(R.id.cysrxxListView)
    ListViewInScrollView cysrxxListView;
    @BindView(R.id.cysrxxLayout)
    LinearLayout cysrxxLayout;
    @BindView(R.id.cydyxxListView)
    ListViewInScrollView cydyxxListView;
    @BindView(R.id.cydyxxLayout)
    LinearLayout cydyxxLayout;
    @BindView(R.id.cycjxxListView)
    ListViewInScrollView cycjxxListView;
    @BindView(R.id.cycjxxLayout)
    LinearLayout cycjxxLayout;
    @BindView(R.id.cycbxxListView)
    ListViewInScrollView cycbxxListView;
    @BindView(R.id.cycbxxLayout)
    LinearLayout cycbxxLayout;
    private YwblDzdaFamilyMember jtcy;
    private MyListPageAdapter<YwblDzdaMemberIncomeInfo> adapterIncomeInfo;
    private MyListPageAdapter<YwblDzdaMemberSalaryInfo> adapterSalaryInfo;
    private MyListPageAdapter<YwblDzdaMemberInsureInfo> adapterInsureInfo;
    private MyListPageAdapter<YwblDzdaMemberDeformityInfo> adapterDeformInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_jtcyxx_detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭成员详细信息");
        jtcy = (YwblDzdaFamilyMember) getIntent().getSerializableExtra("YwblJtcy");
        sfzhView.doSetContent(jtcy.getPidcard());
        nameView.doSetContent(jtcy.getPname());
        xbView.doSetContent(jtcy.getPsex());
        csrqView.doSetContent(jtcy.getPbirthday());
        yhzgxView.doSetContent(jtcy.getPrelationship());
        sfbzdxView.doSetContent(jtcy.getPissafe());
        ryfljzlbView.doSetContent(jtcy.getPersonSalvationType());
        ysrView.doSetContent(jtcy.getPincome());
        mzView.doSetContent(jtcy.getPnation());
        hyzkView.doSetContent(jtcy.getPismarry());
        if (jtcy.getPerIncomeInfo() != null && jtcy.getPerIncomeInfo().size() > 0) {
            cysrxxLayout.setVisibility(View.VISIBLE);
            adapterIncomeInfo = new MyListPageAdapter<YwblDzdaMemberIncomeInfo>(this, R.layout.list_item_ywbl_cysrxx) {
                @Override
                protected void refreshView(int position, YwblDzdaMemberIncomeInfo item, View listItemView) {
                    MyLayoutView srjeView = (MyLayoutView) listItemView.findViewById(R.id.srjeView);
                    MyLayoutView srlxView = (MyLayoutView) listItemView.findViewById(R.id.srlxView);
                    srjeView.doSetContent(item.getMonthIncome());
                    srlxView.doSetContent(item.getIncomeTypeName());
                }
            };
            cysrxxListView.setAdapter(adapterIncomeInfo);
            adapterIncomeInfo.setListData(jtcy.getPerIncomeInfo());
            adapterIncomeInfo.notifyDataSetChanged();
        } else {
            cysrxxLayout.setVisibility(View.GONE);
        }

        if (jtcy.getPerSalaryInfo() != null && jtcy.getPerSalaryInfo().size() > 0) {
            cydyxxLayout.setVisibility(View.VISIBLE);
            adapterSalaryInfo = new MyListPageAdapter<YwblDzdaMemberSalaryInfo>(this, R.layout.list_item_ywbl_cydyxx) {
                @Override
                protected void refreshView(int position, YwblDzdaMemberSalaryInfo item, View listItemView) {
                    MyLayoutView dyjeView = (MyLayoutView) listItemView.findViewById(R.id.dyjeView);
                    MyLayoutView dylxView = (MyLayoutView) listItemView.findViewById(R.id.dylxView);
                    dyjeView.doSetContent(item.getMoney());
                    dylxView.doSetContent(item.getSalary());

                }
            };
            cydyxxListView.setAdapter(adapterSalaryInfo);
            adapterSalaryInfo.setListData(jtcy.getPerSalaryInfo());
            adapterSalaryInfo.notifyDataSetChanged();
        } else {
            cydyxxLayout.setVisibility(View.GONE);
        }


        if (jtcy.getPerInsureInfo() != null && jtcy.getPerInsureInfo().size() > 0) {
            cycbxxLayout.setVisibility(View.VISIBLE);
            adapterInsureInfo = new MyListPageAdapter<YwblDzdaMemberInsureInfo>(this, R.layout.list_item_ywbl_cycbxx) {
                @Override
                protected void refreshView(int position, YwblDzdaMemberInsureInfo item, View listItemView) {
                    MyLayoutView cbjeView = (MyLayoutView) listItemView.findViewById(R.id.cbjeView);
                    MyLayoutView cblxView = (MyLayoutView) listItemView.findViewById(R.id.cblxView);
                    MyLayoutView cbsjView = (MyLayoutView) listItemView.findViewById(R.id.cbsjView);
                    cbjeView.doSetContent(item.getInsureMoney());
                    cblxView.doSetContent(item.getInsureCategoryName());
                    cbsjView.doSetContent(item.getInsureTime());


                }
            };
            cycbxxListView.setAdapter(adapterInsureInfo);
            adapterInsureInfo.setListData(jtcy.getPerInsureInfo());
            adapterInsureInfo.notifyDataSetChanged();
        } else {
            cycbxxLayout.setVisibility(View.GONE);
        }


        if (jtcy.getPerDeformityInfo() != null && jtcy.getPerDeformityInfo().size() > 0) {
            cycjxxLayout.setVisibility(View.VISIBLE);
            adapterDeformInfo = new MyListPageAdapter<YwblDzdaMemberDeformityInfo>(this, R.layout.list_item_ywbl_cycjxx) {
                @Override
                protected void refreshView(int position, YwblDzdaMemberDeformityInfo item, View listItemView) {
                    MyLayoutView cjdjView = (MyLayoutView) listItemView.findViewById(R.id.cjdjView);
                    MyLayoutView cjlxView = (MyLayoutView) listItemView.findViewById(R.id.cjlxView);
                    cjdjView.doSetContent(item.getDeformityLevelName());
                    cjlxView.doSetContent(item.getDeformityTypeName());


                }
            };
            cycjxxListView.setAdapter(adapterDeformInfo);
            adapterDeformInfo.setListData(jtcy.getPerDeformityInfo());
            adapterDeformInfo.notifyDataSetChanged();
        } else {
            cycjxxLayout.setVisibility(View.GONE);
        }
    }


}
