package com.mxm.baseproject.subView.subView2.ColorFullTextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

/**
 * 霓虹灯字体
 * 在彩色字体上加了一个位移变换而已
 */
@SuppressLint("AppCompatCustomView")
public class LedTextview extends TextView {
    private int transX = 0;

    public LedTextview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Logger.i("onSizeChanged");
        if (getMeasuredWidth() > 0) {
            LinearGradient linearGradient = new LinearGradient(0, 0, getMeasuredWidth(), 0, new int[]{Color.BLUE, Color.YELLOW, Color.RED, Color.GREEN, Color.GRAY}, null, LinearGradient.TileMode.CLAMP);
            getPaint().setShader(linearGradient);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        transX += getWidth() / 10;
        if (transX > getWidth())
            transX = -getWidth();
        Matrix matrix = new Matrix();
        matrix.setTranslate(transX, 0);
        getPaint().getShader().setLocalMatrix(matrix);
        postInvalidateDelayed(100);
    }
}
