package com.mxm.baseproject.MyView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.mxm.baseproject.R;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/4/15.
 */
public class ClickEffectView extends View {
Button b;
    private ColorStateList mTextColorList = ColorStateList.valueOf(Color.GRAY);
    private String mContextText;
    private Drawable mBgDrawable;

    private int mCurTextColor;
    private Paint mTextPaint;
    private Rect mRect;

    public ClickEffectView(Context context) {
        this(context,null);
    }

    public ClickEffectView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClickEffectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /*绑定StyleAble自定义属性，使ClickEffectView支持XML赋值属性值*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClickEffectView);
        ColorStateList color = null;
        color = typedArray.getColorStateList(R.styleable.ClickEffectView_cev_TextColor);
        if (color != null) {
            setTextColor(color);
        }
        String text = typedArray.getString(R.styleable.ClickEffectView_cev_Text);
        setText(text);
        mBgDrawable = typedArray.getDrawable(R.styleable.ClickEffectView_cev_Background);
        typedArray.recycle();

        mTextPaint = createPaint();
        mTextPaint.setTextSize(30);
        mRect = new Rect();
    }

    protected Paint createPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        paint.setXfermode(null);
        return paint;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Logger.i("drawableStateChanged");
        updateState();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if(mBgDrawable != null){
            mBgDrawable.setBounds(0,0,width,height);
            mBgDrawable.draw(canvas);
        }

        //在绘制之前，重新设置颜色，以保证是根据getDrawableState()得到的值
        mTextPaint.setColor(mCurTextColor);

        mRect.set(0 , 0, width, height);
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt() ;
        int baseline = (mRect. bottom + mRect.top - fontMetrics.bottom - fontMetrics.top ) / 2;
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
        mTextPaint.setTextAlign(Paint.Align. CENTER);
        canvas.drawText(TextUtils. isEmpty( mContextText ) ? "" : mContextText ,
                mRect.centerX() , baseline, mTextPaint);

    }

    public void setTextColor(ColorStateList textColor) {
        this.mTextColorList = textColor;
        updateState();
    }

    public void setText(String text) {
        if(TextUtils.isEmpty(text)){
            this.mContextText = "";
        }
        this.mContextText = text;
    }

    private void updateState() {
        boolean invalidate = false;
        //获取View当前状态
        int[] states = getDrawableState();
        for (int i:states
             ) {
            Logger.i(i+"");
        }

        //获取状态对应的颜色
        int textColor = mTextColorList.getColorForState(states, 0);
        if(textColor != mCurTextColor){
            mCurTextColor = textColor;
            invalidate = true;
        }
        if(mBgDrawable != null){
            mBgDrawable.setState(states);
            invalidate = true;
        }
        if(invalidate){
            invalidate();
        }
    }
}