package com.chesterlsy.mhwassistant.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chesterlsy.mhwassistant.R;
import com.chesterlsy.mhwassistant.presenter.AbstractBasePresenter;
import com.chesterlsy.mhwassistant.ui.AbstractBaseFragment;
import com.chesterlsy.mhwassistant.ui.BaseView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainHomeFragment<T extends AbstractBasePresenter>
        extends AbstractBaseFragment<T>
        implements BaseView<T> {
    @BindView(R.id.section_label)
    TextView section_label;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Should always be 1;
     */
    private int sectionNumber;

    public MainHomeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MainHomeFragment newInstance(int sectionNumber) {
        MainHomeFragment fragment = new MainHomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_main_home;
    }

    @Override
    protected void init() {
        if (getArguments() != null) {
            sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

        }
        section_label.setText(getString(R.string.section_format, sectionNumber));
    }

    @Override
    protected boolean onBackPressedInFragment() {
        return false;
    }

    @Override
    protected AbstractBaseFragment newInstance(Object... objects) {
        return null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putString("data", data);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        data = savedInstanceState.getString("data");
    }

    @Override
    public T setPresenter() {
        return null;
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showNoConnection() {

    }
}
