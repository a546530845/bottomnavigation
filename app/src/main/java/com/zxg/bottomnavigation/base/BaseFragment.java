package com.zxg.bottomnavigation.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.zxg.bottomnavigation.R;
import com.zxg.dialogtemplate.CustomDialog;
import com.zxg.dialogtemplate.MyDialogUtil;
import com.zxg.loadview.LoadViewHelper;


public abstract class BaseFragment extends Fragment {

	public Activity mContext;
	//共享参数
	private CustomDialog proDialog;

	public LoadViewHelper helper;//加载显示图
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this.getActivity();
		dealLogicBeforeOnCreate();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	/**
	 * 在实例化之前处理的逻辑
	 */
	public abstract void dealLogicBeforeOnCreate();

	/**
	 * 在实例化之后处理的逻辑
	 */
	public abstract void dealLogicAfterOnCreate();

	/*
	 * 跳转到下一个页面的动作
	 */
	protected void GotoAnim() {
		this.getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}
	public void finish() {
		MyActivityManager.getInstance().removeActivity(this.getActivity());
		super.getActivity().finish();
	}
	public void instanceLoadingViewHelp(View mView){
		helper = new LoadViewHelper(mView);
	}
	/*
   * 显示progressDialog
   */
	public void ShowProDialog(Context mContext, String msg, boolean cancelable) {
		if (proDialog == null) {

			proDialog = MyDialogUtil.showRotateDialog(mContext, msg, cancelable);
		} else {
			if (!proDialog.isShowing()) {
				proDialog = MyDialogUtil.showRotateDialog(mContext, msg, cancelable);
			}
		}
	}

	/*
     * 关闭progressDialog
     */
	public void DissProDialog() {
		if (proDialog != null) {
			proDialog.dismiss();
			proDialog = null;
		}
	}
}
