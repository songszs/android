package com.zs.kuangjia.extra;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * 获取本地图片 ， 压缩并返回
 * 
 * @author Administrator
 * 
 */
public class LocalImageActivity extends Activity {
	final int IMAGE_SELECT = 15; // 浏览本地相册选择

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 通知系统刷新图库
		// Uri localUri =
		// Uri.fromFile(Environment.getExternalStorageDirectory());
		// Intent localIntent = new
		// Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);
		// sendBroadcast(localIntent);

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, IMAGE_SELECT);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Toast.makeText(this, "sd卡不存在,请插入sd卡", Toast.LENGTH_LONG).show();
			Intent it = new Intent();
			it.putExtra("picFile", "");
			setResult(Activity.RESULT_CANCELED, it);
			finish();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == IMAGE_SELECT)// 浏览本地相册选择
			{
				Uri uri = data.getData();
				Cursor cursor = this.getContentResolver().query(uri, null,
						null, null, null);
				// cursor.moveToFirst();
				// for (int i = 0; i < cursor.getColumnCount(); i++)
				// {// 取得图片uri的列名和此列的详细信息
				// System.out.println(i + "-" + cursor.getColumnName(i) +
				// "-" + cursor.getString(i));
				// }
				String realPath = "";
				while (cursor.moveToNext()) {
					realPath = cursor.getString(cursor.getColumnIndex("_data"));
				}
				if (cursor != null) {
					cursor.close();
					cursor = null;
				}
				Intent it = new Intent();
				it.putExtra("picFile", realPath);
				setResult(Activity.RESULT_OK, it);
				finish();
			}
		} else // 用户没选择照片
		{
			finish();
		}
	}

}
