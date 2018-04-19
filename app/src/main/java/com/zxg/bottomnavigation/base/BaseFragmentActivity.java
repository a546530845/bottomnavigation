package com.zxg.bottomnavigation.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.zxg.bottomnavigation.R;
import com.zxg.bottomnavigation.utils.About_keyboard;
import com.zxg.bottomnavigation.utils.SystemBarTintManager;
import com.zxg.dialogtemplate.CustomDialog;
import com.zxg.dialogtemplate.MyDialogUtil;


/**
 * 基础Activity
 *
 * @author zxg
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
    private final static String TAG = "BaseFragmentActivity";
    private CustomDialog proDialog;
    public Activity mContext;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MyActivityManager.getInstance().pushActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;
        setStatusbar();
        init(bundle);

    }

    /**
     * 初始化操作
     */
    private void init(Bundle bundle) {
        this.beforeInitView();
        this.setLayoutView();
        this.initTitleView();
        this.initView(bundle);
        this.initListener();
        this.initData();
    }

    /**
     * 如果重写此方法，systembar不做修改，使用系统默认状态
     */
    public void setStatusbar() {
//		setStausBarColorOrDrawable(R.color.white,-1);
        setStausBarColorOrDrawable(-1, R.mipmap.systembar_title);
    }

    /**
     * 设置systembar的状态为颜色填充还是图片填充
     * @param colorID
     * @param drawable
     */
    public void setStausBarColorOrDrawable(int colorID, int drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            if (colorID != -1) {
                tintManager.setStatusBarTintColor(getResources().getColor(colorID));
                tintManager.setStatusBarAlpha(0.0f);
            } else {
                tintManager.setStatusBarTintDrawable(getResources().getDrawable(drawable));
            }

        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 监听控件<br>
     * 页面跳转时，最先获取数据
     */
    protected abstract void beforeInitView();

    /**
     * 监听控件<br>
     */
    protected abstract void setLayoutView();

    /**
     * 监听控件<br>
     * 调用完setContentView之后马上调用 用于实例化TitleLayout
     */
    protected abstract void initTitleView();

    /**
     * 监听控件<br>
     * 调用完initTitleView之后马上调用 实例化Activity用到的控件
     */
    protected abstract void initView(Bundle bundle);

    /**
     * 初始化设定<br>
     * 调用完跟随initView之后调用 给控件设置设置事件
     */
    protected abstract void initListener();

    /**
     * 初始化设定<br>
     * 调用完跟随initView之后调用 给控件设置上信息
     */
    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void finish() {
        MyActivityManager.getInstance().removeActivity(this);

        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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

    /**
     * 返回到上一页面（退出）
     */
    protected void turnToBack() {
        About_keyboard.hideSoftInput(this);
        finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    /*
     * 跳转到下一个页面的动作
     */
    protected void GotoAnim() {
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

}