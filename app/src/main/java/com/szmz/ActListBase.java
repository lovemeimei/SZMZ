package com.szmz;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.materiallistview.MaterialRefreshListener;

import butterknife.BindView;

/**
 * 有下拉list基类
 *
 * @author qieyixuan
 * @created at 2016年05月24
 */
public abstract class ActListBase extends ActBase {

    @BindView(R.id.refresh)
    public MaterialRefreshLayout refresh;
    protected int currentPage = 1;
    protected int pageSize = 20;
    @BindView(R.id.search_ed)
    protected EditText searchEd;

    public abstract void doRefresh(MaterialRefreshLayout materialRefreshLayout);

    public abstract void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout);


    @Override
    protected void initUI() {
        super.initUI();
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
        searchEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

//                    doSearch(searchEd.getText().toString());
                    doLoadData();
                    return true;
                }
                return false;
            }
        });
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
}
