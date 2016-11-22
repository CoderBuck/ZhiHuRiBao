package com.buck.zhihuribao.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.adapter.HomeRecyclerAdapter;
import com.buck.zhihuribao.data.bean.HomePageBean;
import com.buck.zhihuribao.network.ApiStoresGenerator;
import com.buck.zhihuribao.network.service.ApiStores;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity" ;
    private DrawerLayout mDrawerLayout;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private NavigationView mNavigationView;
    private BGABanner mBanner;

    private HomeRecyclerAdapter mHomeRecyclerAdapter;
    private List<HomePageBean> mList = new ArrayList<>();

    private ApiStores api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = ApiStoresGenerator.getInstance();

        setStatusBarTranslucent();
        bindViews();
        loadData();

    }

    private void setStatusBarTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //  设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
            ViewCompat.setFitsSystemWindows(rootView,false);
            rootView.setClipToPadding(true);
        }
    }

    private void bindViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mBanner = (BGABanner) findViewById(R.id.banner);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                Glide.with(banner.getContext())
                        .load(model)
                        .centerCrop()
                        .crossFade()
                        .into((ImageView)view);
            }
        });


    }

    private void loadData() {
        Call<HomePageBean> call = api.getHomePage();
        call.enqueue(new Callback<HomePageBean>() {
            @Override
            public void onResponse(Call<HomePageBean> call, Response<HomePageBean> response) {
                final HomePageBean bean = response.body();
                Log.d(TAG, "onResponse: "+bean.getTop_stories().size());
                List<String> imgs = new ArrayList<String>();
                List<String> tips = new ArrayList<String>();
                for (int i = 0; i < bean.getTop_stories().size(); i++) {
                    imgs.add(bean.getTop_stories().get(i).getImage());
                    tips.add(bean.getTop_stories().get(i).getTitle());
                }
                mBanner.setData(imgs,tips);

                mBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
                    @Override
                    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
                        int newsId = bean.getTop_stories().get(position).getId();
                        NewsActivity.start(MainActivity.this,newsId);
                    }
                });

                mHomeRecyclerAdapter = new HomeRecyclerAdapter(bean,MainActivity.this);
                mRecyclerView.setAdapter(mHomeRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<HomePageBean> call, Throwable t) {
                Toast.makeText(MainActivity.this, "获取图片出错...", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
