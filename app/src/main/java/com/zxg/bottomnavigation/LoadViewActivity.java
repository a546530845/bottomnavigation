package com.zxg.bottomnavigation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zxg.loadview.LoadViewHelper;

/**
 * Author ：zxg on 2018/4/19 14:48
 * email : remotecountry@163.com
 * date : 2018/4/19
 * 测试加载view
 */

public class LoadViewActivity extends Activity {
    private LinearLayout linearLayout;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    public LoadViewHelper helper;//加载显示图
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadview_aty);
        initView();
        initListener();
    }

    private void initListener() {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.showLoadingProgressBar();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.showFail( new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        //这里可以做你想要做的事情
                        helper.showLoadingProgressBar();
                    }

                });
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.restore();
            }
        });
    }

    /**
     * 实例化loadview
     * @param mView
     */
    public void instanceLoadingViewHelp(View mView) {
        helper = new LoadViewHelper(mView);
    }
    private void initView() {
        linearLayout = findViewById(R.id.linear1);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        instanceLoadingViewHelp(linearLayout);
    }

}
