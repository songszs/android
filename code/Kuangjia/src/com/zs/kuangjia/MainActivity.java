package com.zs.kuangjia;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import com.example.kuangjia.R;
import com.zs.kuangjia.adapter.ItemAdapter;
import com.zs.kuangjia.drawerarrowdrawable.DrawerArrowDrawable;
import com.zs.kuangjia.fragment.main.HomeFragment;
import com.zs.kuangjia.fragment.main.InstanceFragment;
import com.zs.kuangjia.fragment.main.NoteFragment;
import com.zs.kuangjia.fragment.main.PersonFragment;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private long mExitTime = 0;
	private ActionBar actionbar;
	private SparseArray<Fragment> map = new SparseArray<Fragment>();
	private FragmentManager manager;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private ListView mListView;
	private ItemAdapter mAdapter;
	private View mAbView;
	private DrawerArrowDrawable drawerArrowDrawable;
	private ImageView mArrawImageView;
	private float offset;
	private boolean flipped;
	private TextView mTitle;
	private PopupWindow mPopupWindow;
	private View mPopupView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		initView();
		// getOverflowMenu();
	}

	/**
	 * ��ʼ��
	 */
	private void initView() {
		manager = getFragmentManager();
		setActbar();
		// ���ò໬�˵�
		mListView = (ListView) findViewById(R.id.main_left_drawer);
		ArrayList<String> mData = new ArrayList<String>();
		mData.add("item1");
		mData.add("item2");
		mData.add("item3");
		mData.add("item4");
		mAdapter = new ItemAdapter(mData, this);
		initPopupWindow();
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "click item" + (arg2 + 1), 0)
						.show();
				mDrawerLayout.closeDrawer(android.view.Gravity.START);
			}
		});
		mArrawImageView = (ImageView) mAbView
				.findViewById(R.id.actbar_arrow_imageview);
		mTitle = (TextView) findViewById(R.id.actbar_title);
		drawerArrowDrawable = new DrawerArrowDrawable(getResources());
		drawerArrowDrawable.setStrokeColor(getResources().getColor(
				R.color.color_white));
		mArrawImageView.setImageDrawable(drawerArrowDrawable);
		mArrawImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mDrawerLayout.isDrawerVisible(android.view.Gravity.START)) {
					mDrawerLayout.closeDrawer(android.view.Gravity.START);
				} else {
					mDrawerLayout.openDrawer(android.view.Gravity.START);
				}
			}
		});

		mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer_home_pressed, R.string.radioname1,
				R.string.radioname2) {

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
			}
		};
		// mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerLayout
				.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
					@Override
					public void onDrawerSlide(View drawerView, float slideOffset) {
						offset = slideOffset;
						// Sometimes slideOffset ends up so close to but not
						// quite 1 or 0.
						if (slideOffset >= .995) {
							flipped = true;
							drawerArrowDrawable.setFlip(flipped);
						} else if (slideOffset <= .005) {
							flipped = false;
							drawerArrowDrawable.setFlip(flipped);
						}
						drawerArrowDrawable.setParameter(offset);
					}
				});
		// �������ò໬�˵�

		addMap(R.id.navi_item1, new HomeFragment());
		addMap(R.id.navi_item2, new InstanceFragment());
		addMap(R.id.navi_item3, new NoteFragment());
		// addMap(R.id.navi_item4, new PersonFragment());
		switchContent(R.id.navi_item1, manager);
	}

	/**
	 * ����actionbar
	 */
	private void setActbar() {
		actionbar = getActionBar();
		// getActionBar().setDisplayHomeAsUpEnabled(true);
		// getActionBar().setHomeButtonEnabled(true);
		// actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
		mAbView = LayoutInflater.from(this).inflate(R.layout.actionbar_layout,
				null);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		actionbar.setCustomView(mAbView, params);
		actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionbar.setDisplayShowCustomEnabled(true);
	}

	/**
	 * �л��߼��������ж� ��ʡ����
	 * 
	 * @param v
	 */
	private boolean switchSelected(View v) {
		if (map.indexOfKey(v.getId()) < 0) {
			return false;
		}
		if (!v.isSelected()) {
			switchContent(v.getId(), manager);
		} else
			return false;
		return true;
	}

	/**
	 * �л�fragment����
	 * 
	 * @param id
	 *            ����� �·� ������id
	 * @param fg
	 *            fragment ������
	 */
	private void switchContent(int id, FragmentManager fg) {
		String tag = String.valueOf(id);
		FragmentTransaction transaction = fg.beginTransaction();
		if (fg.findFragmentByTag(tag) == null) {
			transaction.replace(R.id.content_frame, map.get(id), tag);
			// transaction.addToBackStack(tag);
		} else {
			transaction.replace(R.id.content_frame, fg.findFragmentByTag(tag),
					tag);
		}
		transaction.commit();
		// ���� ����
		for (int i = 0; i < map.size(); i++) {
			if (id == map.keyAt(i)) {
				findViewById(id).setSelected(true);
			} else {
				findViewById(map.keyAt(i)).setSelected(false);
			}
		}
		// �л�actionbar��ʽ
		switch (id) {
		case R.id.navi_item1:
			mTitle.setText("Home");
			break;
		case R.id.navi_item2:
			mTitle.setText("Demo");
			break;
		case R.id.navi_item3:
			mTitle.setText("����");
			// actionbar.setDisplayShowCustomEnabled(false);
			// actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
			break;
		}
	}

	/**
	 * Ϊview����л��¼�������������ӵ� map
	 * 
	 * @param id
	 *            ������浼����id
	 * @param fg
	 *            ��ӵ�fragment
	 */
	private void addMap(int id, Fragment fg) {
		View v = findViewById(id);
		v.setSelected(false);
		v.setOnClickListener(new MyListener());
		map.put(id, fg);
	}

	private class MyListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			if (switchSelected(view))
				return;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * ��overflowʼ����ʾ
	 */
	private void getOverflowMenu() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��overflow�е�ѡ����ʾͼ��
	 */
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

	public void myClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.sl_jckj:
			intent.setClass(this, JckjActivity.class);
			startActivity(intent);
			break;
		case R.id.sl_zdy:
			intent.setClass(this, ViewPagerActivity.class);
			startActivity(intent);
			break;
		case R.id.sl_bj:
			break;
		case R.id.sl_cy:
			intent.setClass(this, CyActivity.class);
			startActivity(intent);
			break;
		case R.id.sl_xxts:
			pushMessage();
			break;
		case R.id.sl_pz:
			intent.setClass(this, PhotoActivity.class);
			startActivity(intent);
			break;
		case R.id.sl_viewpager:
			intent.setClass(this, ViewPagerActivity.class);
			startActivity(intent);
			break;
		// actionbar user
		case R.id.actbar_user:
			// intent.setClass(this, PhotoActivity.class);
			// startActivity(intent);

			// mPopupWindow.update(mPopupView,
			// ViewGroup.LayoutParams.FILL_PARENT,
			// ViewGroup.LayoutParams.WRAP_CONTENT);
			Rect frame = new Rect();
			getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
			int statusBarHeight = frame.top;
			mPopupWindow.showAtLocation(mPopupView,
					Gravity.RIGHT | Gravity.TOP, 20, actionbar.getHeight()
							+ statusBarHeight);
			// mPopupWindow.showAsDropDown(actionbar.getCustomView().findViewById(R.id.actbar_user),0,20);
			break;
		}
	}

	/**
	 * ��״̬�� ������Ϣ
	 */
	private void pushMessage() {
		// ��ȡϵͳ���͹�����
		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// ����������Ϣ �ֱ�Ϊͼ�� �����͹�����Ϣ��ʱ��
		Notification notification = new Notification(R.drawable.ic_launcher,
				"�����Ϣ���Ͳ���", System.currentTimeMillis());
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		// FLAG_AUTO_CANCEL ��֪ͨ�ܱ�״̬���������ť�������
		// FLAG_NO_CLEAR ��֪ͨ���ܱ�״̬���������ť�������
		// FLAG_ONGOING_EVENT ֪ͨ��������������
		// FLAG_INSISTENT �Ƿ�һֱ���У���������һֱ���ţ�֪���û���Ӧ
		// notification.flags |= Notification.FLAG_ONGOING_EVENT; //
		// ����֪ͨ�ŵ�֪ͨ����"Ongoing"��"��������"����
		// notification.flags |= Notification.FLAG_NO_CLEAR; //
		// �����ڵ����֪ͨ���е�"���֪ͨ"�󣬴�֪ͨ�������������FLAG_ONGOING_EVENTһ��ʹ��
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		// DEFAULT_ALL ʹ������Ĭ��ֵ�������������𶯣������ȵ�
		// DEFAULT_LIGHTS ʹ��Ĭ��������ʾ
		// DEFAULT_SOUNDS ʹ��Ĭ����ʾ����
		// DEFAULT_VIBRATE ʹ��Ĭ���ֻ��𶯣������<uses-permission
		// android:name="android.permission.VIBRATE" />Ȩ��
		notification.defaults = Notification.DEFAULT_LIGHTS;
		// ����Ч������
		// notification.defaults=Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND;
		notification.ledARGB = Color.BLUE;
		notification.ledOnMS = 5000; // ����ʱ�䣬����

		// ����֪ͨ���¼���Ϣ
		CharSequence contentTitle = "��ܲ���֪ͨ������"; // ֪ͨ������
		CharSequence contentText = "��ܲ���֪ͨ������"; // ֪ͨ������
		Intent notificationIntent = new Intent(MainActivity.this,
				MainActivity.class); // �����֪ͨ��Ҫ��ת��Activity
		PendingIntent contentItent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(this, contentTitle, contentText,
				contentItent);

		// ��Notification���ݸ�NotificationManager
		manager.notify(0, notification);
	}

	/**
	 * ��״̬��ɾ����Ϣ
	 */
	private void clearNotification() {
		// ������ɾ��֮ǰ���Ƕ����֪ͨ
		NotificationManager notificationManager = (NotificationManager) this
				.getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(0);
	}

	private void initPopupWindow() {
		mPopupView = LayoutInflater.from(this).inflate(
				R.layout.popupwindow_view, null);
		ListView mPopListView = (ListView) mPopupView
				.findViewById(R.id.popupwindow_listview);
		ItemAdapter adpter;
		ArrayList<String> mData = new ArrayList<String>();
		mData.add("item1");
		mData.add("item2");
		mData.add("item3");
		mData.add("item4");
		adpter = new ItemAdapter(mData, this);
		mPopListView.setAdapter(adpter);
		mPopListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,
						"click popupwindow item" + (arg2 + 1), 0).show();
				mPopupWindow.dismiss();
			}
		});

		mPopupWindow = new PopupWindow(mPopupView,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
		// mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xffffff));
		mPopupWindow.setFocusable(true);
		mPopupWindow.setOutsideTouchable(true);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - mExitTime > 2000) {
				Toast.makeText(MainActivity.this, "�ڰ�һ���˳�", 0).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
	}

}
