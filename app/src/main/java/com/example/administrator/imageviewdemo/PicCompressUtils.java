package com.example.administrator.imageviewdemo;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * http://blog.csdn.net/harryweasley/article/details/51955467 5种压缩方式
 * http://blog.csdn.net/tianchenglin/article/details/53538044 3种压缩方式 jni
 * 
 * @author zhiyuan
 *
 */
public class PicCompressUtils {
	private static final String TAG = "PicCompressUtils";

	public static Bitmap compressPicInQuality(Bitmap bitmap) {
		// 压缩图片format 图片格式 .jpg .png
		// 质量比例
		int quality = 90;
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, quality, arrayOutputStream);
		// 100K
		while (arrayOutputStream.size() / 1024 > 100) {
			quality = quality - 10;
			// 重新进行压缩
			arrayOutputStream.reset();
			bitmap.compress(CompressFormat.JPEG, quality, arrayOutputStream);
		}

		byte[] bts = arrayOutputStream.toByteArray();
		Bitmap bmp = BitmapFactory.decodeByteArray(bts, 0, bts.length);
		Log.i(TAG, "质量压缩结束后" + bmp.getByteCount() / 1024 / 1024 + "------");
		try {
			arrayOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bmp;
	}

	/**
	 * 尺寸压缩
	 * 
	 * @return
	 */
	@SuppressLint("NewApi")
	public static Bitmap compressPicInSize(WindowManager windowManager, String path) {
		Options opts = new Options();
		// 是否只加载一些参数信息
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, opts);
		// 设置为rgb565
		opts.inPreferredConfig = Config.RGB_565;
		// 获取图片宽高
		int outHeight = opts.outHeight;
		int outWidth = opts.outWidth;
		// 获取屏幕分辨率 屏幕宽高
		
		Display defaultDisplay = windowManager.getDefaultDisplay();

		DisplayMetrics outMetrics = new DisplayMetrics();
		defaultDisplay.getMetrics(outMetrics);

		int windowHight = outMetrics.heightPixels;
		int windowWidth = outMetrics.widthPixels;

		// 设置比例
		int scale = 1;
		int scaleX = outWidth / windowWidth;

		int scaleY = outHeight / windowHight;

		// 200 100 2000 500 200 50
		//以缩放比例大的方式进行缩放
		if (scaleX > scaleY && scaleY > 1) {
			scale = scaleX;
		} else if (scaleY > scaleX && scaleX > 1) {
			scale = scaleY;
		}
		// 重新加载图片
		opts.inSampleSize = scale;
		opts.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeFile(path, opts);

		Log.i(TAG, bitmap.getByteCount() / 1024 / 1024 + "------");
		return compressPicInQuality(bitmap);
	}

}
