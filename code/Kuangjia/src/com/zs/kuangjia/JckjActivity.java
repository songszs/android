package com.zs.kuangjia;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.example.kuangjia.R;
import com.zs.kuangjia.fragment.main.HomeFragment;
import com.zs.kuangjia.fragment.main.InstanceFragment;
import com.zs.kuangjia.fragment.main.NoteFragment;
import com.zs.kuangjia.fragment.main.PersonFragment;

import android.app.ActionBar;
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
import android.os.Build;

public class JckjActivity extends Activity {

	private ActionBar actionbar;
	private FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jckj);
		init();
		setActBar();
	}
	public void init(){
		actionbar = getActionBar();
	}
	public void setActBar(){
		actionbar.setDisplayHomeAsUpEnabled(true);
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

}
