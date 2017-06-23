package com.mxm.baseproject.subView.subView2;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RadioGroup;

import com.mxm.baseproject.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mao on 2017/6/21.
 */

public class MyViewActivity1_1 extends AppCompatActivity {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.rbg)
    RadioGroup rbg;
    List<GridView> list;
    int rbt[] = new int[]{R.id.rbt1, R.id.rbt2, R.id.rbt3};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_myview1_1);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        GridView g1 = (GridView) LayoutInflater.from(this).inflate(R.layout.item_gridview, null);
        GridView g2 = (GridView) LayoutInflater.from(this).inflate(R.layout.item_gridview, null);
        GridView g3 = (GridView) LayoutInflater.from(this).inflate(R.layout.item_gridview, null);
        list = new ArrayList<>();
        list.add(g1);
        list.add(g2);
        list.add(g3);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }


            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(list.get(position));
                return list.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(list.get(position));
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Logger.i(position + 1 + "页");
                findViewById(rbt[position]).setSelected(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
