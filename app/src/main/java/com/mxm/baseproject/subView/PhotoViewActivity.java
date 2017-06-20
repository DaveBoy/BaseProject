package com.mxm.baseproject.subView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mxm.baseproject.R;
import com.mxm.baseproject.util.DefaultParameter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mxm.baseproject.R.id.glide_img2;

public class PhotoViewActivity extends AppCompatActivity {

    @BindView(glide_img2)
    ImageView photoViewl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);
        ButterKnife.bind(this);

        Glide.with(this).load(DefaultParameter.verImgUrl1).into(photoViewl);
        //必须添加一个onExitListener,在拖拽到底部时触发.
        photoViewl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoViewActivity.this, DragPhotoActivity.class);
                int location[] = new int[2];

                v.getLocationOnScreen(location);
                intent.putExtra("left", location[0]);
                intent.putExtra("top", location[1]);
                intent.putExtra("height", v.getHeight());
                intent.putExtra("width", v.getWidth());

                PhotoViewActivity.this.startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }




}
