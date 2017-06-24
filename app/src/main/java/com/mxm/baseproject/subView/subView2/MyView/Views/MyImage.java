package com.mxm.baseproject.subView.subView2.MyView.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mxm.baseproject.R;

/**
 * Created by Mao on 2017/6/24.
 */

public class MyImage extends View {
    private String my_text;
    private int text_color;
    private int text_size;

    private Paint paint;
    private Rect rect;

    public MyImage(Context context) {
        this(context, null);
    }

    public MyImage(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyImage, defStyleAttr, 0);


    }
}
