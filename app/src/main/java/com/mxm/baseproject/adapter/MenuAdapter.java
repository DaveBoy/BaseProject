package com.mxm.baseproject.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mxm.baseproject.R;
import com.mxm.baseproject.model.QMenu;
import com.mxm.baseproject.util.MyApplicationLike;

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
        helper.setText(R.id.menu_title, item.getName());
        helper.setText(R.id.menu_detail, item.getDetail());
        if (item.getIcon() != null)
            Glide.with(MyApplicationLike.getInstance()).load(item.getIcon()).into((ImageView) helper.getView(R.id.menu_icon));
    }


}
