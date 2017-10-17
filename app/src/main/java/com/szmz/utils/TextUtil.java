package com.szmz.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qieyixuan
 * @created at 2016年03月28
 */
public class TextUtil {

    private TextUtil() {
    }

    /**
     * 是否是有效的邮箱地址
     *
     * @param address
     * @return
     */
    public static final boolean isEMailAddress(String address) {

        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w-]*[a-zA-Z0-9]\\.[a-zA-Z]{2," +
                "3}(\\.[a-zA-Z]{2})?$";
        Pattern pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    /**
     * 是否是有效的移动电话
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNumber(String mobiles) {
        //((13[0-9])|(15[^4,\D])|(18[0-9]))\d{8}
        Pattern p = Pattern
                .compile("^[1][3,4,5,7,8]\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 判断密码是否为数字和字母组合,6-20位
     * @return
     */
    public static boolean isVailPw(String pw){

        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$");

        Matcher matcher = pattern.matcher(pw);

        return matcher.matches();
    }
    /**
     * 是否是有效的固定电话
     *
     * @param number
     * @return
     */
    public static boolean isTelephoneNumber(String number) {
        Pattern p = Pattern.compile("^(\\(*\\d{3,4}\\)*-)*\\d{6,8}$");
        Matcher m = p.matcher(number);
        return m.matches();
    }

    /**
     * 需要转换的字符串
     *
     * @param text
     * @return
     */
    public static Spanned convertHtmlToText(String text, Context c) {
        return Html.fromHtml(text, null, null);

    }

    public static String textTOHtml(Spanned text) {
        return Html.toHtml(text);
    }

    private static Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            Log.d("Image Path", source);
            final URL url;
            try {
                url = new URL(source);


                drawable = Drawable.createFromStream(url.openStream(), "");

            } catch (Exception e) {
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth()*2,
                    drawable.getIntrinsicHeight()*2);
            return drawable;
        }
    };

    /**
     * 给字符串添加颜色,返回SpannableString
     *
     * @param str
     * @param color
     * @return
     */
    public static SpannableString getSpannableString(String str, int color) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(color), 0,
                str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, str.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 转换合适单位
     *
     * @param fileSize 单位字节
     * @return
     */
    public static String FormetFileSize(long fileSize) {

        DecimalFormat df = new DecimalFormat("#0.00");
        String fileSizeString = "";
        if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < 1024 * 1024) {
            fileSizeString = df.format((double) fileSize / (1024)) + "KB";
        } else if (fileSize < 1024 * 1024 * 1024) {
            fileSizeString = df.format((double) fileSize / (1024 * 1024))
                    + "MB";
        } else {
            fileSizeString = df
                    .format((double) fileSize / (1024 * 1024 * 1024)) + "GB";
        }
        return fileSizeString;

    }

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern
                .compile("[0-9]*");
        Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 是否是有效的身份证号码
     *
     * @param code
     * @return
     */
    public static boolean isPersonCode(String code) {
        Pattern pp = Pattern
                .compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
        Matcher m = pp.matcher(code);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
