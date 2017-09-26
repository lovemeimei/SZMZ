package com.szmz.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.szmz.App;
import com.szmz.SystemEnv;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public final class ImageUtil {


    /**
     * 加载图片文件到ImageView控件中，并设置width,height
     *
     * @param img
     * @param filename
     * @param width
     * @param height
     * @return
     */
    public static int loadImage(ImageView img, String filename, int width,
                                int height) {
        try {
            Drawable drawable = getImage(filename, width, height,
                    img.getContext());
            if (drawable != null) {
                img.setImageDrawable(drawable);
            }

            return 0;

        } catch (Exception e) {
            return -1;
        }
    }

    public static int loadImage(ImageView img, String filename) {
        return loadImage(img, filename, 1024, 768);
    }

    private static int MAX_IMAGE_DIMENSION = 1280;
    private static final String TAG = "ImageUtil";

    public static Bitmap getImage(String fileName) {

        FileInputStream stream = null;
        try {
            stream = new FileInputStream(fileName);
            FileDescriptor fd = stream.getFD();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(fd, null, options);
            if (options.mCancel || options.outWidth == -1
                    || options.outHeight == -1) {
                return null;
            }

            // 1.换算合适的图片缩放值，以减少对JVM太多的内存请求。
            options.inSampleSize = computeSampleSize(options.outWidth,
                    options.outHeight);
            options.inJustDecodeBounds = false;

            options.inDither = false;
            options.inPreferredConfig = Config.ARGB_8888;

            // 2. inPurgeable 设定为 true，可以让java系统, 在内存不足时先行回收部分的内存
            options.inPurgeable = true;
            // 与inPurgeable 一起使用
            options.inInputShareable = true;

            try {
                // 4. inNativeAlloc 属性设置为true，可以不把使用的内存算到VM里
                BitmapFactory.Options.class.getField("inNativeAlloc")
                        .setBoolean(options, true);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            // 5. 使用decodeStream 解码，则利用NDK层中，利用nativeDecodeAsset（）
            // 进行解码，不用CreateBitmap
            return BitmapFactory.decodeStream(stream, null, options);

        } catch (IOException ex) {
            Log.e(TAG, "", ex);
        } catch (OutOfMemoryError oom) {
            Log.e(TAG, "Unable to decode file " + fileName
                    + ". OutOfMemoryError.", oom);
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException ex) {
                Log.e(TAG, "", ex);
            }
        }

        return null;
    }

    public static int computeSampleSize(int width, int height) {

        if (width > MAX_IMAGE_DIMENSION || height > MAX_IMAGE_DIMENSION) {
            float widthRatio = ((float) width) / MAX_IMAGE_DIMENSION;
            float heightRatio = ((float) height) / MAX_IMAGE_DIMENSION;
            float maxRatio = Math.max(widthRatio, heightRatio);
            return (int) maxRatio;
        } else {
            return 1;
        }

    }

    public static Bitmap getImage(String fileName, int maxWidth, int maxHeight) {

        try {

            Bitmap result = null;

            int targetSize = Math.max(maxWidth, maxHeight);
            int maxPixels = 1024 * 768 * 2;
            result = ThumbnailUtil.createImageThumbnail(fileName, targetSize,
                    maxPixels);

            if (result != null) {
                result = ThumbnailUtil.extractThumbnail(result, maxWidth,
                        maxHeight, ThumbnailUtil.OPTIONS_RECYCLE_INPUT);
            }

            return result;

        } catch (Exception e) {
            return null;
        }

    }

    public static Drawable getImage(String fileName, int maxWidth,
                                    int maxHeight, Context context) {
        try {

            Bitmap result = null;

            int targetSize = Math.max(maxWidth, maxHeight);
            int maxPixels = 1024 * 768 * 2;
            result = ThumbnailUtil.createImageThumbnail(fileName, targetSize,
                    maxPixels);

            if (result != null) {
                result = ThumbnailUtil.extractThumbnail(result, maxWidth,
                        maxHeight, ThumbnailUtil.OPTIONS_RECYCLE_INPUT);
            }

            return new BitmapDrawable(context.getResources(), result);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将base64字符串的图片内容转换为图片文件，使用jpg做为文件扩展名
     *
     * @param base64
     * @return 返回图片文件路径
     */
    public static String Base64ToPicture(String base64) {
        return Base64ToPicture(base64, "jpg");
    }

    /**
     * 将base64字符串的图片内容转换为图片文件，使用指定的扩展名
     *
     * @param base64
     * @param extensionName
     * @return
     */
    public static String Base64ToPicture(String base64, String extensionName) {
        return Base64ToPicture(base64, extensionName, FileUtil.getSDCachePath());
    }

    public static String Base64ToPicture(String base64, String extensionName,
                                         String path) {

        if (base64 == null || base64.equals("")
                || base64.equalsIgnoreCase("null")) {
            return "";
        }

        String ret = path + UUID.randomUUID().toString().replaceAll("-", "")
                + "." + extensionName;

        try {

            byte[] buf = Base64.decode(base64, 0);

            FileOutputStream fos = new FileOutputStream(new File(ret));
            fos.write(buf);
            fos.flush();
            fos.close();
        } catch (Exception ex) {
            return null;
        }
        return ret;
    }

    /**
     * 将文件转换为base64字符串
     *
     * @param picName 文件路径包括文件包
     * @return
     */
    public static String pictureToBase64(String picName) {
        File f = new File(picName);
        if (!f.exists())
            return "";
        String ret = "";

        try {
            FileInputStream fis = new FileInputStream(f);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int r;
            while ((r = fis.read(buf)) >= 0) {
                out.write(buf, 0, r);
            }
            ret = new String(Base64.encode(out.toByteArray(), 0));
            fis.close();
        } catch (FileNotFoundException e) {
            return "";
        } catch (IOException e) {
            return "";
        }

        return ret;
    }

    /**
     * 将Bitmap文件保存为文件路径下
     *
     * @param bm
     * @param imagePath
     * @return
     */
    public static boolean saveBitmapFile(Bitmap bm, String imagePath,
                                         String type) {

        if (imagePath == null || imagePath.equals(""))
            return false;

        File f = new File(imagePath);
        if (!f.exists())
            return false;

        if (bm == null)
            return false;

        BufferedOutputStream stream = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(f);
            stream = new BufferedOutputStream(fos);

            if (type != null && type.equals("png")) {
                bm.compress(CompressFormat.PNG, 80, stream);
            } else {
                bm.compress(CompressFormat.JPEG, 80, stream);
            }
            stream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ImageUtil", "压缩图片时出错:" + e.getMessage());
            return false;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bm != null && !bm.isRecycled()) {
                bm.recycle();
                bm = null;
            }
        }

        return true;
    }

    /* 拍照 */
    public static String getPicture(Activity act) {

        String path = FileUtil.getSDImagePath();

        if (path == null) {
            Toast.makeText(act, "请插入存储卡", Toast.LENGTH_SHORT).show();
            return null;
        }

        String strImgPath = path
                + UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
        Log.v("TakePic", strImgPath);

        SystemEnv.saveImagePath(strImgPath);

        File out = new File(strImgPath);
        Uri uri = Uri.fromFile(out);

        Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        try {
            act.startActivityForResult(imageCaptureIntent, CAPTUR_FROM_CAMERA);
        } catch (ActivityNotFoundException e) {

            return null;
        }

        return strImgPath;

    }

    public static final int CAPTUR_FROM_CAMERA = 99;
    protected static final int REQUEST_CODE_CROPIMAGE = 999;

    public static String getCropPicture(String pathName) {

        if (pathName == null || pathName.trim().equals(""))
            return "";

        ByteArrayOutputStream arrayStream = null;
        BufferedOutputStream stream = null;
        Bitmap bm = null;

        try {
            bm = getImage(pathName, 1024, 768);
            if (bm != null) {
                arrayStream = new ByteArrayOutputStream(2000);
                stream = new BufferedOutputStream(arrayStream);
                bm.compress(CompressFormat.JPEG, 80, stream);
                stream.flush();
                arrayStream.flush();
                String result = new String(Base64.encode(
                        arrayStream.toByteArray(), 0));
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.e("ImageUtil", "压缩图片时出错:" + e.getMessage());
            return null;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (arrayStream != null) {
                try {
                    arrayStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (bm != null && !bm.isRecycled()) {
                bm.recycle();
                bm = null;
            }
        }
    }

    /**
     * Drawable->Bitmap 2012-12-28王磊杰 （暂用）
     *
     * @param drawable Drawable
     * @return Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 Drawable 的长宽
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

        // 取 Drawable 的颜色格式
        Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
                : Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        // 设置边界
        drawable.setBounds(0, 0, width, height);
        // 把 Drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 图片去色,返回灰度图片 2013-3-14王磊杰(暂用)
     *
     * @param bmpOriginal
     *            传入的图片
     * @return 去色后的图片
     */
    // public static Bitmap toGrayscale(Bitmap bmpOriginal) {
    // int width, height;
    // height = bmpOriginal.getHeight();
    // width = bmpOriginal.getWidth();
    //
    // Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
    // Bitmap.Config.RGB_565);
    // Canvas c = new Canvas(bmpGrayscale);
    // Paint paint = new Paint();
    // ColorMatrix cm = new ColorMatrix();
    // cm.setSaturation(0);
    // ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
    // paint.setColorFilter(f);
    // c.drawBitmap(bmpOriginal, 0, 0, paint);
    // return bmpGrayscale;
    // }

    /**
     * Dip to Px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * Px To Dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 矩形2圆形 2013-3-26 王磊杰
     *
     * @param bitmap
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }

    /**
     * Uri2Bitmap 2013-3-29 王磊杰
     *
     * @param uri
     * @return
     */
    public static Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(App.getInstance()
                    .getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        // opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPreferredConfig = Config.ARGB_8888;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 郄益轩3月27号修改，添加图片后缀
     *
     * @param oPath
     * @param ext图片类型
     * @return
     */
    public static String getThumbImagePath(String oPath, String ext) {

        if (TextUtils.isEmpty(oPath))
            return "";

        int index = oPath.lastIndexOf("/");
        String fileName = oPath.substring(index + 1);
        String newPath = FileUtil.getSDImagePath() + fileName;
        File file = new File(newPath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 压缩图片
        Bitmap bitmap = getImage(oPath);
        bitmap = zoomBitmap(bitmap, 1280, 1280);
        boolean isSuccess = ImageUtil.saveBitmapFile(bitmap, newPath, ext);
        if (isSuccess) {
            Log.d(TAG, newPath);
            return newPath;
        }

        return oPath;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {

        if (bitmap == null) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (width <= w && height <= h) {
            return bitmap;
        }

        Matrix matrix = new Matrix();
        float scaleWidht = ((float) w / width);
        float scaleHeight = ((float) h / height);
        float min = Math.min(scaleWidht, scaleHeight);

        Log.d(TAG, min + "");

        matrix.postScale(min, min);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);

        return newbmp;
    }

    public static String getOImage(String url) {

        try {
            url = url.replace("_s", "");
        } catch (Exception e) {
            // TODO: handle exception
        }

        return url;
    }
}
