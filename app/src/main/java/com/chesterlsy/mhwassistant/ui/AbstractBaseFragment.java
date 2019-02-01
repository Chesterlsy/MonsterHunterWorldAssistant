package com.chesterlsy.mhwassistant.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chesterlsy.mhwassistant.R;
import com.chesterlsy.mhwassistant.presenter.AbstractBasePresenter;
import com.gc.materialdesign.widgets.ProgressDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * App: MonsterHunterWorldAssistant
 * Author: Siyi Liu
 * Created: 2019-01-31 15:05
 * Description:
 */
public abstract class AbstractBaseFragment<T extends AbstractBasePresenter>
        extends Fragment
        implements BaseView<T> {

    private Unbinder unbinder;
    protected View rootView;
    protected T presenter;
    protected ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(setLayout(), container, false);
        }
        rootView.setFocusable(true);
        rootView.setFocusableInTouchMode(true);
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return onBackPressedInFragment();
                } else {
                    return false;
                }
            }
        });
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = setPresenter();
        init();
    }

    protected abstract int setLayout();

    protected abstract void init();

    protected abstract boolean onBackPressedInFragment();

    protected void closeFragment() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    protected abstract AbstractBaseFragment newInstance(Object... objects);

//    public static AbstractBaseFragment newInstance(Object... objects) {
//        Bundle args = new Bundle();
//        args.putAll();
//        AbstractBaseFragment abstractBaseFragment = new AbstractBaseFragment();
//        abstractBaseFragment.setArguments(args);
//        return abstractBaseFragment;
//    }

    /**
     * Fragments have a different view lifecycle than activities.
     * When binding a fragment in onCreateView, set the views to null in onDestroyView.
     * Butter Knife returns an Unbinder instance when you call bind to do this for you.
     * Call its unbind method in the appropriate lifecycle callback.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext(), "Loading", R.color.colorPrimary);
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
