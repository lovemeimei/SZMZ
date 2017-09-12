package com.szmz.search;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActBase;
import com.szmz.ActListBase;
import com.szmz.R;
import com.szmz.utils.UIUtil;
import com.szmz.widget.ClearEditText;
import com.szmz.widget.SearchEditText;

import butterknife.BindView;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/11 0011下午 3:08
 */

public class ActHistoryList extends ActListBase{

    @BindView(R.id.lv)
    ListView lv;

    @BindView(R.id.search_ed)
    SearchEditText etSearch;

    @Override
    protected void initUI() {
        super.initUI();
        UIUtil.hideInputMethod(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_history_list;
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

    }
}
