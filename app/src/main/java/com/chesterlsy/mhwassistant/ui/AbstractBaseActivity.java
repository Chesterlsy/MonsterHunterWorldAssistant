package com.chesterlsy.mhwassistant.ui;

import android.os.Bundle;

import com.chesterlsy.mhwassistant.R;
import com.chesterlsy.mhwassistant.presenter.AbstractBasePresenter;
import com.gc.materialdesign.widgets.ProgressDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * App: MonsterHunterWorldAssistant
 * Author: Siyi Liu
 * Created: 2019-01-31 14:23
 * Description:
 */
public abstract class AbstractBaseActivity<T extends AbstractBasePresenter>
        extends AppCompatActivity
        implements BaseView<T> {

    protected T presenter;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setBaseLayout());
        presenter = setPresenter();
        init();
    }

    protected abstract int setBaseLayout();

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.destroy();
            presenter = null;
        }
        if (progressDialog != null) {
            progressDialog = null;
        }
    }

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this, "Loading", R.color.colorPrimary);
        }
        progressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
