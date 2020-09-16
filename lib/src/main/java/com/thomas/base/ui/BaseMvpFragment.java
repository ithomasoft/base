package com.thomas.base.ui;

import com.thomas.base.mvp.BaseMvpPresenter;
import com.thomas.base.mvp.IBaseMvpView;
import com.thomas.base.ui.BaseFragment;

public abstract class BaseMvpFragment<P extends BaseMvpPresenter> extends BaseFragment
        implements IBaseMvpView {

    protected P presenter;


    @Override
    public void setContentView() {
        super.setContentView();
        //创建present
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除绑定，避免内存泄露
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }

    protected abstract P createPresenter();
}
