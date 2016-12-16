package com.buck.zhihuribao.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.fragment.MainFragment;
import com.buck.zhihuribao.fragment.OtherFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.container,MainFragment.newInstance());
        transaction.commit();
    }

    private void bindViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                mDrawerLayout.closeDrawer(GravityCompat.START);
                if (item.getItemId() == R.id.home_page) {
                    if (!item.isChecked()) {
                        manager.beginTransaction().replace(R.id.container, MainFragment.newInstance()).commit();
                    }
                } else {
                    if (!item.isChecked()) {
                        CharSequence id =  item.getTitleCondensed();
                        CharSequence title = item.getTitle();
                        manager.beginTransaction().replace(R.id.container, OtherFragment.newInstance((String) id, (String) title)).commit();
                    }
                }
                return true;
            }
        });
    }

    public void openDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }


}
