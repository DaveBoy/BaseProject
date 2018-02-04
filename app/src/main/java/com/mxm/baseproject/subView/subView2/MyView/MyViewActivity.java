package com.mxm.baseproject.subView.subView2.MyView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mxm.baseproject.R;
import com.mxm.baseproject.adapter.MenuAdapter;
import com.mxm.baseproject.model.QMenu;
import com.mxm.baseproject.util.DefaultParameter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mao on 2017/6/21.
 */

public class MyViewActivity extends Activity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myview);
        ButterKnife.bind(this);
        List<QMenu> list = getMenuData();

        BaseQuickAdapter adapter = new MenuAdapter(R.layout.item_menu, list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MyViewActivity.this, MyViewActivity1_1.class));
                        break;
                    case 1:
                        startActivity(new Intent(MyViewActivity.this, MyViewActivity1_2.class));
                        break;
                }
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<QMenu> getMenuData() {
        List<QMenu> list = new ArrayList<>();
        list.add(new QMenu(DefaultParameter.MiniImgUrl1, "仿美团上方菜单", "使用GridView+viewPaper实现"));
        list.add(new QMenu(DefaultParameter.MiniImgUrl1, "点击切换数字，view随数字长度变换", "自定义View"));
        return list;
    }
}
