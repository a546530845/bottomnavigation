package com.zxg.loadview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VaryViewHelper {
	/**
	 * 需要替代的view
	 */
	private View view;
	private ViewGroup parentView;
	/**
	 * view处于父类的布局的位置下标
	 */
	private int viewIndex;
	private ViewGroup.LayoutParams params;
	private View currentView;
	public boolean enable = true;

	public VaryViewHelper(View view) {
		super();
		this.view = view;
	}

	private void init() {
		params = view.getLayoutParams();
		if (view.getParent() != null) {
			parentView = (ViewGroup) view.getParent();
		} else {
			parentView = (ViewGroup) view.getRootView().findViewById(android.R.id.content);
		}
		int count = parentView.getChildCount();
		for (int index = 0; index < count; index++) {
			if (view == parentView.getChildAt(index)) {
				viewIndex = index;
				break;
			}
		}
		currentView = view;
	}

	public View getCurrentLayout() {
		return currentView;
	}

	/**
	 * 重置换回原来的布局
	 */
	public void restoreView() {
		showLayout(view);
	}

	public void showLayout(View view) {
		if (!enable)
			return;
		if (parentView == null) {
			init();
		}
		this.currentView = view;
		// 如果已经是那个view，那就不需要再进行替换操作了
		if (parentView.getChildAt(viewIndex) != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
			parentView.removeViewAt(viewIndex);
			parentView.addView(view, viewIndex, params);
		}
	}

	public void showLayout(int layoutId) {
		showLayout(inflate(layoutId));
	}

	public View inflate(int layoutId) {
		return LayoutInflater.from(view.getContext()).inflate(layoutId, null);
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Context getContext() {
		return view.getContext();
	}

	public View getView() {
		return view;
	}
}
