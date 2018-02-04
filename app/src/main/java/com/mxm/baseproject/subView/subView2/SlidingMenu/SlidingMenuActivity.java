package com.mxm.baseproject.subView.subView2.SlidingMenu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mxm.baseproject.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlidingMenuActivity extends ActionBarActivity {
    @BindView(R.id.nav_view)
    NavigationView nav_view;
    @BindView(R.id.mytoolbar)
    Toolbar mytoolbar;
    @BindView(R.id.mydrawerlayout)
    DrawerLayout mydrawerlayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingmenu);
        ButterKnife.bind(this);

        setTitle("");
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle
                (this, mydrawerlayout, mytoolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mydrawerlayout.setDrawerListener(mDrawerToggle);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Logger.i(item.getTitle().toString());
                mydrawerlayout.closeDrawers();//s表示关闭所有的
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mydrawerlayout.isDrawerOpen(nav_view)) {
            mydrawerlayout.closeDrawers();
        } else
            super.onBackPressed();
    }

}
