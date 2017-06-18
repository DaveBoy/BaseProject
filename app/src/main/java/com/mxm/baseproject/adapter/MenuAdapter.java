package com.mxm.baseproject.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mxm.baseproject.model.QMenu;

import java.util.List;

/**
 * Created by Mao on 2017/6/18.
 */

public class MenuAdapter extends BaseQuickAdapter<QMenu, BaseViewHolder> {
    public MenuAdapter(@LayoutRes int layoutResId, @Nullable List<QMenu> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QMenu item) {

    }


}
