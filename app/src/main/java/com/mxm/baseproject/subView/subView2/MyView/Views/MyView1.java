package com.mxm.baseproject.subView.subView2.MyView.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mxm.baseproject.R;
import com.orhanobut.logger.Logger;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Mao on 2017/6/24.
 * 点击切换数字，view随数字长度变换
 */

public class MyView1 extends View {
    private String my_text;
    private int text_color;
    private int text_size;

    private Paint paint;
    private Rect rect;


    public MyView1(Context context) {
        this(context, null);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Logger.i("MyView1");
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView1, defStyleAttr, 0);
        my_text = a.getString(R.styleable.MyView1_my_text);
        text_color = a.getInteger(R.styleable.MyView1_my_textColor, Color.GRAY);
        text_size = a.getDimensionPixelSize(R.styleable.MyView1_my_textSize, 16);
        a.recycle();
        paint = new Paint();
        paint.setTextSize(text_size);
        rect = new Rect();
        paint.getTextBounds(my_text, 0, my_text.length(), rect);
        this.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                my_text = randomText();

                requestLayout();
                //postInvalidate();
            }

        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Logger.i("onMeasure");

        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int width;
        int height;

        rect = new Rect();
        paint.getTextBounds(my_text, 0, my_text.length(), rect);
        if (widthModel == MeasureSpec.EXACTLY)
            width = widthSize;
        else {
            int text_width = rect.width();
            int w = (int) (getPaddingLeft() + text_width + getPaddingRight());
            width = w;
        }
        if (heightModel == MeasureSpec.EXACTLY)
            height = heightSize;
        else {
            int text_height = rect.height();
            int h = (int) (getPaddingTop() + text_height + getPaddingBottom());
            height = h;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Logger.i("onDraw");
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        paint.setColor(text_color);
        canvas.drawText(my_text, getWidth() / 2 - rect.width() / 2 - getPaddingLeft(), rect.height() / 2 + getHeight() / 2 - getPaddingTop(), paint);

    }

    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        int length = random.nextInt(10);
        while (set.size() < length) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }

        return sb.toString();
    }
}
