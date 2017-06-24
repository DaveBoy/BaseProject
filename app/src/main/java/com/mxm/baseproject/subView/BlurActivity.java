package com.mxm.baseproject.subView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mxm.baseproject.R;
import com.mxm.baseproject.util.DefaultParameter;
import com.mxm.baseproject.util.MUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/23.
 */

public class BlurActivity extends AppCompatActivity{
    @BindView(R.id.blur_re)RelativeLayout blur_re;
    @BindView(R.id.img)ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_blur);
        ButterKnife.bind(this);
        Glide.with(this).load(DefaultParameter.verImgUrl1).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Logger.i("onLoadFailed");
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Logger.i("onResourceReady");
                BitmapDrawable bd = (BitmapDrawable) resource;
                blur_re.setBackground(new BitmapDrawable(MUtil.blurBitmap(BlurActivity.this,bd.getBitmap(),2)));
                return false;
            }
        }).into(imageView);

        applyBlur();
    }
    /**
     * 添加模糊效果
     */
    private void applyBlur() {
        imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                blur_re.getViewTreeObserver().removeOnPreDrawListener(this);
                blur_re.buildDrawingCache();
                Bitmap bmp = blur_re.getDrawingCache();
                blur(bmp, blur_re);
                return true;
            }
        });
    }
    /**
     * 一个高斯模糊的算法
     *
     * @param bkg
     * @param view
     */
    private void blur(Bitmap bkg, View view) {
        float radius = 20;
        Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth()),
                (int) (view.getMeasuredHeight()), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft(), -view.getTop());
        canvas.drawBitmap(bkg, 0, 0, null);

        RenderScript rs = RenderScript.create(BlurActivity.this);

        Allocation overlayAlloc = Allocation.createFromBitmap(rs, overlay);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, overlayAlloc.getElement());
        blur.setInput(overlayAlloc);
        blur.setRadius(radius);
        blur.forEach(overlayAlloc);
        overlayAlloc.copyTo(overlay);
        view.setBackground(new BitmapDrawable(getResources(), overlay));
        rs.destroy();

    }
}
