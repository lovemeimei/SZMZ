package com.szmz.ywbl.dzda;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.YwblDzdaFamilyMember;
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
    private YwblDzdaFamilyMember jtcy;

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
        sfzhView.doSetContent(jtcy.getIdcard());
        nameView.doSetContent(jtcy.getName());
        xbView.doSetContent(jtcy.getSex());
        csrqView.doSetContent(jtcy.getBirthday());
        yhzgxView.doSetContent(jtcy.getRelationship());
        sfbzdxView.doSetContent("1".equals(jtcy.getIssafe()) ? "是" : "否");
        ryfljzlbView.doSetContent(jtcy.getPersonSalvationType());
        ysrView.doSetContent(jtcy.getIncome());
        mzView.doSetContent(jtcy.getNation());
        hyzkView.doSetContent("1".equals(jtcy.getIsmarry()) ? "已婚" : "未婚");
    }
}
