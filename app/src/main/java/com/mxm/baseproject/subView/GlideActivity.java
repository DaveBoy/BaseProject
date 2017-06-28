package com.mxm.baseproject.subView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mxm.baseproject.R;
import com.mxm.baseproject.util.DefaultParameter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class GlideActivity extends AppCompatActivity {
    @BindView(R.id.glide_img1)
    ImageView glide_img1;
    @BindView(R.id.glide_btn)
    Button glide_btn;
    @BindView(R.id.glide_blur_btn)
    Button glide_blur_btn;
    @BindView(R.id.glide_circle_btn)
    Button glide_circle_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);


    }


    @OnClick(R.id.glide_btn)
    public void showImg1(Button button) {
        Logger.i(button.getText().toString());
        Glide.with(this).load(DefaultParameter.verImgUrl1).into(glide_img1);
    }
    @OnClick(R.id.glide_blur_btn)
    public void showBlurImg(Button button) {
        Logger.i(button.getText().toString());
        Glide.with(this).load(DefaultParameter.verImgUrl1).bitmapTransform( new BlurTransformation(this,25,2)).override(600,600).into(glide_img1);
    }
    @OnClick(R.id.glide_circle_btn)
    public void showCircleImg(Button button) {
        Logger.i(button.getText().toString());
        Glide.with(this).load(DefaultParameter.verImgUrl1).override(600,600).bitmapTransform( new CropCircleTransformation(this)).into(glide_img1);
    }
}
