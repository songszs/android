package com.zs.kuangjia;

import com.example.kuangjia.R;
import com.zs.kuangjia.adapter.ViewPagerAdapter;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ViewPagerActivity extends FragmentActivity {

	private final int SELECTPART1 = 0;
	private final int SELECTPART2 = 1;
	private final int SELECTPART3 = 2;
	private ViewPager mViewPager;
	private RadioGroup mRadioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_viewpager);
		init();
	}

	public void init() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		mViewPager = (ViewPager) findViewById(R.id.cy_viewpager);
		mRadioGroup = (RadioGroup) findViewById(R.id.cy_radiogroup);
		mRadioGroup.setOnCheckedChangeListener(new MyCheckChangeListener());
		mViewPager
				.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
		mViewPager.setOnPageChangeListener(new MyOnPageChangerListener());
	}

	private class MyCheckChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			int mSelected = SELECTPART1;
			switch (arg1) {
			case R.id.cy_radiobutton1:
				mSelected = SELECTPART1;
				break;
			case R.id.cy_radiobutton2:
				mSelected = SELECTPART2;
				break;
			case R.id.cy_radiobutton3:
				mSelected = SELECTPART3;
				break;
			}
			//获取viewpager选择的是哪一个，索引是0，1,2,3,4依次
			if (mViewPager.getCurrentItem() != mSelected) {
				mViewPager.setCurrentItem(mSelected);
			}
		}
	}

	private class MyOnPageChangerListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			int vpItem = mViewPager.getCurrentItem();
			switch (vpItem) {
			case SELECTPART1:
				mRadioGroup.check(R.id.cy_radiobutton1);
				break;
			case SELECTPART2:
				mRadioGroup.check(R.id.cy_radiobutton2);
				break;
			case SELECTPART3:
				mRadioGroup.check(R.id.cy_radiobutton3);
				break;
			}
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
