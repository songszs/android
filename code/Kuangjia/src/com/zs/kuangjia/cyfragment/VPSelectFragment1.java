package com.zs.kuangjia.cyfragment;

import com.example.kuangjia.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VPSelectFragment1 extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub\
		View v = null;
		v = inflater.inflate(R.layout.cy_viewpager_fg1, null);
		TextView text = (TextView) v.findViewById(R.id.cy_viewpager_fg1_text);
		text.setText("Fragment1");
		return v;
	}

}
