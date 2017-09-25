package com.szmz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActBase {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_search:

                    return true;
                case R.id.navigation_job:

                    return true;
                case R.id.navigation_tj:

                    return true;
                case R.id.navigation_user:

                    return true;
            }
            return false;
        }

    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
