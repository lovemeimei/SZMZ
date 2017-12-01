package com.szmz.ayljzxt;

import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActBase;
import com.szmz.ActListBase;
import com.szmz.R;
import com.szmz.utils.UIUtil;

import butterknife.BindView;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/29 0029下午 3:30
 */

public class ActYZS_JDList extends ActListBase {

    @BindView(R.id.lv)
    ListView lv;

    @BindView(R.id.tv_search_title)
    TextView tvSearchView;

    @Override
    protected int getLayoutId() {
        return R.layout.comm_list;
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {
        currentPage=1;
        getList();
    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
        currentPage++;
        getList();
    }

    @Override
    protected void initUI() {
        super.initUI();
        UIUtil.hideInputMethod(this);

        setLeftVisible(true);
        setTitle("进度查询");

    }

    private void getList(){

    }
}
