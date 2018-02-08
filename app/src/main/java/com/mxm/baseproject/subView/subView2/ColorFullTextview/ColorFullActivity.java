package com.mxm.baseproject.subView.subView2.ColorFullTextview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.mxm.baseproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorFullActivity extends Activity {
    @BindView(R.id.color_view)
    TextView color_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorfull);
        ButterKnife.bind(this);
        String content = color_view.getText().toString();
        color_view.setText(SpannableStringFont.changeFont(this, content));
        color_view.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
