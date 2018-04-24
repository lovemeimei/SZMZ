package com.szmz;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.BindView;

/**
 * 二维码证件真伪
 */
public class ActWebView extends ActBase {

    @BindView(R.id.webview)
    WebView webView;
    private MaterialDialog dialogLoading;

    String url;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("证件验证");
        setLeftVisible(true);

        url = getIntent().getStringExtra("url");

        dialogLoading = new MaterialDialog.Builder(context)
                .content("请稍后···")
                .progress(true, 100)
                .cancelable(true)
                .canceledOnTouchOutside(false)
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                })
                .build();

        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webView.loadUrl("http://222.222.49.34:9095/SocietySalvation/ReportServer?reportlet=securityCard.cpt&familyId=56e878847d964c098e75788d57c8cc4a");
//        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                    return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialogLoading.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialogLoading.dismiss();
            }


        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });


    }

    @Override
    protected void onDestroy() {
        webView.destroy();


        super.onDestroy();
    }
}
