package com.mxm.baseproject.subView;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mxm.baseproject.R;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tinkerpatch.sdk.TinkerPatch;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * 本地补丁
 */
public class TinkerPlateformActivity extends Activity {
    @BindView(R.id.tinkerform_btn)
    Button tinkerform_btn;

    @BindView(R.id.tinkerform_txt)
    TextView tinkerform_txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinkerpalteform);
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
                    Toast.makeText(TinkerPlateformActivity.this, "已获取权限" + permission.name, Toast.LENGTH_SHORT).show();
                } else if (permission.shouldShowRequestPermissionRationale) {
                    //拒绝权限请求
                    Toast.makeText(TinkerPlateformActivity.this, "已拒绝权限" + permission.name, Toast.LENGTH_SHORT).show();
                } else {
                    // 拒绝权限请求,并不再询问
                    // 可以提醒用户进入设置界面去设置权限
                    Toast.makeText(TinkerPlateformActivity.this, "已拒绝权限" + permission.name + "并不再询问", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @OnClick(R.id.tinkerform_btn)
    public void myclick1() {
        TinkerPatch.with().fetchPatchUpdate(true);
    }


}
