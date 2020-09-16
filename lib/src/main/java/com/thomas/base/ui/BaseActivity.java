package com.thomas.base.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.thomas.core.AdaptScreenUtils;
import com.thomas.core.BarUtils;
import com.thomas.core.ClickUtils;
import com.thomas.core.ScreenUtils;

/**
 * @author Thomas
 * @describe 基础Activity
 * @date 2019/9/24
 * @updatelog
 * @since 1.0.0
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    protected View mContentView;
    protected AppCompatActivity mActivity;

    private View.OnClickListener mClickListener = v -> onThomasClick(v);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
        //默认实现沉浸式状态栏
        initStatusBar();
        initData(getIntent().getExtras());
        setContentView();
        initView(savedInstanceState, mContentView);
        doBusiness();
    }

    public void initStatusBar() {
        //默认强制竖屏
        ScreenUtils.setPortrait(this);
        if (BarUtils.isNavBarVisible(this)) {
            BarUtils.setNavBarVisibility(this, !isTransparent());
        }
        if (BarUtils.isSupportNavBar()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                BarUtils.setNavBarColor(this, ContextCompat.getColor(this, android.R.color.black));
            }
        }
    }

    /**
     * 是否进行屏幕适配
     *
     * @return true：进行；false：不进行
     */
    protected abstract boolean isNeedAdapt();

    /**
     * 设置设计图的宽度（pt）
     *
     * @return
     */
    protected abstract int setAdaptScreen();

    @Override
    public void setContentView() {
        if (bindLayout() <= 0) {
            return;
        }
        mContentView = LayoutInflater.from(this).inflate(bindLayout(), null);
        setContentView(mContentView);
    }


    public void applyThomasClickListener(View... views) {
        ClickUtils.applyGlobalDebouncing(views, mClickListener);

    }

    public void applyThomasClickScaleListener(View... views) {
        ClickUtils.applyGlobalDebouncing(views, mClickListener);
        ClickUtils.applyPressedViewScale(views);
    }

    @Override
    public Resources getResources() {
        if (isNeedAdapt()) {
            //今日头条屏幕适配方案
            if (ScreenUtils.isPortrait()) {
                //竖屏以宽度为基准
                return AdaptScreenUtils.adaptWidth(super.getResources(), setAdaptScreen());
            } else {
                //横屏以高度为基准
                return AdaptScreenUtils.adaptHeight(super.getResources(), setAdaptScreen());
            }

        } else {
            return super.getResources();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS
                );
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }
}
