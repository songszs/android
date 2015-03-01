package com.zs.kuangjia.adapter;

import java.util.ArrayList;

import com.example.kuangjia.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

	private ArrayList<String> mSourceData;
	private LayoutInflater mInflater;

	public ItemAdapter(ArrayList<String> data, Context context) {
		mSourceData = data;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mSourceData.size();
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
	public View getView(int arg0, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder mHolder;
		if (v == null) {
			v = mInflater.inflate(R.layout.item_menuitem, null);
			mHolder = new ViewHolder();
			mHolder.image = (ImageView) v.findViewById(R.id.item_menu_photo);
			mHolder.textView = (TextView) v
					.findViewById(R.id.item_menu_itemname);
			v.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) v.getTag();
		}
		mHolder.textView.setText(mSourceData.get(arg0));
		return v;
	}

	private class ViewHolder {
		public ImageView image;
		public TextView textView;
	}

}
