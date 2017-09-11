package com.barcode;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.barcode.camera.PlanarYUVLuminanceSource;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.szmz.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TestCaptureActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private SurfaceView sfvCamera;
    private SFHCamera sfhCamera;
    private ImageView imgView;
    private View centerView;
    private TextView txtScanResult;
    private Timer mTimer;
    private MyTimerTask mTimerTask;
    // 按照标准HVGA
    final static int width = 480;
    final static int height = 320;
    int dstLeft, dstTop, dstWidth, dstHeight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_test);
        imgView = (ImageView) this.findViewById(R.id.ImageView01);
        centerView = (View) this.findViewById(R.id.centerView);
        sfvCamera = (SurfaceView) this.findViewById(R.id.sfvCamera);
        sfhCamera = new SFHCamera(sfvCamera.getHolder(), width, height,
                previewCallback);
        txtScanResult = (TextView) this.findViewById(R.id.txtScanResult);
        // 初始化定时器
        mTimer = new Timer();
        mTimerTask = new MyTimerTask();
        mTimer.schedule(mTimerTask, 0, 80);
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            if (dstLeft == 0) {//只赋值一次
                dstLeft = centerView.getLeft() * width
                        / getWindowManager().getDefaultDisplay().getWidth();
                dstTop = centerView.getTop() * height
                        / getWindowManager().getDefaultDisplay().getHeight();
                dstWidth = (centerView.getRight() - centerView.getLeft()) * width
                        / getWindowManager().getDefaultDisplay().getWidth();
                dstHeight = (centerView.getBottom() - centerView.getTop()) * height
                        / getWindowManager().getDefaultDisplay().getHeight();
            }
            sfhCamera.AutoFocusAndPreviewCallback();
        }
    }

    /**
     * 自动对焦后输出图片
     */
    private Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera arg1) {
            //取得指定范围的帧的数据
            PlanarYUVLuminanceSource source = new PlanarYUVLuminanceSource(
                    data, width, height, dstLeft, dstTop, dstWidth, dstHeight);
            //取得灰度图
            Bitmap mBitmap = source.renderCroppedGreyscaleBitmap();
            //显示灰度图
            imgView.setImageBitmap(mBitmap);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            MultiFormatReader reader = new MultiFormatReader();
            try {
                Result result = reader.decodeWithState(bitmap);
                String strResult = "BarcodeFormat:"
                        + result.getBarcodeFormat().toString() + "  text:"
                        + result.getText();
                txtScanResult.setText(strResult);
            } catch (Exception e) {
                txtScanResult.setText("Scanning");
            } finally {
                reader.reset();
            }


        }
    };
}


class SFHCamera implements SurfaceHolder.Callback {
    private SurfaceHolder holder = null;
    private Camera mCamera;
    private int width, height;
    private Camera.PreviewCallback previewCallback;

    public SFHCamera(SurfaceHolder holder, int w, int h, Camera.PreviewCallback previewCallback) {
        this.holder = holder;
        this.holder.addCallback(this);
        this.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        width = w;
        height = h;
        this.previewCallback = previewCallback;
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(width, height);//设置尺寸
        parameters.setPictureFormat(PixelFormat.JPEG);
        mCamera.setParameters(parameters);
        mCamera.startPreview();//开始预览
        Log.e("Camera", "surfaceChanged");
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        mCamera = Camera.open();//启动服务
        try {
            mCamera.setPreviewDisplay(holder);//设置预览
            Log.e("Camera", "surfaceCreated");
        } catch (IOException e) {
            mCamera.release();//释放
            mCamera = null;
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();//停止预览
        mCamera = null;
        Log.e("Camera", "surfaceDestroyed");
    }

    /**
     * 自动对焦并回调Camera.PreviewCallback
     */
    public void AutoFocusAndPreviewCallback() {
        if (mCamera != null)
            mCamera.autoFocus(mAutoFocusCallBack);
    }

    /**
     * 自动对焦
     */
    private Camera.AutoFocusCallback mAutoFocusCallBack = new Camera.AutoFocusCallback() {

        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {  //对焦成功，回调Camera.PreviewCallback
                mCamera.setOneShotPreviewCallback(previewCallback);
            }
        }
    };


}
