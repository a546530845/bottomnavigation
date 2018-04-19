package com.zxg.bottomnavigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Author ：zxg on 2018/4/18 14:42
 * email : remotecountry@163.com
 * date : 2018/4/18
 */

/**
 * 必须设置图片和文字颜色
 */
public class BottomView extends LinearLayout {
    /**
     * 选中状态默认为false
     */
    private boolean check  = false;
    private Drawable drawable_selected;
    private Drawable drawable_normal;
    private int bottom_text_color_selected;
    private int bottom_text_color_normal;
    private String text_content = "幸福";
    private View lineView;
    private ImageView imageView;
    private TextView textView;
    /**
     * 默认title的文字颜色,单位sp
     */
    private final int default_title_text_color = Color.parseColor("#88000000");

    public BottomView(Context context) {
        this(context, null);
    }

    public BottomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initByAttributes(context,attrs,defStyleAttr);
    }

    /**
     * 实例化传入数据，和设置数据
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void initByAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        //获取xml中传入的数据
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BottomView, defStyleAttr, 0);
        check = attributes.getBoolean(R.styleable.BottomView_bottomview_check,false);
        drawable_normal = attributes.getDrawable(R.styleable.BottomView_bottomview_image_src_normal);
        drawable_selected = attributes.getDrawable(R.styleable.BottomView_bottomview_image_src_selected);
        bottom_text_color_normal = attributes.getColor(R.styleable.BottomView_bottomview_text_color_normal, default_title_text_color);
        bottom_text_color_selected = attributes.getColor(R.styleable.BottomView_bottomview_text_color_selected, default_title_text_color);
        text_content = attributes.getString(R.styleable.BottomView_bottomview_text_content);
        attributes.recycle();

        //设置布局和布局view的数据
        lineView = View.inflate(context, R.layout.bottom_view, null);
        this.addView(lineView);
        this.setGravity(Gravity.CENTER);//设置居中
        imageView = lineView.findViewById(R.id.bottom_iv);
        textView = lineView.findViewById(R.id.bottom_tv);

        changeCheckStatus(check);

    }
    /**
     * 设置选中状态
     * @param checkStatus
     */
    public void changeCheckStatus(boolean checkStatus){
        this.check = checkStatus;
        if(!check){
            if (null != drawable_normal) {
                imageView.setImageDrawable(drawable_normal);
            }
            if (null != text_content) {
                textView.setText(text_content);
            }
            textView.setTextColor(bottom_text_color_normal);
        }else {
            if (null != drawable_selected) {
                imageView.setImageDrawable(drawable_selected);
            }
            if (null != text_content) {
                textView.setText(text_content);
            }
            textView.setTextColor(bottom_text_color_selected);
        }
    }
//    private void setImageSrc(int resourceSrc) {
//        imageView.setImageResource(resourceSrc);
//    }
//
//    private void setTextColor(int resourceColor) {
//        textView.setTextColor(resourceColor);
//    }
//
//    private void setText_content(String text_content) {
//        textView.setText(text_content);
//    }
}
