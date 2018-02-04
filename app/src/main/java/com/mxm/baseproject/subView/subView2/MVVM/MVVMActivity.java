package com.mxm.baseproject.subView.subView2.MVVM;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mxm.baseproject.R;
import com.mxm.baseproject.databinding.ActivityMvvmBinding;

/**
 * Created by Administrator on 2017/6/24.
 */

public class MVVMActivity extends Activity implements ILoginView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        LoginViewModel login = new LoginViewModel(this);

        binding.setLoginViewModel(login);

    }
}
