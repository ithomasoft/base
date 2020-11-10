package com.thomas.base.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thomas.base.ui.BaseActivity;
import com.thomas.core.ToastUtils;

public class MainActivity extends BaseActivity {
    private Button button, button2, button3;

    @Override
    protected boolean isNeedAdapt() {
        return false;
    }

    @Override
    protected int setAdaptScreen() {
        return 0;
    }

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public boolean isTransparent() {
        return false;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        applyThomasClickListener(button, button2, button3);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onThomasClick(@NonNull View view) {
        if (view == button) {
            ToastUtils.showShort("button");
            return;
        }
        if (view == button2) {
            ToastUtils.showShort("button2");
            return;
        }
        if (view == button3) {
            ToastUtils.showShort("button3");
            return;
        }
    }
}