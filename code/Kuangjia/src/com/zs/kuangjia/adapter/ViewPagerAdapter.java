package com.zs.kuangjia.adapter;

import java.util.ArrayList;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import com.zs.kuangjia.cyfragment.VPSelectFragment1;
import com.zs.kuangjia.cyfragment.VPSelectFragment2;
import com.zs.kuangjia.cyfragment.VPSelectFragment3;


public class ViewPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<Fragment> data ;
	public ViewPagerAdapter(android.support.v4.app.FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
		data = new  ArrayList<Fragment>();
		VPSelectFragment1 mVp1  = new VPSelectFragment1();
		VPSelectFragment2 mVp2  = new VPSelectFragment2();
		VPSelectFragment3 mVp3  = new VPSelectFragment3();
		data.add(mVp1);
		data.add(mVp2);
		data.add(mVp3);
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

}
