package com.szmz;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.szmz.entity.User;
import com.szmz.net.ApiService;
import com.szmz.ywbl.LocationService;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {

    private static GetMsgTask msgTask = null;

    private static App singleton = null;
    public static LocationService locationService;

    public static LocationService getLocationService() {
        if (locationService == null) {
            synchronized (LocationService.class) {
                if (locationService == null) {
                    locationService = new LocationService(App.getInstance());
                }
            }

        }
        return locationService;
    }

    /**
     * 作为系统资源被回收的标记
     */
    public static Boolean isInit = false;

    private static ApiService apiProxy = null;
    private static ApiService apiProxyCom = null;
    private static ApiService apiProxyComSQR = null;
    private static ApiService apiProxyJZ = null;
    private static ApiService apiProxyYZS = null;

    public static boolean isOnline = true;

    public static boolean isIsOnline() {
        return isOnline;
    }

    public static void setIsOnline(boolean isOnline) {
        App.isOnline = isOnline;
    }

    public static App getInstance() {
        if (singleton == null)
            singleton = new App();
        return singleton;
    }

    public static void setInstance(App app) {
        singleton = app;
    }

    private static User loginUser = null;

    public static GetMsgTask getGetMsgTask() {

        msgTask = new GetMsgTask();
        return msgTask;

    }

    public final void login(User loginUser) {
        App.loginUser = loginUser;
    }

    public final User getLoginUser() {
        return loginUser;
    }

    public final void logout() {
        loginUser = null;
    }

    /**
     * 是否已登录
     *
     * @return
     */
    public final boolean isLogin() {
        if (loginUser == null)
            return false;
        return true;
    }


    public static void setSingleton(App app) {
        singleton = app;
    }

    public static ApiService getApiProxy() {
        if (apiProxy == null) {
            apiProxy = initRetorfit(SystemConst.DEFAULT_SERVER);
        }
        return apiProxy;
    }

    /**
     * 平台
     *
     * @return
     */
    public static ApiService getApiProxyCom() {
        if (apiProxyCom == null) {
            apiProxyCom = initRetorfit(SystemConst.DEFAULT_SERVER_COM);
        }
        return apiProxyCom;
    }

    /**
     * 平台申请人
     *
     * @return
     */
    public static ApiService getApiProxyComSQR() {
        if (apiProxyComSQR == null) {
            apiProxyComSQR = initRetorfit(SystemConst.DEFAULT_SERVER_COM_SQR);
        }
        return apiProxyComSQR;
    }

    /**
     * 救助
     *
     * @return
     */
    public static ApiService getApiProxyJZ() {
        if (apiProxyJZ == null) {
            apiProxyJZ = initRetorfit(SystemConst.DEFAULT_SERVER_JZ);
        }
        return apiProxyJZ;
    }

    /**
     * 一站式
     *
     * @return
     */
    public static ApiService getApiProxyYZS() {

        if (apiProxyYZS == null) {
            apiProxyYZS = initRetorfit(SystemConst.DEFAULT_SERVER_YZS);
        }
        return apiProxyYZS;
    }


    private static DbManager.DaoConfig daoConfig;

    public static DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        x.Ext.init(this);
        x.Ext.setDebug(true);
        apiProxy = initRetorfit(SystemConst.DEFAULT_SERVER);
        daoConfig = new DbManager.DaoConfig()
                .setDbName("szmz")//创建数据库的名称
                .setDbVersion(1)//数据库版本号
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                    }
                });//数据库更新操作

    }

    private static ApiService initRetorfit(String url) {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                Log.d("MyTag", "OKHTTP:" + message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;
    }


    private static List<Activity> activityList = new LinkedList<Activity>();

    /**
     * 添加Activity到activityList
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 系统退出时调用
     */
    public static void exit() {

        try {

            for (Activity activity : activityList) {
                if (activity != null)
                    activity.finish();
            }

            getGetMsgTask().cancle();
        } catch (Exception e) {

        } finally {

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);

        }

    }

    /**
     * 系统退出时调用
     */
    public static void exit2() {

        try {

            for (Activity activity : activityList) {
                if (activity != null)
                    activity.finish();
            }
            getGetMsgTask().cancle();
        } catch (Exception e) {

        }

    }
}
