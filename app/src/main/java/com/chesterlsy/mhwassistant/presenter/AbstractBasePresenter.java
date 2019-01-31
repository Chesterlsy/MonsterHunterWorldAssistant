package com.chesterlsy.mhwassistant.presenter;

import com.chesterlsy.mhwassistant.ui.BaseView;

import androidx.annotation.NonNull;

/**
 * App: MonsterHunterWorldAssistant
 * Author: Siyi Liu
 * Created: 2019-01-31 14:24
 * Description:
 */
public abstract class AbstractBasePresenter implements BasePresenter {
    protected BaseView<BasePresenter> view;

    public AbstractBasePresenter(@NonNull BaseView<BasePresenter> view) {
        this.view = view;
    }

    public void destroy() {
        view = null;
    }
}
