package com.szmz;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.szmz.entity.IEntity;
import com.szmz.utils.GsonUtil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by bz on 2017/4/10.
 */

public final class SystemEnv {

    private static Context context = App.getInstance().getApplicationContext();
    private static SharedPreferences pref = PreferenceManager
            .getDefaultSharedPreferences(context);

    public static void saveImagePath(String path) {
        SharedPreferences.Editor et = pref.edit();
        et.putString(IMAGE_PATH, path);
        et.commit();
    }

    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public static <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        SharedPreferences.Editor et = pref.edit();
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
//        et.clear();
        et.remove(tag);
        et.putString(tag, strJson);
        et.commit();

    }

    public static void deleteDataList() {
        SharedPreferences.Editor et = pref.edit();
        et.remove("XZQH");
        et.commit();
    }

    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public static <T> List<T> getDataList(String tag) {
        List<T> datalist = new ArrayList<T>();
        String strJson = pref.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;

    }

    public static <T extends IEntity> boolean save(T entity, String key) {
        String prefFileName = entity.getClass().getName();
        SharedPreferences sp = context.getSharedPreferences(prefFileName, 0);
        SharedPreferences.Editor et = sp.edit();
        String json = GsonUtil.ser(entity);
        et.putString(key, json);
        return et.commit();
    }

    public static <T extends IEntity> List<T> findAll(Class<T> clazz) {
        String prefFileName = clazz.getName();
        SharedPreferences sp = context.getSharedPreferences(prefFileName, 0);
        Map<String, String> values = (Map<String, String>) sp.getAll();
        List<T> results = new ArrayList<T>();
        if (values == null || values.isEmpty())
            return results;
        Collection<String> colles = values.values();
        for (String json : colles) {
            results.add(GsonUtil.deser(json, clazz));
        }
        return results;
    }

    public static <T extends IEntity> T find(String key, Class<T> clazz) {
        String prefFileName = clazz.getName();
        SharedPreferences sp = context.getSharedPreferences(prefFileName, 0);
        String json = sp.getString(key, null);
        if (json == null)
            return null;
        return GsonUtil.deser(json, clazz);
    }

    public static <T extends IEntity> void delete(String key, Class<T> clazz) {
        String prefFileName = clazz.getName();
        SharedPreferences sp = context.getSharedPreferences(prefFileName, 0);
        if (sp.contains(key)) {
            sp.edit().remove(key).commit();
        }
    }

    public static <T extends IEntity> void deleteAll(Class<T> clazz) {
        String prefFileName = clazz.getName();
        SharedPreferences sp = context.getSharedPreferences(prefFileName, 0);
        sp.edit().clear().commit();
    }

    /**
     * 系统退出(不带提示框)
     */
    public static void systemOut() {
        NotificationManager notificationManager = (NotificationManager) App
                .getInstance().getSystemService(
                        Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        App.getInstance().logout();
        App.exit();
    }

    private static PackageInfo getAppPackageInfo() {
        try {

            Context context = App.getInstance().getApplicationContext();
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            return info;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 显示用版本号
     *
     * @return
     */
    public static String getVersionName() {
        PackageInfo info = getAppPackageInfo();
        if (info == null) {
            return "1.0";
        }
        return info.versionName;
    }

    public static int getVersionCode() {
        PackageInfo info = getAppPackageInfo();
        if (info == null) {
            return 1;
        }
        return info.versionCode;
    }

    private static final String USERID = "USER_ID";// userID
    private static final String DEVICEDEY = "DEVICEDEY";// 唯一设备号
    private static final String DATE = "DATE";// 日期
    private static final String MOBILEINFO = "MOBILE_INFO";// 手机信息
    private static final String ERRORTMSG = "ERROR_MSG";// 错误信息
    private static final String ERRORSTACK = "ERROR_STACK";// 错误堆栈
    private static final String IMAGE_PATH = "IMAGE_PATH";// 拍照图片路径

    private static final String ISCRASH = "ISCRASH";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_PW = "USER_PW";

    public static String getUserName() {

        return pref.getString(USER_NAME,"");
    }

    public static String getUserPw() {
        return pref.getString(USER_PW,"");
    }

    public static void saveUserName(String userName){

        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USER_NAME,userName);
        editor.commit();

    }


    public static void saveUserPw(String pw){

        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USER_PW,pw);
        editor.commit();

    }

    public static boolean iscrash() {
        return pref.getBoolean(ISCRASH, false);
    }

    /**
     * 崩溃设true，提交成功设false
     */
    public static void setcrash(boolean isCrash) {
        pref.edit().putBoolean(ISCRASH, isCrash).commit();
    }

    public static final String crashFilePath = "crashFilePath";

    public static String getCrashFilePath() {
        return pref.getString(crashFilePath, "");
    }


    public static void setCrashFilePath(String path) {
        pref.edit().putString(crashFilePath, path).commit();
    }


    public static void rememberCrashInfo(String userId, String deviceDey,
                                         String date, String mobileInfo, String errorMsg, String errorStack) {
        SharedPreferences.Editor et = pref.edit();
        et.putString(USERID, userId);
        et.putString(DEVICEDEY, deviceDey);
        et.putString(DATE, date);
        et.putString(MOBILEINFO, mobileInfo);
        et.putString(ERRORTMSG, errorMsg);
        et.putString(ERRORSTACK, errorStack);
        et.commit();
    }


    public static String getImagePath() {
        return pref.getString(IMAGE_PATH, "");
    }

    public static String getSavedUserId() {
        return pref.getString(USERID, "");
    }

    public static String getSavedDeviceDey() {
        return pref.getString(DEVICEDEY, "");
    }

    public static String getSavedDate() {
        return pref.getString(DATE, "");
    }

    public static String getSavedMobileInfo() {
        return pref.getString(MOBILEINFO, "");
    }

    public static String getSavedErrorTitle() {
        return pref.getString(ERRORTMSG, "");
    }

    public static String getSavedResult() {
        return pref.getString(ERRORSTACK, "");
    }

    public static String getDeviceInfo() {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = tm.getDeviceId();
        String tel = tm.getLine1Number();
        String imei = tm.getSimSerialNumber();
        String imsi = tm.getSubscriberId();
        return "deviceid#" + deviceid + "tel#" + tel + "imsi#" + imsi + "imei#"
                + imei;
    }

    public static String getDeviceTel() {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        String tel = tm.getLine1Number();

        if (tel != null && !tel.equals("")) {
            if (tel.length() > 11) {
                tel = tel.substring(tel.indexOf("1"), tel.length());
            }
            return tel;
        }
        return null;
    }

    public static String getDeviceInfo2() {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String tel = tm.getLine1Number();
        String imsi = tm.getSubscriberId();
        return "tel#" + tel + "_imsi#"
                + imsi;
    }

    public static final String DEVICE_KEY = "DEVICE_KEY";

    public static String getDeviceKey() {
        String deviceKey = pref.getString(DEVICE_KEY, null);
        if (deviceKey == null) {
            deviceKey = getCurrentMachineSerialNo();
            pref.edit().putString(DEVICE_KEY, deviceKey).commit();
        }

        return deviceKey;
    }

    /**
     * 获取当前设备组合的唯一标示
     *
     * @return
     */
    public static String getCurrentMachineSerialNo() {

        String cpuNo = getCPUSerialNo();
        String androidId = getAndroidID();

        boolean cpuIsEmpty = cpuNo == null || cpuNo.equals("");
        boolean androidIdIsEmpty = androidId == null || androidId.equals("");

        if (cpuIsEmpty) {
            cpuNo = "0000000";
        }

        if (androidIdIsEmpty) {
            androidId = "XXX-000000";
        }

        long mostSigBits = 0L;
        long leastSigBits = 0L;

        UUID uuid;

        mostSigBits = cpuNo.hashCode();
        leastSigBits = androidId.hashCode();

        uuid = new UUID(mostSigBits, leastSigBits);
        String MachineSerialNo = uuid.toString();

        return MachineSerialNo;
    }

    private static String getAndroidID() {
        String android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }

    private static String getCPUSerialNo() {
        String str = "", strCPU = "", cpuAddress = "";

        try {

            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");

            InputStreamReader ir = new InputStreamReader(pp.getInputStream());

            LineNumberReader input = new LineNumberReader(ir);

            for (int i = 1; i < 100; i++) {

                str = input.readLine();

                if (str != null) {

                    if (str.indexOf("Serial") > -1) {
                        strCPU = str.substring(str.indexOf(":") + 1,
                                str.length());
                        cpuAddress = strCPU.trim();
                        break;
                    }

                } else {
                    break;
                }

            }

            input.close();
            ir.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return cpuAddress;

    }

}
