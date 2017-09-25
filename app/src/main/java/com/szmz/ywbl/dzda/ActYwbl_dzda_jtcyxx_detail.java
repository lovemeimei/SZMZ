package com.szmz.ywbl.dzda;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.YwblJtcy;
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
    private YwblJtcy jtcy;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_jtcyxx_detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭成员详细信息");
        jtcy = (YwblJtcy) getIntent().getSerializableExtra("YwblJtcy");
        sfzhView.doSetContent(jtcy.getSfzh());
        nameView.doSetContent(jtcy.getName());
        xbView.doSetContent(jtcy.getXb());
        csrqView.doSetContent(jtcy.getCsrq());
        yhzgxView.doSetContent(jtcy.getYhzgx());
        sfbzdxView.doSetContent(jtcy.getSfbzdx());
        ryfljzlbView.doSetContent(jtcy.getRyfljzlb());
        ysrView.doSetContent(jtcy.getYsr());
        mzView.doSetContent(jtcy.getMz());
        hyzkView.doSetContent(jtcy.getHyzk());
    }
}
