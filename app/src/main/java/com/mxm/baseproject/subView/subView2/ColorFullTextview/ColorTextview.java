package com.mxm.baseproject.subView.subView2.ColorFullTextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

/**
 * 彩色字体
 * 就是给textview加个渐变颜色而已
 */
@SuppressLint("AppCompatCustomView")
public class ColorTextview extends TextView {
    public ColorTextview(Context context, @Nullable AttributeSet attrs) {
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
}
