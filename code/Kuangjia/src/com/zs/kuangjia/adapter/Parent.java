package com.zs.kuangjia.adapter;

import java.util.ArrayList;

/**
 * ≤‚ ‘¿‡
 * @author Administrator
 *
 */
public class Parent {
	public Parent(String title, String onLineNum) {
		mSubChild = new ArrayList<Child>();
		this.mTitle = title;
		this.mOnlineNum = onLineNum;
	}

	public void addChild(Child child) {
		mSubChild.add(child);
	}

	ArrayList<Child> mSubChild;
	String mTitle;
	int mDrawId;
	String mOnlineNum;

}
