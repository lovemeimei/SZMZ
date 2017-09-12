package com.szmz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

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

    @BindView(R.id.mTitle)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    protected TextView tvTitleRight;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    protected String TAG;
    protected MaterialDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setLeftVisible(false);

        dialog = new MaterialDialog.Builder(this).
                content("请稍后···").
                progress(true, 100)
                .cancelable(false)
                .canceledOnTouchOutside(false).build();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimFinish();
            }
        });

    }
    protected void setDialogCancledable(boolean isCan){
        if (dialog!=null){
            dialog.setCancelable(isCan);
        }
    }

    protected void setDialogContent(String content){
        if (dialog!=null){
            dialog.setContent(content);
        }
    }
    public void trans(Class cls){
        Intent intent =new Intent(context,cls);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       App.removeActivity(this);
    }

    protected void setLeftVisible(boolean isShow) {
        if (isShow){
            ivBack.setVisibility(View.VISIBLE);
        }else {
            ivBack.setVisibility(View.GONE);
        }
    }

    protected void setRightVisible(boolean isShow) {
        if (tvTitleRight != null) {

            tvTitleRight.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    protected void setRightShow(String str) {
        if (tvTitleRight != null) {
            tvTitleRight.setText(str);
        }
    }

    public String getStringByUI(View view) {

        if (view instanceof EditText) {

            return ((EditText) view).getText().toString().trim();
        } else if (view instanceof TextView) {
            return ((TextView) view).getText().toString().trim();
        }
        return "";
    }

    protected void setTitle(String title) {
        if (tvTitle != null && !TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }

    /**
     * 包含动画的finish
     */
    public void myAnimFinish() {
        this.finish();
//        this.overridePendingTransition(0, R.anim.roll_down);
    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("isGoHome", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
