package com.zxg.bottomnavigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.zxg.bottomnavigation.adapter.BottomnavigationViewPagerAdapter;
import com.zxg.bottomnavigation.base.BaseFragmentActivity;
import com.zxg.bottomnavigation.fragment.Fragment1;
import com.zxg.bottomnavigation.fragment.Fragment2;
import com.zxg.bottomnavigation.fragment.Fragment3;

import java.util.ArrayList;
import java.util.List;

/***
 * 他人已经写好，测试google的bottomview
 */
public class MainActivity extends BaseFragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener,ViewPager.OnTouchListener{
    FrameLayout flFragment;
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    private Fragment fragment_now = null;

    BottomnavigationViewPagerAdapter pagerAdapter;
    List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initTitleView() {

    }

    @Override
    protected void initView(Bundle bundle) {
        viewPager = findViewById(R.id.viewpager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        inint();
    }
    @SuppressLint("NewApi")
    private void inint() {

        fragments = new ArrayList<>();
        fragment1 = Fragment1.newInstance();
        fragment2 = Fragment2.newInstance();
        fragment3 = Fragment3.newInstance();
        if(!fragments.contains(fragment1)){
            fragments.add(fragment1);
        }
        if(!fragments.contains(fragment2)){
            fragments.add(fragment2);
        }
        if(!fragments.contains(fragment3)){
            fragments.add(fragment3);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(this);//设置 NavigationItemSelected 事件监听
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);//改变 BottomNavigationView 默认的效果
        //选中第一个item,对应第一个fragment
        bottomNavigationView.setSelectedItemId(R.id.item_1);

        pagerAdapter = new BottomnavigationViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);

        // 如果想禁止滑动，可以把下面的代码取消注释
//        viewPager.setOnTouchListener(this);

    }
    //NavigationItemSelected 事件监听
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        changePageFragment(item.getItemId());
        switch (item.getItemId()){
            case  R.id.item_1:
                viewPager.setCurrentItem(0);
                break;
            case  R.id.item_2:
                viewPager.setCurrentItem(1);
                break;
            case  R.id.item_3:
                viewPager.setCurrentItem(3);
                break;
        }
        return true;
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.e("page选中=",position+"页");
        switch (position){
            case  0:
                bottomNavigationView.setSelectedItemId(R.id.item_1);
                break;
            case  1:
                bottomNavigationView.setSelectedItemId(R.id.item_2);
                break;
            case  2:
                bottomNavigationView.setSelectedItemId(R.id.item_3);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}
