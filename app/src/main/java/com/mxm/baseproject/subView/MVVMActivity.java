package com.mxm.baseproject.subView;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mxm.baseproject.R;
import com.mxm.baseproject.databinding.ActivityMvvmBinding;
import com.mxm.baseproject.subView.subView2.MVVM.LoginViewModel;

/**
 * Created by Administrator on 2017/6/24.
 */

public class MVVMActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        LoginViewModel login = new LoginViewModel(this, binding);


    }
}
