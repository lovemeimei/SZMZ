package com.szmz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 8:52
 */

public abstract class ActBase extends AppCompatActivity {

    protected abstract int getLayoutId();
    Context context;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    protected String TAG;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        App.addActivity(this);
        TAG = this.getClass().getName();
        initUI();
    }

    protected void initUI() {
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);
        context = this;
    }

    public void trans(Class cls){
        Intent intent =new Intent(context,cls);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       App.removeActivity(this);
    }
}
