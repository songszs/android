package com.zs.kuangjia;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import com.example.kuangjia.R;
import com.zs.kuangjia.cyfragment.ExpandableListViewFragment;
import com.zs.kuangjia.cyfragment.IndexableListViewFragment;
import com.zs.kuangjia.cyfragment.RefreshListViewFragment;
import com.zs.kuangjia.cyfragment.SwipeListViewFragment;
import com.zs.kuangjia.cyfragment.SwipeListViewFragment1;
import com.zs.kuangjia.fragment.main.HomeFragment;
import com.zs.kuangjia.fragment.main.InstanceFragment;
import com.zs.kuangjia.fragment.main.NoteFragment;
import com.zs.kuangjia.fragment.main.PersonFragment;
import com.zs.kuangjia.indexablelistview.IndexableListView;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.os.Build;

public class CyActivity extends Activity {

	private ActionBar actionbar;
	private FragmentManager manager;
	private SpinnerAdapter adpter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy);
		init();
		setActBar();
	}

	public void init() {
//		adpter = ArrayAdapter.createFromResource(this, R.array.cy,
//				android.R.layout.simple_spinner_dropdown_item);
		adpter = ArrayAdapter.createFromResource(this, R.array.cy,
				R.layout.actbar_checktextview);
		actionbar = getActionBar();
	}

	public void setActBar() {
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionbar.setListNavigationCallbacks(adpter, new MyNavigationListener());
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private class MyNavigationListener implements OnNavigationListener {

		@Override
		public boolean onNavigationItemSelected(int arg0, long arg1) {
			// TODO Auto-generated method stub
			Fragment fg = null;
			switch (arg0) {
			case 0:
				fg = new ExpandableListViewFragment();
				break;
			case 1:
				fg = new SwipeListViewFragment1();
				break;
			case 2:
				fg = new RefreshListViewFragment();
				break;
			case 3:
				fg = new IndexableListViewFragment();
				break;
			default:
				break;
			}
			getFragmentManager().beginTransaction().replace(R.id.swtichcontent, fg).commit();
			return true;
		}
	}
}
