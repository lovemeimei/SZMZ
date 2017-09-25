package com.szmz.ywbl;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.materiallistview.MaterialRefreshListener;
import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.utils.MyListPageAdapter;

import butterknife.BindView;

/**
 * Created by bz on 2017/9/21.
 */

public abstract class ActBaseList<T> extends ActBase {

    @BindView(R.id.refresh)
    public MaterialRefreshLayout refresh;

    protected EditText searchEd;
    protected MyListPageAdapter<T> adapter;
    protected ListView listView;
    protected LinearLayout noDataLayout;
    protected TextView textView;
    protected int CurrentPage = 1;
    protected int PageSize = 20;

    protected abstract void doRefreshView(int p, T item, View view);

    protected abstract int getListItemID();

    protected abstract void doMore(boolean isMore);

    protected void doListItemOnClick(T item) {

    }

    protected void doListItemOnClick(View view, T item) {

    }

    @Override
    public void initUI() {
        super.initUI();
        searchEd = (EditText) findViewById(R.id.search_ed);
        if (searchEd != null) {
            searchEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId,
                                              KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH
                            || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                        doMore(false);
                        return true;
                    }
                    return false;
                }
            });
        }
        noDataLayout = (LinearLayout) findViewById(R.id.noDataLayout);
        textView = (TextView) findViewById(R.id.textView);
        adapter = new MyListPageAdapter<T>(this, getListItemID()) {
            @Override
            protected void refreshView(int position, T item, View listItemView) {
                doRefreshView(position, item, listItemView);
            }
        };
        listView = (ListView) findViewById(R.id.listView);
        refresh = (MaterialRefreshLayout) findViewById(R.id.refresh);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                T item = (T) adapterView.getAdapter().getItem(i);
                doListItemOnClick(item);
                doListItemOnClick(view, item);
            }
        });
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

    }

    public static boolean isHasNextPage(int currentPage, int pageNumber, int totalItems) {
        int lastIndex = currentPage * pageNumber;
        if (lastIndex >= totalItems)
            return false;
        return true;
    }
}
