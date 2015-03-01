package com.zs.kuangjia.cyfragment;

import com.example.kuangjia.R;
import com.zs.kuangjia.adapter.MyExpandableListView;
import com.zs.kuangjia.adapter.SwipeListViewAdapter;
import com.zs.kuangjia.swipelistview.BaseSwipeListViewListener;
import com.zs.kuangjia.swipelistview.SwipeListView;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

/**
 * 需要jar包nineoldandroids（开源动画jar包） 和 swipelistview 包中的类文件
 * 
 * @author Administrator
 * 
 */
public class SwipeListViewFragment extends Fragment {

	private SwipeListView list;
	private SwipeListViewAdapter adp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.cy_swipelistview, null);
		list = (SwipeListView) v.findViewById(R.id.example_lv_list);
		init();
		return v;
	}

	public void init() {
		TextView emptyView = new TextView(getActivity());
		emptyView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		emptyView.setText("数据被你删完了");
		emptyView.setGravity(Gravity.CENTER_HORIZONTAL
				| Gravity.CENTER_VERTICAL);
		emptyView.setVisibility(View.GONE);
		((ViewGroup) list.getParent()).addView(emptyView);
		list.setEmptyView(emptyView);
		adp = new SwipeListViewAdapter(getActivity(), list);
		list.setAdapter(adp);
		list.setSwipeListViewListener(new BaseSwipeListViewListener() {

			@Override
			public void onOpened(int position, boolean toRight) {
				// TODO Auto-generated method stub
				super.onOpened(position, toRight);
			}

			@Override
			public void onClosed(int position, boolean fromRight) {
				// TODO Auto-generated method stub
				super.onClosed(position, fromRight);
			}

			@Override
			public void onListChanged() {
				// TODO Auto-generated method stub
				list.closeOpenedItems();
				super.onListChanged();
			}

			@Override
			public void onMove(int position, float x) {
				// TODO Auto-generated method stub
				super.onMove(position, x);
			}

			@Override
			public void onStartOpen(int position, int action, boolean right) {
				// TODO Auto-generated method stub
				super.onStartOpen(position, action, right);
			}

			@Override
			public void onStartClose(int position, boolean right) {
				// TODO Auto-generated method stub
				super.onStartClose(position, right);
			}

			@Override
			public void onClickFrontView(int position) {
				// TODO Auto-generated method stub
				super.onClickFrontView(position);
			}

			@Override
			public void onClickBackView(int position) {
				// TODO Auto-generated method stub
				super.onClickBackView(position);
			}

			@Override
			public void onDismiss(int[] reverseSortedPositions) {
				// TODO Auto-generated method stub
				super.onDismiss(reverseSortedPositions);
			}

			@Override
			public int onChangeSwipeMode(int position) {
				// TODO Auto-generated method stub
				return super.onChangeSwipeMode(position);
			}

			@Override
			public void onChoiceChanged(int position, boolean selected) {
				// TODO Auto-generated method stub
				super.onChoiceChanged(position, selected);
			}

			@Override
			public void onChoiceStarted() {
				// TODO Auto-generated method stub
				super.onChoiceStarted();
			}

			@Override
			public void onChoiceEnded() {
				// TODO Auto-generated method stub
				super.onChoiceEnded();
			}

			@Override
			public void onFirstListItem() {
				// TODO Auto-generated method stub
				super.onFirstListItem();
			}

			@Override
			public void onLastListItem() {
				// TODO Auto-generated method stub
				super.onLastListItem();
			}

		});
	}

}
