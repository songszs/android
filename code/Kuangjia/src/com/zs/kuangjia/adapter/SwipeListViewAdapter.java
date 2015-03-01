package com.zs.kuangjia.adapter;

import java.util.ArrayList;
import java.util.zip.Inflater;
import com.example.kuangjia.R;
import com.zs.kuangjia.swipelistview.SwipeListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SwipeListViewAdapter extends BaseAdapter {

	private ArrayList<Parent> dataSource;
	private Context context;
	private SwipeListView listView;

	public SwipeListViewAdapter(Context context,SwipeListView listView){
		this(context);
		this.listView = listView;
	}
	public SwipeListViewAdapter(Context context) {
		this.context = context;
		Parent p1 = new Parent("Group1", "(4/10)");
		Parent p2 = new Parent("Group2", "(5/20)");
		Parent p3 = new Parent("Group3", "(4/30)");

		Child c1 = new Child("Test11", "mytest");
		Child c2 = new Child("Test12", "mytest");
		Child c3 = new Child("Test13", "mytest");
		Child c4 = new Child("Test14", "mytest");
		Child c5 = new Child("Test15", "mytest");
		p1.addChild(c1);
		p1.addChild(c2);
		p1.addChild(c3);
		p1.addChild(c4);
		p1.addChild(c5);

		Child c6 = new Child("Test21", "mytest");
		Child c7 = new Child("Test22", "mytest");
		Child c8 = new Child("Test23", "mytest");
		Child c9 = new Child("Test24", "mytest");
		p2.addChild(c6);
		p2.addChild(c7);
		p2.addChild(c8);
		p2.addChild(c9);

		Child c10 = new Child("Test31", "mytest");
		Child c11 = new Child("Test32", "mytest");
		Child c12 = new Child("Test33", "mytest");
		p3.addChild(c10);
		p3.addChild(c11);
		p3.addChild(c12);

		dataSource = new ArrayList<Parent>();
		dataSource.add(p1);
		dataSource.add(p2);
		dataSource.add(p3);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataSource.get(0).mSubChild.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ChildViewHolder viewHolder = null;
		if (convertView != null) {
			viewHolder = (ChildViewHolder) convertView.getTag();
		} else {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_swipelistview, null);
			viewHolder = new ChildViewHolder();
			viewHolder.image = (ImageView) convertView
					.findViewById(R.id.childphoto);
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.childname);
			viewHolder.sign = (TextView) convertView
					.findViewById(R.id.childsign);
//			viewHolder.delete = (Button) convertView
//					.findViewById(R.id.id_delete);
			convertView.setTag(viewHolder);
		}
		viewHolder.name.setText(dataSource.get(0).mSubChild.get(arg0).mName);
		viewHolder.sign.setText(dataSource.get(0).mSubChild.get(arg0).mSign);
//		viewHolder.delete.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View view) {
//				// TODO Auto-generated method stub
//				Toast.makeText(context, "已删除"+dataSource.get(0).mSubChild.get(arg0).mName, 0).show();
//				dataSource.get(0).mSubChild.remove(arg0);
//				notifyDataSetInvalidated();
//				listView.closeAnimate(arg0);
//				/**
//				 * 关闭SwipeListView 不关闭的话，刚删除位置的item存在问题
//				 * 在监听事件中onListChange中关闭，会出现问题
//				 */
//				// mSwipeListView.closeOpenedItems();
//			}
//		});
		return convertView;
	}

	private class ChildViewHolder {
		TextView name;
		TextView sign;
		ImageView image;
		Button delete;
	}

	public void addData() {
		Child c6 = new Child("addtest11", "mytest");
		Child c7 = new Child("addtest12", "mytest");
		Child c8 = new Child("addtest13", "mytest");
		Child c9 = new Child("addtest14", "mytest");
		dataSource.get(0).mSubChild.add(c6);
		dataSource.get(0).mSubChild.add(c7);
		dataSource.get(0).mSubChild.add(c8);
		dataSource.get(0).mSubChild.add(c9);
	}

	public void resetData() {
		dataSource.clear();
		Parent p1 = new Parent("Group1", "(4/10)");
		Child c1 = new Child("Test11", "mytest");
		Child c2 = new Child("Test12", "mytest");
		Child c3 = new Child("Test13", "mytest");
		Child c4 = new Child("Test14", "mytest");
		Child c5 = new Child("Test15", "mytest");
		p1.addChild(c1);
		p1.addChild(c2);
		p1.addChild(c3);
		p1.addChild(c4);
		p1.addChild(c5);
		dataSource.add(p1);
	}

}
