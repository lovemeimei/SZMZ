package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.request.BaseRequest;
import com.szmz.widget.MyJobDialog;
import com.szmz.widget.MyJobDialogPL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 待办
 */
public class ActMyJobdb extends ActBase {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.ll_db_qx)
    LinearLayout llQX;

    MyJobDialog dialogDB;
    MyJobDialogPL dialogPL;

    @OnClick({
            R.id.tv_qx,R.id.tv_plbl,R.id.tv_title_right
    })
    public void doClick(View v){
        switch (v.getId()){
            case R.id.tv_qx:
                //全选
                break;
            case R.id.tv_plbl:
                //批量处理
                dialogPL.show();
                break;
            case R.id.tv_title_right:

                if (llQX.isShown()){
                    dialogDB.show();

                }else {
                    setRightShow("办理");
                    llQX.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_my_jobdb;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("待办业务");
        setLeftVisible(true);
        setRightVisible(true);
        setRightShow("操作");
        dialogDB = new MyJobDialog(this, new MyJobDialog.MDialogListener() {
            @Override
            public void doClick(int positon) {
                switch (positon){
                    case 0:
                        //同意
                        break;
                    case 1:
                        //退回
                        break;
                    case 2:
                        //移交
                        break;
                    case 3:
                        //不同意
                        break;
                    case 4:
                        //不通过
                        break;
                    case 5:
                        //轨迹
                        break;



                }
            }
        });

        dialogPL = new MyJobDialogPL(this, new MyJobDialogPL.MDialogListener() {
            @Override
            public void doClick(int positon) {
                switch (positon){
                    case 0:
                        //同意
                        break;
                    case 1:
                        //退回
                        break;
                    case 2:
                        //废除
                        break;
                    case 3:
                        //不通过
                        break;
                }
            }
        });
    }
}
