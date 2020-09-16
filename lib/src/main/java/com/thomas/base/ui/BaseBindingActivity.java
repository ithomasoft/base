package com.thomas.base.ui;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author Thomas
 * @describe 基础Activity
 * @date 2019/9/24
 * @updatelog
 * @since 1.0.0
 */
public abstract class BaseBindingActivity<DB extends ViewDataBinding> extends BaseActivity {
    protected DB dataBinding;

    @Override
    public void setContentView() {
        super.setContentView();
        dataBinding = DataBindingUtil.setContentView(mActivity, bindLayout());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataBinding != null) {
            dataBinding.unbind();
        }
    }
}
