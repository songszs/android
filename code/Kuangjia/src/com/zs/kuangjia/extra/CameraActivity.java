package com.zs.kuangjia.extra;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.ImageColumns;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * @Title: CameraActivity.java
 */
public class CameraActivity extends Activity {

	public static final String TAG = CameraActivity.class.getSimpleName();
	public static final int REQUESTCODE = 1;
	private String filename = "";
	Intent intent = null;
	private String takephoto;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				// takephoto =StringUtil.noNull(
				// this.getIntent().getStringExtra("takephoto"));
				
				String filepath = Environment.getExternalStorageDirectory()
						+ "/mytestfile";				
				File parentfile = new File(filepath);
				if (!parentfile.exists()) {
					parentfile.mkdirs();
				}
				
				filename = filepath + "/" + createFileName() + ".jpg";
				Log.e("===",filename);
				intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				File out = new File(filename);
				Uri uri = Uri.fromFile(out);
				intent.putExtra(ImageColumns.ORIENTATION, 0);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				startActivityForResult(intent, REQUESTCODE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Intent it = new Intent();
			it.putExtra("picFile", "");
			setResult(Activity.RESULT_CANCELED, it);
			finish();
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUESTCODE) {

			if (resultCode == Activity.RESULT_OK) {
				Intent it = new Intent();
				it.putExtra("picFile", filename);
				setResult(Activity.RESULT_OK, it);
				finish();
			} else // 用户没�?择照�?
			{
				finish();
			}
		}
	}

	public String createFileName() {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			String today = sf.format(new java.util.Date());
			return today;
		} catch (Exception e) {
		}
		return "";
	}

}
