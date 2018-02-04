package com.mxm.baseproject.subView.subView2.MVP;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mxm.baseproject.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/24.
 */

public class LoginActivity extends Activity implements ILoginView {

    @BindView(R.id.phone_ed)
    EditText phone_ed;
    @BindView(R.id.password_ed)
    EditText password_ed;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.clear)
    Button clear;
    LoginPresenter presenter;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.result_msg)
    TextView result_msg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
    }

    @Override
    public String getPhone() {
        return phone_ed.getText().toString();
    }

    @Override
    public String getPassword() {
        return password_ed.getText().toString();
    }

    @Override
    public void showDialog() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDialog() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void loginSuccess(User user) {
        Logger.i(user.toString());
        result_msg.setText("登录成功：" + user.toString());
        result_msg.setVisibility(View.VISIBLE);
    }

    @Override
    public void loginFailed(String msg) {
        Logger.e(msg);
        result_msg.setText("登录失败：" + msg);
        result_msg.setVisibility(View.VISIBLE);
    }

    @Override
    public void clear() {
        phone_ed.setText("");
        password_ed.setText("");
    }

    @OnClick({R.id.login, R.id.clear, R.id.progress})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                presenter.login();
                break;
            case R.id.clear:
                presenter.clear();
                break;
            case R.id.progress:
                break;
        }
    }
}
