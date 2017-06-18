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

public class GlideActivity extends AppCompatActivity {
    @BindView(R.id.glide_img1)
    ImageView glide_img1;
    @BindView(R.id.glide_btn)
    Button glide_btn;

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

}
