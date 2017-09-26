package com.szmz.ahdxt;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 核对系统主界面
 */
public class ActHdxtMain extends ActBase {


    @BindView(R.id.grcxLayout)
    LinearLayout grcxLayout;
    @BindView(R.id.jgLayout)
    LinearLayout jgLayout;
    @BindView(R.id.tjfxLayout)
    LinearLayout tjfxLayout;
    @BindView(R.id.xxtzLayout)
    LinearLayout xxtzLayout;
    @BindView(R.id.zlglLayout)
    LinearLayout zlglLayout;
    @BindView(R.id.home)
    ImageView home;
    @BindView(R.id.person)
    ImageView person;

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("居民经济核对系统");
    }

    @OnClick({
            R.id.grcxLayout, R.id.jgLayout, R.id.tjfxLayout, R.id.xxtzLayout, R.id.zlglLayout, R.id.person
    })
    public void doClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.grcxLayout:
                intent = new Intent(this, ActHdxt_GRCX.class);
                intent.putExtra("Type", 1);
                startActivity(intent);
                break;
            case R.id.jgLayout:
                intent = new Intent(this, ActHdxt_JG.class);
                intent.putExtra("Type", 2);
                startActivity(intent);
                break;
            case R.id.tjfxLayout:
                intent = new Intent(this, ActHdxt_TJFX.class);
                intent.putExtra("Type", 3);
                startActivity(intent);
                break;
            case R.id.xxtzLayout:
                intent = new Intent(this, ActHdxt_XXTZ.class);
                intent.putExtra("Type", 4);
                startActivity(intent);
                break;
            case R.id.zlglLayout:
                intent = new Intent(this, ActHdxt_ZLGL.class);
                intent.putExtra("Type", 0);
                startActivity(intent);
                break;
            case R.id.person:
                intent = new Intent(this, ActHdxt_ZLGL.class);
                intent.putExtra("Type", 5);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt_main;
    }


}
