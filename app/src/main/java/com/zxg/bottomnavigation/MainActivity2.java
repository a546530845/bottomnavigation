package com.zxg.bottomnavigation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.zxg.bottomnavigation.base.BaseFragmentActivity;
import com.zxg.bottomnavigation.fragment.Fragment1;
import com.zxg.bottomnavigation.fragment.Fragment2;
import com.zxg.bottomnavigation.fragment.Fragment3;

/**
 * Author ：zxg on 2018/4/18 14:36
 * email : remotecountry@163.com
 * date : 2018/4/18
 * 自定义view，测试底部导航
 */

public class MainActivity2 extends BaseFragmentActivity {

    public static final String MainFgt = "MainFgt";
    public static final String MineFgt = "MineFgt";
    public static final String HistoryFgt = "HisToryFgt";
    private static FragmentManager fMgr;
    private Fragment1 mMainFragment;
    private Fragment2 mMineFragment;
    private Fragment3 mHistoryFragment;


    private BottomView bottomView1;
    private BottomView bottomView2;
    private BottomView bottomView3;
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRV_SELINDEX = "PREV_SELINDEX";
    private int selindex = 0;

    @Override
    protected void beforeInitView() {
    }

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.main_aty2);
    }

    @Override
    protected void initTitleView() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        bottomView1 = findViewById(R.id.bottom_1);
        bottomView2 = findViewById(R.id.bottom_2);
        bottomView3 = findViewById(R.id.bottom_3);



        fMgr = getSupportFragmentManager();
        if (savedInstanceState != null) {
            selindex = savedInstanceState.getInt(PRV_SELINDEX);
            mMainFragment = (Fragment1) fMgr.findFragmentByTag(MainFgt);
            mMineFragment = (Fragment2) fMgr.findFragmentByTag(MineFgt);
            mHistoryFragment = (Fragment3) fMgr.findFragmentByTag(HistoryFgt);

            FragmentTransaction ft = fMgr.beginTransaction();
            if (selindex == 0) {
                ft.hide(mMineFragment).hide(mHistoryFragment).show(mMainFragment).commit();
            } else if (selindex == 1) {
                ft.hide(mMainFragment).hide(mHistoryFragment).show(mMineFragment).commit();
            } else {
                ft.hide(mMainFragment).hide(mMineFragment).show(mHistoryFragment).commit();
            }
        } else {
            initFragment();
        }
    }

    /**
     * init a Fragment
     */
    private void initFragment() {
        FragmentTransaction ft = fMgr.beginTransaction();
        mMainFragment = new Fragment1();
        mMineFragment = new Fragment2();
        mHistoryFragment = new Fragment3();
        ft.add(R.id.fgt_room, mMineFragment, MineFgt);
        ft.add(R.id.fgt_room, mMainFragment, MainFgt);
        ft.add(R.id.fgt_room, mHistoryFragment, HistoryFgt);
        ft.hide(mMineFragment).hide(mHistoryFragment).show(mMainFragment).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //保存tab选中的状态
        outState.putInt(PRV_SELINDEX, selindex);
        super.onSaveInstanceState(outState);
    }

    public void onClickEvent(View view) {
        FragmentTransaction ft = fMgr.beginTransaction();
        switch (view.getId()) {
            case R.id.bottom_1:
                selindex = 0;
                changSelected(1);
                ft.hide(mMineFragment).hide(mHistoryFragment).show(mMainFragment).commit();
                //设置页面显示状态，便于使用setUserVisibleHint方法来判断是不是处于当前显示的状态
                mMainFragment.setUserVisibleHint(true);
                mMineFragment.setUserVisibleHint(false);
                mHistoryFragment.setUserVisibleHint(false);
//                setStatusbar();
                break;
            case R.id.bottom_2:
                selindex = 1;
                changSelected(2);
                ft.hide(mMainFragment).hide(mHistoryFragment).show(mMineFragment).commit();
                mMineFragment.setUserVisibleHint(true);
                mMainFragment.setUserVisibleHint(false);
                mHistoryFragment.setUserVisibleHint(false);
//                setStatusbar_red();
                break;
            case R.id.bottom_3:
                selindex = 2;
                changSelected(3);
                ft.hide(mMineFragment).hide(mMainFragment).show(mHistoryFragment).commit();
                mHistoryFragment.setUserVisibleHint(true);
                mMineFragment.setUserVisibleHint(false);
                mMainFragment.setUserVisibleHint(false);
//                setStatusbar_red();
                break;
        }
    }

    /**
     * select fragment in mainfragment and minefragment ,change statu of view
     *
     * @param select 1.Main 2.mine 3.HIstory
     */
    public void changSelected(int select) {
        switch (select) {
            case 1:
                bottomView1.changeCheckStatus(true);
                bottomView2.changeCheckStatus(false);
                bottomView3.changeCheckStatus(false);



                break;
            case 2:
                bottomView1.changeCheckStatus(false);
                bottomView2.changeCheckStatus(true);
                bottomView3.changeCheckStatus(false);
                break;
            case 3:

                bottomView1.changeCheckStatus(false);
                bottomView2.changeCheckStatus(false);
                bottomView3.changeCheckStatus(true);

                break;
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Log.e("实例化=","ddd");
//        changSelected(1);
    }
}
