package com.thomas.base.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author Thomas
 * @describe 基础Fragment
 * @date 2019/9/24
 * @updatelog
 * @since 1.0.0
 */
public abstract class BaseBindingFragment<DB extends ViewDataBinding> extends BaseFragment  {
    protected DB dataBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mInflater = inflater;
        setContentView();
        dataBinding = DataBindingUtil.inflate(inflater, bindLayout(), container, false);
        return mContentView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dataBinding != null) {
            dataBinding.unbind();
        }
    }

}
