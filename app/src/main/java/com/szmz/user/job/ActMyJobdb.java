package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.JobDB;
import com.szmz.entity.request.BaseRequest;
import com.szmz.utils.BaseListAdapter;
import com.szmz.widget.DialogJobDB;
import com.szmz.widget.DialogJobDBEdit;
import com.szmz.widget.MyJobDialog;
import com.szmz.widget.MyJobDialogPL;

import java.util.ArrayList;
import java.util.List;

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

    private boolean isShowCheck = false;

    private BaseListAdapter<JobDB,ActMyJobdb.MViewHolder> adapter;
    private List<JobDB> items= new ArrayList<>();

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
//                    setRightShow("操作");
//                    llQX.setVisibility(View.GONE);
                    if (doCheck()){

                        dialogDB.show();
                    }

                }else {
                    setRightShow("办理");
                    llQX.setVisibility(View.VISIBLE);
                    isShowCheck = true;
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private boolean doCheck(){
        return true;
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

        adapter = new BaseListAdapter<JobDB, ActMyJobdb.MViewHolder>(this,R.layout.list_item_db) {
            @Override
            protected void refreshView(int postion, JobDB item, MViewHolder holer) {

                if (isShowCheck){
                    holer.checkBox.setVisibility(View.VISIBLE);
                    holer.ivIcon.setVisibility(View.GONE);
                }else {
                    holer.checkBox.setVisibility(View.GONE);
                    holer.ivIcon.setVisibility(View.VISIBLE);
                }
//                holer.tvName.setText(item.name);

            }

            @Override
            protected MViewHolder getHolder(View v) {

                MViewHolder holder =new MViewHolder();
                holder.checkBox = (CheckBox) v.findViewById(R.id.cb);
                holder.ivIcon = (ImageView) v.findViewById(R.id.iv_icon_db);
                holder.tvName = (TextView) v.findViewById(R.id.tv_name);

                return holder;
            }
        };
        lv.setAdapter(adapter);

        for (int i=0;i<10;i++){
            JobDB item = new JobDB();
            items.add(item);
        }

        adapter.setItems(items);
        adapter.notifyDataSetChanged();

        dialogDB = new MyJobDialog(this, new MyJobDialog.MDialogListener() {
            @Override
            public void doClick(int positon) {
                switch (positon){
                    case 0:
                        //同意
                        DialogJobDB dialogJobDB = new DialogJobDB(ActMyJobdb.this, new DialogJobDB.MyLisener() {
                            @Override
                            public void doOK() {

                            }

                            @Override
                            public void doCancel() {

                            }
                        },"确定要提交吗？");
                        dialogJobDB.show();
                        break;
                    case 1:
                        //退回
                        trans(ActDBTuihui.class);
                        break;
                    case 2:
                        //移交
                        trans(ActDBYijiao.class);
                        break;
                    case 3:
                        //不同意
                        DialogJobDB dialogBTY = new DialogJobDB(ActMyJobdb.this, new DialogJobDB.MyLisener() {
                            @Override
                            public void doOK() {

                            }

                            @Override
                            public void doCancel() {

                            }
                        },"确定要删除此流程吗？");
                        dialogBTY.show();
                        break;
                    case 4:
                        trans(ActDBButongguo.class);
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
                        DialogJobDBEdit dialogJobDB = new DialogJobDBEdit(ActMyJobdb.this, new DialogJobDBEdit.MyLisener() {
                            @Override
                            public void doOK(String spyj) {

                            }

                            @Override
                            public void doCancel() {

                            }
                        },"确定所选业务全部通过吗？");
                        dialogJobDB.show();
                        break;
                    case 1:
                        //退回
                        DialogJobDBEdit dialogJobDBTH = new DialogJobDBEdit(ActMyJobdb.this, new DialogJobDBEdit.MyLisener() {
                            @Override
                            public void doOK(String spyj) {

                            }

                            @Override
                            public void doCancel() {

                            }
                        },"确定所选业务全部退回吗？");
                        dialogJobDBTH.show();
                        break;
                    case 2:
                        //废除
                        DialogJobDBEdit dialogJobDBFC = new DialogJobDBEdit(ActMyJobdb.this, new DialogJobDBEdit.MyLisener() {
                            @Override
                            public void doOK(String spyj) {

                            }

                            @Override
                            public void doCancel() {

                            }
                        },"确定所选业务全部废除吗？");
                        dialogJobDBFC.show();
                        break;
                    case 3:
                        //不通过
                        DialogJobDBEdit dialogJobDBbtg = new DialogJobDBEdit(ActMyJobdb.this, new DialogJobDBEdit.MyLisener() {
                            @Override
                            public void doOK(String spyj) {

                            }

                            @Override
                            public void doCancel() {

                            }
                        },"确定所选业务全部否决吗？");
                        dialogJobDBbtg.show();
                        break;
                }
            }
        });
    }

    class MViewHolder{
        CheckBox checkBox;
        TextView tvName;
        ImageView ivIcon;
    }
}
