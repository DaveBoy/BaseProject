package com.mxm.baseproject.subView.subView2.MyView;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
    public static String[] navSort={"美食","电影","休闲娱乐","丽人","闪惠团购","外卖","酒店","周边游",
            "足疗按摩","KTV","运动健身","购物","到家","结婚","亲自","家装",
            "爱车","优惠","演出","咖啡","宠物","景点","医院","全部分类",
            "足疗按摩","KTV","运动健身","购物","到家","结婚"};

    public static int[] navSortImages={R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,
            R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,
            R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,
            R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,
            R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,
            R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,
            R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,R.drawable.default_img,R.drawable.default_img};
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myview1_1);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        GridView g1 = (GridView) LayoutInflater.from(this).inflate(R.layout.item_gridview, null);
        g1.setAdapter(new GridAdapter(0,this));
        GridView g2 = (GridView) LayoutInflater.from(this).inflate(R.layout.item_gridview, null);
        g2.setAdapter(new GridAdapter(1,this));
        GridView g3 = (GridView) LayoutInflater.from(this).inflate(R.layout.item_gridview, null);
        g3.setAdapter(new GridAdapter(2,this));
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
                ((RadioButton)findViewById(rbt[position])).setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rbg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int i=0;
                for (int id:rbt) {
                    if(id==checkedId)
                        viewPager.setCurrentItem(i);
                    else
                        i++;
                }

            }
        });
        ((RadioButton)findViewById(rbt[0])).setChecked(true);

    }
    class GridAdapter extends BaseAdapter {
        private int page;
        private LayoutInflater inflater;

        public GridAdapter(int page, Context context) {
            this.page = page;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {

            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup arg2) {

            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.activity_viewpager_gridview_item, null);
                viewHolder.iv_navsort = (ImageView) convertView.findViewById(R.id.index_home_iv_navsort);
                viewHolder.tv_navsort = (TextView) convertView.findViewById(R.id.index_home_tv_navsort);
                ButterKnife.bind(viewHolder, convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.iv_navsort.setImageResource(navSortImages[position + 10 * page]);
            viewHolder.tv_navsort.setText(navSort[position + 10 * page]);

            //在这里处理gridview每个icon的逻辑，跳转activity

                viewHolder.tv_navsort.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        Logger.i("onclick:"+((TextView)arg0).getText());
                    }
                });



            return convertView;
        }

        //gridview 适配器的holder类
        class ViewHolder {
            public ImageView iv_navsort;
            public TextView tv_navsort;
        }
    }
}
