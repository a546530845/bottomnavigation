package com.zxg.bottomnavigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Author ：zxg on 2018/4/27 10:11
 * email : remotecountry@163.com
 * date : 2018/4/27
 */

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_home);
    }
    public void clickEvent(View view){
        switch (view.getId()){
            case R.id.google:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.diy:
                Intent intent2 = new Intent(this,MainActivity2.class);
                startActivity(intent2);
                break;
            case R.id.loadview:
                Intent intent3 = new Intent(this,LoadViewActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
