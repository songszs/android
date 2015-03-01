package com.zs.kuangjia.cyfragment;

import com.example.kuangjia.R;
import com.zs.kuangjia.adapter.MyExpandableListView;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

public class ExpandableListViewFragment extends Fragment {

	private ExpandableListView list;
	private MyExpandableListView adp ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.cy_expandablablelistview, null);
		list = (ExpandableListView) v.findViewById(R.id.expendlist);
		init();
		return v;
	}
	public void init(){
		adp = new MyExpandableListView(getActivity());
		list.setAdapter(adp);
		list.setGroupIndicator(null);
		list.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View arg1, int groupPosition,
					int childPosition, long arg4) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

}
