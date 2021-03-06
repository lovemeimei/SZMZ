package com.szmz.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.szmz.App;
import com.szmz.R;


/**
 * @author qieyixuan
 * @created at 2016年03月28
 */
public class UIUtil {

    private static Context context = App.getInstance().getApplicationContext();

    private static SharedPreferences pref = PreferenceManager
            .getDefaultSharedPreferences(context);

    private UIUtil() {
    }

    public static void hideInputMethod(Activity context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (context.getWindow() != null) {
            Window win = context.getWindow();
            if (win.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(win
                        .getCurrentFocus().getWindowToken(), 0);
            } else {
                win.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        }
    }

    public static void disableView(View view) {
        setViewState(view, false);
    }

    public static void enableView(View view) {
        setViewState(view, true);
    }

    private static void setViewState(View view, boolean isEnabled) {
        view.setEnabled(isEnabled);
        view.setFocusable(isEnabled);
        view.setClickable(isEnabled);
    }

    /**
     * 通过读取xml文件处理桌面快捷方式,创建、删除、检测是否存在
     *
     * @param appName
     * @param action
     * @return
     */
    private static boolean doShortCut(String appName, String action) {

        SharedPreferences.Editor et = pref.edit();

        boolean result = false;

        if ("SAVE".equals(action)) {
            et.putString("SHORTCUT", appName);
            result = et.commit();
        } else if ("DEL".equals(action)
                && !"".equals(pref.getString("SHORTCUT", ""))) {
            et.clear();
            result = et.commit();
        } else if ("CHECK".equals(action)
                && !"".equals(pref.getString("SHORTCUT", ""))) {
            result = true;
        }

        return result;
    }


    /**
     * 弹出键盘
     */
    public static void showPan(Context contextt) {
        InputMethodManager manager = (InputMethodManager) contextt.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏键盘
     */
    public static void hidePan(Context mcontext, View v) {
        InputMethodManager imm = (InputMethodManager) mcontext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * 自定义Toast
     *
     * @param string
     */
    public static void doToast(String string) {
        // View view = LayoutInflater.from(context).inflate(R.layout.comm_toast,
        // null);
        // TextView title = (TextView) view.findViewById(R.id.txt_toast);
        // title.setText(string);
        Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        // toast.setGravity(Gravity.BOTTOM, 0, 30);
        toast.show();
    }

    /**
     * 获取手机屏幕宽度像素
     *
     * @return
     */
    public static int getWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取手机屏幕高度像素
     *
     * @return
     */
    public static int getHeight() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static void showUpdateDialog(Context context, String content, final ClickListener listener) {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_update, false);
        final MaterialDialog dialog = builder.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        View customView = dialog.getCustomView();
        final LinearLayout layoutShow = (LinearLayout) customView.findViewById(R.id.layoutShow);
        final LinearLayout layoutProgress = (LinearLayout) customView.findViewById(R.id.layoutProgress);
        final ProgressBar progressBar = (ProgressBar) customView.findViewById(R.id.progressBar);
        final TextView textSize = (TextView) customView.findViewById(R.id.tv_size);
        TextView contentTv = (TextView) customView.findViewById(R.id.content);
        contentTv.setText(content);
        TextView yesButton = (TextView) customView.findViewById(R.id.yesButton);
        TextView noButton = (TextView) customView.findViewById(R.id.noButton);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutProgress.setVisibility(View.VISIBLE);
                layoutShow.setVisibility(View.GONE);
                listener.updateClick(progressBar, textSize, dialog);

            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.closeClick();
            }
        });


    }


    public interface ClickListener {
        void closeClick();

        void updateClick(ProgressBar pb, TextView tvProgress, Dialog dialog);
    }

}
