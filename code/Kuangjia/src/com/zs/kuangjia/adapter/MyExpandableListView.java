package com.zs.kuangjia.adapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

import com.example.kuangjia.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyExpandableListView extends BaseExpandableListAdapter {

	private ArrayList<Parent> dataSource;
	private Context context;

	public MyExpandableListView(Context context) {
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
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ChildViewHolder viewHolder = null;
		if (convertView != null) {
			viewHolder = (ChildViewHolder) convertView.getTag();
		} else {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_childview, null);
			viewHolder = new ChildViewHolder();
			viewHolder.image = (ImageView) convertView
					.findViewById(R.id.childphoto);
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.childname);
			viewHolder.sign = (TextView) convertView
					.findViewById(R.id.childsign);
			convertView.setTag(viewHolder);
		}
		viewHolder.name.setText(dataSource.get(groupPosition).mSubChild
				.get(childPosition).mName);
		viewHolder.sign.setText(dataSource.get(groupPosition).mSubChild
				.get(childPosition).mSign);
		return convertView;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return dataSource.get(arg0).mSubChild.size();
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return dataSource.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ParentViewHolder holder = null;
		if (convertView != null) {
			holder = (ParentViewHolder) convertView.getTag();
		} else {
			holder = new ParentViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_parentview, null);
			holder.groupName = (TextView) convertView
					.findViewById(R.id.parentgroupname);
			holder.onlineNum = (TextView) convertView
					.findViewById(R.id.parentonlinenum);
			holder.navi = (ImageView) convertView.findViewById(R.id.parentnavi);
			convertView.setTag(holder);
		}
		holder.groupName.setText(dataSource.get(groupPosition).mTitle);
		holder.onlineNum.setText(dataSource.get(groupPosition).mOnlineNum);
		if (isExpanded) {
			holder.navi.setImageResource(R.drawable.groupoppen);
		} else {
			holder.navi.setImageResource(R.drawable.groupclose);
		}
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 这里设置为return true才能在activity中有点击事件响应，应该是继续向下传递事件分发消息的意思
	 */
	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "你点击了:"+getChildName(arg0,arg1), 0).show();
		return true;
	}
	public String getChildName(int groupPosition,
			int childPosition){
		return dataSource.get(groupPosition).mSubChild.get(childPosition).mName;
	}

	private class ChildViewHolder {
		TextView name;
		TextView sign;
		ImageView image;
	}

	private class ParentViewHolder {
		ImageView navi;
		TextView groupName;
		TextView onlineNum;
	}
}
