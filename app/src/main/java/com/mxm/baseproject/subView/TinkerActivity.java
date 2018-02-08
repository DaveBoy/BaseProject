package com.mxm.baseproject.subView;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import com.mxm.baseproject.R;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * 本地补丁
 * https://www.jianshu.com/p/9680a58e67fe
 */
public class TinkerActivity extends Activity {
    @BindView(R.id.tinker_btn)
    Button tinker_btn;

    @BindView(R.id.tinker_btn1)
    Button tinker_btn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinker);
        ButterKnife.bind(this);
        askPermissions();
    }

    private void askPermissions() {
        String permissions = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(@NonNull Permission permission) throws Exception {
                if (permission.granted) {
                    Toast.makeText(TinkerActivity.this, "已获取权限" + permission.name, Toast.LENGTH_SHORT).show();
                } else if (permission.shouldShowRequestPermissionRationale) {
                    //拒绝权限请求
                    Toast.makeText(TinkerActivity.this, "已拒绝权限" + permission.name, Toast.LENGTH_SHORT).show();
                } else {
                    // 拒绝权限请求,并不再询问
                    // 可以提醒用户进入设置界面去设置权限
                    Toast.makeText(TinkerActivity.this, "已拒绝权限" + permission.name + "并不再询问", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.tinker_btn1)
    public void myclick() {
        Logger.i("你点我干啥");
    }

    @OnClick(R.id.tinker_btn)
    public void myclick1() {
        TinkerInstaller.onReceiveUpgradePatch(this, Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch");
        Logger.i("我错了我不该点你");
    }
}
