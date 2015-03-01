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

public class SwipeListViewAdapter1 extends BaseAdapter {

	private ArrayList<Child> dataSource;
	private Context context;

	public SwipeListViewAdapter1(Context context, ArrayList<Child> dataSource) {
		this.context = context;
		this.dataSource = dataSource;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataSource.size();
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
		viewHolder.name.setText(dataSource.get(arg0).mName);
		viewHolder.sign.setText(dataSource.get(arg0).mSign);
		return convertView;
	}

	private class ChildViewHolder {
		TextView name;
		TextView sign;
		ImageView image;
		Button delete;
	}


}
