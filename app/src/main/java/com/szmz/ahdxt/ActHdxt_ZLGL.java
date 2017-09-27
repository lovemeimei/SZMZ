package com.szmz.ahdxt;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.ahdxt.zlgl.ActZlgl_List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 资料管理
 */
public class ActHdxt_ZLGL extends ActBase {

    @BindView(R.id.hdzcLayout)
    LinearLayout hdzcLayout;
    @BindView(R.id.bmzdLayout)
    LinearLayout bmzdLayout;
    @BindView(R.id.hdywzlLayout)
    LinearLayout hdywzlLayout;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("资料管理");
    }

    @OnClick({
            R.id.hdzcLayout, R.id.bmzdLayout, R.id.hdywzlLayout
    })
    public void doClick(View v) {
        Intent intent = new Intent(this, ActZlgl_List.class);

        switch (v.getId()) {
            case R.id.hdzcLayout:
                intent.putExtra("Type", 1);
                startActivity(intent);
                break;
            case R.id.bmzdLayout:
                intent.putExtra("Type", 2);
                startActivity(intent);
                break;
            case R.id.hdywzlLayout:
                intent.putExtra("Type", 3);
                startActivity(intent);
                break;


        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__zlgl;
    }


}
