package com.chesterlsy.mhwassistant.ui;

import com.chesterlsy.mhwassistant.presenter.BasePresenter;

/**
 * App: MonsterHunterWorldAssistant
 * Author: Siyi Liu
 * Created: 2019-01-30 17:12
 * Description:
 */
public interface BaseView<T extends BasePresenter> {
    T setPresenter();
    void showLoading();
    void dismissLoading();
    void showNoData();
    void showNoConnection();
}
