package com.zs.kuangjia.cyfragment;

import com.example.kuangjia.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zs.kuangjia.adapter.SwipeListViewAdapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 需要 引用library
 * 
 * @author Administrator
 * 
 */
public class RefreshListViewFragment extends Fragment {

	private PullToRefreshListView listView;
	private SwipeListViewAdapter adp;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 1:
				Toast.makeText(getActivity(), "刷新成功", 0).show();
				listView.onRefreshComplete();
				adp.notifyDataSetChanged();
				break;
			case 2:
				Toast.makeText(getActivity(), "加载成功", 0).show();
				adp.notifyDataSetChanged();
				listView.onRefreshComplete();
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adp = new SwipeListViewAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = null;
		v = inflater.inflate(R.layout.cy_refreshlistview, null);
		listView = (PullToRefreshListView) v.findViewById(R.id.refreshlist);
		listView.setAdapter(adp);
		listView.setMode(Mode.BOTH);
		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				new Thread(new AddRunable(1)).start();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				new Thread(new AddRunable(2)).start();
			}
		});
		return v;
	}

	private class AddRunable implements Runnable {

		private int i;

		public AddRunable(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
				if(i==1){
					adp.resetData();
				}else{
					adp.addData();
				}
				Message msg = handler.obtainMessage();
				msg.what = i;
				handler.sendMessage(msg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
