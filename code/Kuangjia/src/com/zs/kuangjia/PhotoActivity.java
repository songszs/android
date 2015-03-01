package com.zs.kuangjia;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import com.example.kuangjia.R;
import com.zs.kuangjia.extra.CameraActivity;
import com.zs.kuangjia.extra.LocalImageActivity;
import com.zs.kuangjia.fragment.main.HomeFragment;
import com.zs.kuangjia.fragment.main.InstanceFragment;
import com.zs.kuangjia.fragment.main.NoteFragment;
import com.zs.kuangjia.fragment.main.PersonFragment;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;

public class PhotoActivity extends Activity {

	private ActionBar actionbar;
	private ImageView img;
	private static final int CODE_PHOTO = 1;
	private static final int CODE_LOCALPIC = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		init();
		setActBar();
	}

	public void init() {
		actionbar = getActionBar();
		img = (ImageView) findViewById(R.id.imageView1);
	}

	public void setActBar() {
		actionbar.setDisplayHomeAsUpEnabled(true);
	}

	public void myClick(View v) {
		switch (v.getId()) {
		case R.id.photo_tp:
			createDialog();
			break;
		case R.id.photo_cz:
			img.setImageResource(R.drawable.sl_icon4);
			break;
		}
	}

	/**
	 * �����Ի���
	 */
	public void createDialog() {
		String[] itemName = { "����", "����ͼƬ" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("��ѡ��");
		builder.setItems(itemName, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent;
				switch (which) {
				case 0:
					intent = new Intent(PhotoActivity.this,
							CameraActivity.class);
					startActivityForResult(intent, CODE_PHOTO);
					break;
				case 1:
					intent = new Intent(PhotoActivity.this,
							LocalImageActivity.class);
					startActivityForResult(intent, CODE_LOCALPIC);
					break;
				}
			}
		});
		builder.create().show();
	}

	/**
	 * �������̣� 1.��������վʹ���һ��ͼƬ�ļ���Ȼ�����մ浽���ļ��� 2.����Ǳ���ͼƬ��ֱ�ӻ�ȡ����ͼƬ·�������뼴�� �������� ���Ǵ�����Ƭ·��
	 * 
	 * �� 1.������requestCode �������ֲ�ͬ������Դ��Ҳ���Ƕ������ͬһ��activityʱ����� 2.�����resultCode
	 * �������ֲ�ͬ�Ľ����Ҳ����ͬһ������������activityʱ����ֵ����
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			String path = data.getStringExtra("picFile");
			Bitmap bmp = BitmapFactory.decodeFile(path);
			switch (resultCode) {
			case Activity.RESULT_OK:
				if (requestCode == CODE_PHOTO) {
					img.setImageBitmap(bmp);
				}
				if (requestCode == CODE_LOCALPIC) {
					img.setImageBitmap(bmp);
				}
				break;
			default:
				break;
			}
		} else {
			Toast.makeText(PhotoActivity.this, "û�л��ͼƬ", 0).show();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
