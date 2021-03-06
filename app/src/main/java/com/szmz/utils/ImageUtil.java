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
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
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
import java.io.ByteArrayInputStream;
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
     * 设置水印图片在左上角
     *
     * @param src
     * @param watermark
     * @param paddingLeft
     * @param paddingTop
     * @return
     */
    public static Bitmap createWaterMaskLeftTop(
            Context context, Bitmap src, Bitmap watermark,
            int paddingLeft, int paddingTop) {
        return createWaterMaskBitmap(src, watermark,
                dp2px(context, paddingLeft), dp2px(context, paddingTop));
    }

    private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark,
                                                int paddingLeft, int paddingTop) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        //创建一个bitmap
        Bitmap newb = Bitmap.createBitmap(width, height, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        //将该图片作为画布
        Canvas canvas = new Canvas(newb);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, null);
        //在画布上绘制水印图片
        canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
        // 保存
        canvas.save(Canvas.ALL_SAVE_FLAG);
        // 存储
        canvas.restore();
        return newb;
    }

    /**
     * 设置水印图片在右下角
     *
     * @param src
     * @param watermark
     * @param paddingRight
     * @param paddingBottom
     * @return
     */
    public static Bitmap createWaterMaskRightBottom(
            Context context, Bitmap src, Bitmap watermark,
            int paddingRight, int paddingBottom) {
        return createWaterMaskBitmap(src, watermark,
                src.getWidth() - watermark.getWidth() - dp2px(context, paddingRight),
                src.getHeight() - watermark.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 设置水印图片到右上角
     *
     * @param src
     * @param watermark
     * @param paddingRight
     * @param paddingTop
     * @return
     */
    public static Bitmap createWaterMaskRightTop(
            Context context, Bitmap src, Bitmap watermark,
            int paddingRight, int paddingTop) {
        return createWaterMaskBitmap(src, watermark,
                src.getWidth() - watermark.getWidth() - dp2px(context, paddingRight),
                dp2px(context, paddingTop));
    }

    /**
     * 设置水印图片到左下角
     *
     * @param src
     * @param watermark
     * @param paddingLeft
     * @param paddingBottom
     * @return
     */
    public static Bitmap createWaterMaskLeftBottom(
            Context context, Bitmap src, Bitmap watermark,
            int paddingLeft, int paddingBottom) {
        return createWaterMaskBitmap(src, watermark, dp2px(context, paddingLeft),
                src.getHeight() - watermark.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 设置水印图片到中间
     *
     * @param src
     * @param watermark
     * @return
     */
    public static Bitmap createWaterMaskCenter(Bitmap src, Bitmap watermark) {
        return createWaterMaskBitmap(src, watermark,
                (src.getWidth() - watermark.getWidth()) / 2,
                (src.getHeight() - watermark.getHeight()) / 2);
    }

    /**
     * 给图片添加文字到左上角
     *
     * @param context
     * @param bitmap
     * @param text
     * @return
     */
    public static Bitmap drawTextToLeftTop(Context context, Bitmap bitmap, String text,
                                           int size, int color, int paddingLeft, int paddingTop) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                dp2px(context, paddingLeft),
                dp2px(context, paddingTop) + bounds.height());
    }

    /**
     * 绘制文字到右下角
     *
     * @param context
     * @param bitmap
     * @param text
     * @param size
     * @param color
     * @return
     */
    public static Bitmap drawTextToRightBottom(Context context, Bitmap bitmap, String text,
                                               int size, int color, int paddingRight, int paddingBottom) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                bitmap.getWidth() - bounds.width() - dp2px(context, paddingRight),
                bitmap.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 绘制文字到右上方
     *
     * @param context
     * @param bitmap
     * @param text
     * @param size
     * @param color
     * @param paddingRight
     * @param paddingTop
     * @return
     */
    public static Bitmap drawTextToRightTop(Context context, Bitmap bitmap, String text,
                                            int size, int color, int paddingRight, int paddingTop) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                bitmap.getWidth() - bounds.width() - dp2px(context, paddingRight),
                dp2px(context, paddingTop) + bounds.height());
    }

    /**
     * 绘制文字到左下方
     *
     * @param context
     * @param bitmap
     * @param text
     * @param size
     * @param color
     * @param paddingLeft
     * @param paddingBottom
     * @return
     */
    public static Bitmap drawTextToLeftBottom(Context context, Bitmap bitmap, String text,
                                              int size, int color, int paddingLeft, int paddingBottom) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                dp2px(context, paddingLeft),
                bitmap.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 绘制文字到中间
     *
     * @param context
     * @param bitmap
     * @param text
     * @param size
     * @param color
     * @return
     */
    public static Bitmap drawTextToCenter(Context context, Bitmap bitmap, String text,
                                          int size, int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                (bitmap.getWidth() - bounds.width()) / 2,
                (bitmap.getHeight() + bounds.height()) / 2);
    }

    //图片上绘制文字
    private static Bitmap drawTextToBitmap(Context context, Bitmap bitmap, String text,
                                           Paint paint, Rect bounds, int paddingLeft, int paddingTop) {
        android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawText(text, paddingLeft, paddingTop, paint);
        return bitmap;
    }

    public static Bitmap drawTextToLeftBottom(Context context, Bitmap bitmap, String[] text,
                                              int size, int color, int paddingLeft, int paddingBottom) {
        if (text.length < 4) {
            return null;
        }
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text[3], 0, text[3].length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                dp2px(context, paddingLeft),
                bitmap.getHeight() - dp2px(context, paddingBottom));
    }

    private static Bitmap drawTextToBitmap(Context context, Bitmap bitmap, String[] text,
                                           Paint paint, Rect bounds, int paddingLeft, int paddingTop) {
        if (text.length < 4) {
            return null;
        }
        android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(text[3], paddingLeft, paddingTop, paint);
        canvas.drawText(text[2], paddingLeft, paddingTop - dp2px(context, 14), paint);
        canvas.drawText(text[1], paddingLeft, paddingTop - dp2px(context, 14) - dp2px(context, 14), paint);
        canvas.drawText(text[0], paddingLeft, paddingTop - dp2px(context, 14) - dp2px(context, 14) - dp2px(context, 14), paint);
        return bitmap;
    }

    /**
     * 缩放图片
     *
     * @param src
     * @param w
     * @param h
     * @return
     */
    public static Bitmap scaleWithWH(Bitmap src, double w, double h) {
        if (w == 0 || h == 0 || src == null) {
            return src;
        } else {
            // 记录src的宽高
            int width = src.getWidth();
            int height = src.getHeight();
            // 创建一个matrix容器
            Matrix matrix = new Matrix();
            // 计算缩放比例
            float scaleWidth = (float) (w / width);
            float scaleHeight = (float) (h / height);
            // 开始缩放
            matrix.postScale(scaleWidth, scaleHeight);
            // 创建缩放后的图片
            return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
        }
    }

    /**
     * dip转pix
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

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

    /**
     * 像素压缩
     * @param fileName
     * @return
     */
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

    //质量压缩
    public static Bitmap mCompressImage(Bitmap image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 80;
        while ((baos.toByteArray().length / 1024) > 500) {	//循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            Log.i("ImageUtil2s", "baos.toByteArray().length / 1024=" + (baos.toByteArray().length / 1024));
            Log.i("ImageUtil2s", "options=" + options);
            options -= 10;
        }
        Log.i("ImageUtil2s", "final1024=" + (baos.toByteArray().length / 1024));
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        return BitmapFactory.decodeStream(isBm, null, null);
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

     /* 旋转图片
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();;
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 读取图片属性：旋转的角度
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree  = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 图片水印
     */
    public static Bitmap waterMark(Bitmap src, Bitmap watermark) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        int startx = (int)(width - watermark.getWidth())/2;
        int starY = (int)(height-watermark.getHeight())/2;
        //创建一个bitmap
        Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        //将该图片作为画布
        Canvas canvas = new Canvas(newb);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, null);
        //在画布上绘制水印图片
        canvas.drawBitmap(watermark, startx, starY, null);
        // 保存
        canvas.save(Canvas.ALL_SAVE_FLAG);
        // 存储
        canvas.restore();
        return newb;
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
     * 矩形2圆形
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
     * @param oPath
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

    public static String getWaterImagePath(Activity context, String oPath, String ext, String[] waterWord) {

        if (TextUtils.isEmpty(oPath))
            return "";

        int index = oPath.lastIndexOf("/");
        String fileName = oPath.substring(index + 1);
        String newPath = FileUtil.getSDCacheCropPath() + fileName;
        File file = new File(newPath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = getImage(oPath);
        bitmap = drawTextToLeftBottom(context, bitmap, waterWord, 12, Color.RED, 10, 10);
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
