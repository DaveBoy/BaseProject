package com.mxm.baseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mxm.baseproject.adapter.MenuAdapter;
import com.mxm.baseproject.model.QMenu;
import com.mxm.baseproject.subView.GlideActivity;
import com.mxm.baseproject.subView.MyViewActivity;
import com.mxm.baseproject.subView.PhotoViewActivity;
import com.mxm.baseproject.util.DefaultParameter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<QMenu> list = getMenuData();
        MenuAdapter menuAdapter = new MenuAdapter(R.layout.item_menu, list);
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Logger.i("onItemClick:" + position);
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, GlideActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, PhotoViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, MyViewActivity.class));
                        break;
                }
            }
        });
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(menuAdapter);

    }

    private List<QMenu> getMenuData() {
        List<QMenu> list = new ArrayList<>();
        list.add(new QMenu(DefaultParameter.MiniImgUrl1, "Glide图片加载", "使用Glide进行图片加载缓存"));
        list.add(new QMenu(DefaultParameter.MiniImgUrl1, "PhotoView", "点击放大"));
        list.add(new QMenu(DefaultParameter.MiniImgUrl1, "自定义View", "自定义View的初步实现"));



        list.add(new QMenu(DefaultParameter.MiniImgUrl1, "欢迎页", "实现常用欢迎页"));


        return list;
    }
}
