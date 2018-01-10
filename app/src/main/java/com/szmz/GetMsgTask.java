package com.szmz;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

import com.szmz.ayljzxt.ActHomeDetail;
import com.szmz.entity.CommMsgSave;
import com.szmz.entity.User;
import com.szmz.entity.request.Comm_msg_req;
import com.szmz.entity.response.Comm_msg_Res;
import com.szmz.home.*;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/11 0011上午 9:57
 */

public class GetMsgTask{

     Timer timer = null;
     TimerTask task =null;
     long delay = 0;
     long intevalPeriod = 2*60 * 1000;
     Context context = App.getInstance().getApplicationContext();
    NotificationManager notificationManager;
    PendingIntent mainPendingIntent;


    public GetMsgTask() {
        if (timer==null)
            timer = new Timer();
        if (task==null){
            task = new TimerTask() {
                @Override
                public void run() {
                    getList();
                }
            };
        }
    }

    public void excute(){

        timer.scheduleAtFixedRate(task, delay, intevalPeriod);

    }

    public void cancle(){
        timer.cancel();
    }

     void getList(){

        User user = App.getInstance().getLoginUser();
        String idCard =user.getIdCode();
        if (idCard==null)
            idCard="";
        final Comm_msg_req req = new Comm_msg_req(user.getUserName(),user.getPhone(),idCard,"2");

        Call<Comm_msg_Res> call = App.getApiProxyJZ().getMsg(req);

        ApiUtil<Comm_msg_Res> apiUtil = new ApiUtil<>(App.getInstance().getApplicationContext(),call,new SimpleApiListener<Comm_msg_Res>(){
            @Override
            public void doSuccess(Comm_msg_Res result) {
                super.doSuccess(result);
                List<CommMsgSave> items = new ArrayList<>();
                items =result.Result;
                if (items!=null && items.size()>0){
                    DbManager dbManager = x.getDb(App.getDaoConfig());
                    for (CommMsgSave item:items){
                        try {
                            dbManager.save(item);
                            notificaitonMsg(item);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },false);

        apiUtil.excute();
    }

    private void initNotificaiton(){
        if (notificationManager==null){
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        }
    }

    private void notificaitonMsg(CommMsgSave item){

        initNotificaiton();
        Intent mainIntent = new Intent(context, com.szmz.home.ActMsgDetail.class);
        mainIntent.putExtra("item",item);
        mainPendingIntent = PendingIntent.getActivity(context, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(App.getInstance().getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(item.getContent())
                .setContentTitle(item.getTitle())
                .setContentIntent(mainPendingIntent);

        Notification notification = notificationCompat.build();
        notificationManager.notify(0, notification);
    }

}
