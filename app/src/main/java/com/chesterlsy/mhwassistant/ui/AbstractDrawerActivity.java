package com.chesterlsy.mhwassistant.ui;

import com.chesterlsy.mhwassistant.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public abstract class AbstractDrawerActivity
        extends AbstractBaseActivity
        implements BaseView, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.abstract_drawer_drawer_layout)
    DrawerLayout abstract_drawer_drawer_layout;
    @BindView(R.id.abstract_drawer_nav_view)
    NavigationView abstract_drawer_nav_view;
    @BindView(R.id.abstract_drawer_toolbar)
    Toolbar abstract_drawer_toolbar;
    @BindView(R.id.abstract_drawer_fab)
    FloatingActionButton abstract_drawer_fab;
//    @BindView(R.id.abstract_drawer_imageView)
//    ImageView abstract_drawer_imageView;
    @BindView(R.id.abstract_drawer_content_container)
    FrameLayout abstract_drawer_frameLayout;

    protected DrawerLayout drawerLayout;
    protected NavigationView drawerNavigationView;
    protected Toolbar toolbar;
    protected FrameLayout contentContainer;
    protected TabLayout toolbarTab;
//    protected FloatingActionButton fab;

    /**
     * setBaseLayout => setContentView(setBaseLayout())
     * => setAppbarLayout => addAppbarView(setAppbarLayout()) => init drawer and toolbar
     * => setToolbarTab => setContentContainer
     * => tabVisible(*) = > setTabVisible(tabVisible())
     * => setAppbarContentLayout(*) => addAppbarContentView(setAppbarContentLayout())
     * => ButterKnife.bind
     */
    @Override
    protected void init() {
        addAppbarView(setAppbarLayout());
        drawerLayout = (DrawerLayout) findViewById(R.id.abstract_drawer_drawer_layout);
        drawerNavigationView = (NavigationView) findViewById(R.id.abstract_drawer_nav_view);


        drawerNavigationView.setNavigationItemSelectedListener(this);

        toolbar = findViewById(R.id.abstract_drawer_toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        fab = findViewById(R.id.abstract_drawer_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        toolbarTab = findViewById(setToolbarTab());
        contentContainer = findViewById(setContentContainer());
        setTabVisible(tabVisible());
        addAppbarContentView(setAppbarContentLayout());
        ButterKnife.bind(this);
    }

    @Override
    protected int setBaseLayout() {
        return R.layout.activity_abstract_drawer;
    }

    protected int setAppbarLayout() {
        return R.layout.appbar_abstract_drawer;
    }

    private void addAppbarView(int layoutId) {
        View contentView = LayoutInflater.from(this).inflate(layoutId, null);
        FrameLayout appbarLayoutContainer = findViewById(R.id.abstract_drawer_appbar_layout_container);
        appbarLayoutContainer.removeAllViews();
        appbarLayoutContainer.addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    protected int setToolbarTab() {
        return R.id.abstract_drawer_tabs;
    }

    protected int setContentContainer() {
        return R.id.abstract_drawer_content_container;
    }

    protected abstract boolean tabVisible();

    private void setTabVisible(boolean isTabVisible) {
        if (toolbarTab != null) {
            if (isTabVisible) {
                toolbarTab.setVisibility(View.VISIBLE);
            } else {
                toolbarTab.setVisibility(View.GONE);
            }
        }
    }

    protected abstract int setAppbarContentLayout();

    private void addAppbarContentView(int layoutId) {
        View contentView = LayoutInflater.from(this).inflate(layoutId, null);
        contentContainer.removeAllViews();
        contentContainer.addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.abstract_drawer_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_abstract_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle toolbar_main view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.abstract_drawer_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showNoConnection() {

    }
}
