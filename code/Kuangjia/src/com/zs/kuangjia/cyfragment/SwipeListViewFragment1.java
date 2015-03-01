package com.zs.kuangjia.cyfragment;

import java.util.ArrayList;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.example.kuangjia.R;
import com.zs.kuangjia.adapter.Child;
import com.zs.kuangjia.adapter.SwipeListViewAdapter;
import com.zs.kuangjia.adapter.SwipeListViewAdapter1;
import android.app.Fragment;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class SwipeListViewFragment1 extends Fragment {

	private SwipeMenuListView mListView;
	private SwipeListViewAdapter1 adp;
	private ArrayList<Child> mSourceData = new ArrayList<Child>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = null;
		v = inflater.inflate(R.layout.cy_swipelistview1, null);
		mListView = (SwipeMenuListView) v.findViewById(R.id.listView);
		init();
		return v;
	}

	private void init() {
		// step 1. create a MenuCreator
		addData();
		SwipeMenuCreator creator = new SwipeMenuCreator() {
			@Override
			public void create(SwipeMenu menu) {
				// create "open" item
				SwipeMenuItem openItem = new SwipeMenuItem(getActivity());
				// set item background
				openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
						0xCE)));
				// set item width
				openItem.setWidth(dp2px(90));
				// set item title
				openItem.setTitle("Open");
				// set item title fontsize
				openItem.setTitleSize(18);
				// set item title font color
				openItem.setTitleColor(Color.WHITE);
				// add to menu
				menu.addMenuItem(openItem);
				// create "delete" item
				SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity());
				// set item background
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
						0x3F, 0x25)));
				// set item width
				deleteItem.setWidth(dp2px(90));
				// set a icon
				deleteItem.setIcon(R.drawable.ic_delete);
				// add to menu
				menu.addMenuItem(deleteItem);
			}
		};
		// set creator
		mListView.setMenuCreator(creator);

		TextView emptyView = new TextView(getActivity());
		emptyView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		emptyView.setText("无数据");
		emptyView.setGravity(Gravity.CENTER_HORIZONTAL
				| Gravity.CENTER_VERTICAL);
		emptyView.setVisibility(View.GONE);
		((ViewGroup) mListView.getParent()).addView(emptyView);
		mListView.setEmptyView(emptyView);
		adp = new SwipeListViewAdapter1(getActivity(), mSourceData);
		mListView.setAdapter(adp);

		mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu,
					int index) {
				switch (index) {
				case 0:
					// open
					Toast.makeText(getActivity(), "你点击了"+mSourceData.get(position).getmName(), 0).show();
					break;
				case 1:
					// delete
					// delete(item);
					mSourceData.remove(position);
					adp.notifyDataSetChanged();
					break;
				}
				return false;
			}
		});
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}

	private void addData() {
		Child c6 = new Child("Group1", "mytest mytest");
		Child c7 = new Child("Group2", "mytest mytest");
		Child c8 = new Child("Group3", "mytest mytest");
		Child c9 = new Child("Group4", "mytest mytest");
		mSourceData.add(c6);
		mSourceData.add(c7);
		mSourceData.add(c8);
		mSourceData.add(c9);
	}
}
