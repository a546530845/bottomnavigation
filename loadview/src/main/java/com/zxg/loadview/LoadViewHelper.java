package com.zxg.loadview;


import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * 加载界面切换的辅助类
 * 布局可自己修改如需增加控件，只需要在下列类中增加代码即可
 *
 * @author zxg
 */
public class LoadViewHelper {
    private VaryViewHelper helper;
    private View loadingLayout;
    private TextView loadingTextView;
    private ImageView imageView;
    private ProgressBar progressBar;
    private RelativeLayout relay;

    public boolean isEnable() {
        return helper.isEnable();
    }

    public void setEnable(boolean enable) {
        helper.setEnable(enable);
    }

    /**
     * 实例化
     * @param view 传入需要替代的布局
     */
    public LoadViewHelper(View view) {
        super();
        this.helper = new VaryViewHelper(view);
        loadingLayout = helper.inflate(R.layout.loadview_layout);
        loadingTextView = (TextView) loadingLayout.findViewById(R.id.tv_noresult);
        imageView = (ImageView) loadingLayout.findViewById(R.id.iv_noresult);
        progressBar = (ProgressBar) loadingLayout.findViewById(R.id.loading_progressbar_update);
        relay = (RelativeLayout) loadingLayout.findViewById(R.id.relay_noresult);
    }

    public View getLoadingLayout() {
        return loadingLayout;
    }

    /**
     * 文字形式加载中。。。
     */
    public void showLoading() {
        showLoading(helper.getContext().getResources().getString(R.string.loadview_loading));
    }

    public void showLoading(String text) {
        loadingTextView.setText(text);
        loadingTextView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        helper.showLayout(loadingLayout);
    }

    public void showLoading(int text) {
        loadingTextView.setText(text);
        loadingTextView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        helper.showLayout(loadingLayout);
    }

    /**
     * 转圈形式加载中
     */
    public void showLoadingProgressBar() {
//        TextView textView = (TextView) loadingLayout.findViewById(R.id.tv_noresult);
//        ImageView image = (ImageView) loadingLayout.findViewById(R.id.iv_noresult);
//        ProgressBar progress = (ProgressBar) loadingLayout.findViewById(R.id.loading_progressbar_update);
        loadingTextView.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        helper.showLayout(loadingLayout);
    }

    public void showFail(OnClickListener listener) {
        Context context = helper.getContext();
        String errorMsg = context.getString(R.string.loadview_bad_wangluo);
        showFail(errorMsg, listener);
    }
    public void showFail() {
        Context context = helper.getContext();
        String errorMsg = context.getString(R.string.loadview_bad_wangluo);
        showFail(errorMsg, null);
    }

    public void showFail(String errorMsg) {
        showFail(errorMsg, null);
    }

    public void showFail(int errorMsgId, OnClickListener listener) {
        Context context = helper.getContext();
        String errorMsg = context.getString(errorMsgId);
        showFail(errorMsg, listener);
    }

    public void showFail(String errorMsg, OnClickListener listener) {
//        View view = helper.inflate(R.layout.loadview_layout);
//        TextView textView = (TextView) view.findViewById(R.id.tv_noresult);
//        ImageView image = (ImageView) view.findViewById(R.id.iv_noresult);
//        ProgressBar progress = (ProgressBar) view.findViewById(R.id.loading_progressbar_update);
        if (null != listener) {
            relay.setOnClickListener(listener);
            imageView.setOnClickListener(listener);
            loadingTextView.setOnClickListener(listener);
        }
        loadingTextView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        loadingTextView.setText(errorMsg);
        helper.showLayout(loadingLayout);
    }

    public void showNoContent(String noContentMsg, OnClickListener listener) {
//        View view = helper.inflate(R.layout.loadview_layout);
//        TextView textView = (TextView) view.findViewById(R.id.tv_noresult);
//        ImageView image = (ImageView) view.findViewById(R.id.iv_noresult);
//        ProgressBar progress = (ProgressBar) view.findViewById(R.id.loading_progressbar_update);
        if (null != listener) {
            relay.setOnClickListener(listener);
            imageView.setOnClickListener(listener);
            loadingTextView.setOnClickListener(listener);
        }

        loadingTextView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        loadingTextView.setText(noContentMsg);
        helper.showLayout(loadingLayout);
    }

    public void showLayout(View view) {
        helper.showLayout(view);
    }

    public void restore() {
        helper.restoreView();
    }

}
