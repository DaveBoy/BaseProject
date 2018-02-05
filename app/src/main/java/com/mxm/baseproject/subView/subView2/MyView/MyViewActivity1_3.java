package com.mxm.baseproject.subView.subView2.MyView;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class MyViewActivity1_3 extends AppCompatActivity {
    public static String[] navSort = {"美食", "电影", "休闲娱乐", "丽人", "闪惠团购", "外卖", "酒店", "周边游",
            "足疗按摩", "KTV", "运动健身", "购物", "到家", "结婚", "亲自", "家装",
            "爱车", "优惠", "演出", "咖啡", "宠物", "景点", "医院", "全部分类",
            "足疗按摩", "KTV", "运动健身", "购物", "到家", "结婚"};
    public static int[] navSortImages = {R.drawable.default_img, R.drawable.default_img, R.drawable.default_img, R.drawable.default_img,
            R.drawable.default_img, R.drawable.default_img, R.drawable.default_img, R.drawable.default_img,
            R.drawable.default_img, R.drawable.default_img, R.drawable.default_img, R.drawable.default_img,
            R.drawable.default_img, R.drawable.default_img, R.drawable.default_img, R.drawable.default_img,
            R.drawable.default_img, R.drawable.default_img, R.drawable.default_img, R.drawable.default_img,
            R.drawable.default_img, R.drawable.default_img, R.drawable.default_img, R.drawable.default_img,
            R.drawable.default_img, R.drawable.default_img, R.drawable.default_img, R.drawable.default_img, R.drawable.default_img, R.drawable.default_img};
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private List<ImageView> imageViews;
    private int circleSize = 26;
    private List<GridView> gridViews;
    private Animator selected;
    private Animator unselected;
    private SparseBooleanArray isLarge;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myview1_3);
        ButterKnife.bind(this);
        initView();
        // 初始化Animator
        initAnimator();
        initDots();

    }

    /**
     * 根据引导页的数量，动态生成相应数量的导航小圆点，并添加到LinearLayout中显示。
     */
    private void initDots() {
        isLarge = new SparseBooleanArray();

        //动态设置布局
        LinearLayout layout = (LinearLayout) findViewById(R.id.dot_layout);
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(circleSize, circleSize);
        mParams.setMargins(10, 0, 10, 0);//设置小圆点左右之间的间隔

        imageViews = new ArrayList<ImageView>();          //初始化小圆点集合

        //为每个小圆点设置属性，selector，还有初始化小圆点
        for (int i = 0; i < gridViews.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(mParams);
            imageView.setImageResource(R.drawable.first_viewpager2_meituan_selector);

//            imageView.setImageResource(R.drawable.home_serve_dot);                //此set图片的方案，代码复用不好，性能略低
            imageView.setSelected(false);
            imageViews.add(imageView);//得到每个小圆点的引用，用于滑动页面时，（onPageSelected方法中）更改它们的状态。
            layout.addView(imageView);//添加到布局里面显示
            isLarge.put(i, false);          //一开始为每个圆点标记为false
        }

        //设置第一个圆点为选中样式
        imageViews.get(0).setSelected(true);
        selected.setTarget(imageViews.get(0));
        selected.start();
        isLarge.put(0, true);
    }

    private void initAnimator() {
        selected = AnimatorInflater.loadAnimator(this, R.animator.scale_to_large);
        unselected = AnimatorInflater.loadAnimator(this, R.animator.scale_to_small);
    }

    private void initView() {
        GridView g1 = (GridView) LayoutInflater.from(this).inflate(R.layout.item_gridview, null);
        g1.setAdapter(new GridAdapter(0, this));
        GridView g2 = (GridView) LayoutInflater.from(this).inflate(R.layout.item_gridview, null);
        g2.setAdapter(new GridAdapter(1, this));
        GridView g3 = (GridView) LayoutInflater.from(this).inflate(R.layout.item_gridview, null);
        g3.setAdapter(new GridAdapter(2, this));
        gridViews = new ArrayList<>();
        gridViews.add(g1);
        gridViews.add(g2);
        gridViews.add(g3);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return gridViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }


            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(gridViews.get(position));
                return gridViews.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(gridViews.get(position));
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Logger.i(position + 1 + "页");
                //((RadioButton)findViewById(rbt[position])).setChecked(true);
                for (int i = 0; i < imageViews.size(); i++) {
                    if (i == position) {//设置选中
                        if (!isLarge.get(i)) {
                            imageViews.get(i).setSelected(true);
                            selected.setTarget(imageViews.get(i));
                            selected.start();
                            isLarge.put(i, true);
                        } else {
                            //已经选中，不做操作
                        }
                    } else {//清理曾经的选
                        if (isLarge.get(i)) {
                            imageViews.get(i).setSelected(false);
                            unselected.setTarget(imageViews.get(i));
                            unselected.start();
                            isLarge.put(i, false);
                        } else {
                            //未选中，不做操作
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


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
                    Logger.i("onclick:" + ((TextView) arg0).getText());
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
