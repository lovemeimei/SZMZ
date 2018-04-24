package com.szmz;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.szmz.ayljzxt.ActHomeDetail;
import com.szmz.entity.CommMsgSave;
import com.szmz.entity.User;
import com.szmz.entity.request.Comm_msg_req;
import com.szmz.entity.response.Comm_msg_Res;
import com.szmz.home.*;
import com.szmz.home.ActMsgDetail;
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

import static android.support.v4.app.NotificationCompat.PRIORITY_MAX;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/11 0011上午 9:57
 */

public class GetMsgTask{

     Timer timer = null;
     TimerTask task =null;
     long delay = 10*1000;
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
        notificationManager.cancelAll();
        timer.cancel();
    }

     void getList(){

        User user = App.getInstance().getLoginUser();
        String idCard =user.getIdCode();
        if (idCard==null)
            idCard="";
        String type = "1";
        if (user.getType()==1){
            type = "2";
        }else {
            type="1";
        }
        final Comm_msg_req req = new Comm_msg_req(user.getUserName(),user.getPhone(),idCard,type);

        Call<Comm_msg_Res> call = App.getApiProxyJZ().getMsg(req);

        ApiUtil<Comm_msg_Res> apiUtil = new ApiUtil<>(App.getInstance().getApplicationContext(),call,new SimpleApiListener<Comm_msg_Res>(){
            @Override
            public void doSuccess(Comm_msg_Res result) {
                super.doSuccess(result);
                List<CommMsgSave> items = new ArrayList<>();
                items =result.Result;
                if (items!=null && items.size()>0){
                    DbManager dbManager = x.getDb(App.getDaoConfig());
                    int id=100;
                    for (CommMsgSave item:items){
                        try {
                            dbManager.save(item);

                            if (SystemEnv.getSetNewMsg()){
                                notificaitonMsg(item,id);

                            }
                            id++;
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

    private void notificaitonMsg(CommMsgSave item,int id){

        if (!SystemEnv.getSetNewMsg()){
            return;
        }
        initNotificaiton();
        Intent mainIntent = new Intent(context, com.szmz.home.ActMsgDetail.class);
        mainIntent.putExtra("item",item);
        mainPendingIntent = PendingIntent.getActivity(context, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(App.getInstance().getApplicationContext())
                .setSmallIcon(R.drawable.logo_s)
                .setContentText(item.getContent())
                .setContentTitle(item.getTitle())
                .setAutoCancel(true)
                .setContentIntent(mainPendingIntent)
                .setGroup("zhmz_qyx")
                .setGroupSummary(true);

        if (SystemEnv.getSetShake()&&SystemEnv.getSetSound()&&!SystemEnv.getSetMdr()){

            notificationCompat.setDefaults(Notification.DEFAULT_VIBRATE|Notification.DEFAULT_SOUND);

        }else if (SystemEnv.getSetShake()&&!SystemEnv.getSetMdr()){

            notificationCompat.setDefaults(Notification.DEFAULT_VIBRATE);

        }else if (SystemEnv.getSetSound()&&!SystemEnv.getSetMdr()){
            //
            notificationCompat.setDefaults(Notification.DEFAULT_SOUND);
        }

        notificationManager.notify(id, notificationCompat.build());
    }

}
