package com.buck.zhihuribao.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.adapter.HomeRecyclerAdapter;
import com.buck.zhihuribao.data.bean.FooterBean;
import com.buck.zhihuribao.data.bean.OldNewsBean;
import com.buck.zhihuribao.data.bean.TodayNewsBean;
import com.buck.zhihuribao.data.bean.StoriesBean;
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

    private static final String TAG = MainActivity.class.getSimpleName();
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private NavigationView mNavigationView;
    private BGABanner mBanner;

    private ApiStores api;
    private String date;

    private List<Object> items;
    private HomeRecyclerAdapter mAdapter;
    private int appbarHeght;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = ApiStoresGenerator.getInstance();
        appbarHeght=getResources().getDimensionPixelSize(R.dimen.app_bar_height);

        bindViews();
        loadData();

    }

    private void bindViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mBanner = (BGABanner) findViewById(R.id.banner);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

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

        items = new ArrayList<>();
        mAdapter = new HomeRecyclerAdapter(items);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int itemCount, lastItemPosition, lastItemCount;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                itemCount = layoutManager.getItemCount();
                lastItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                if (lastItemCount != itemCount && lastItemPosition == itemCount - 1) {
                    loadOldNews();
                }
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                CharSequence id =  item.getTitleCondensed();
                // start
                return true;

            }
        });


    }

    private void loadData() {
        Call<TodayNewsBean> call = api.getHomePage();
        call.enqueue(new Callback<TodayNewsBean>() {
            @Override
            public void onResponse(Call<TodayNewsBean> call, Response<TodayNewsBean> response) {
                final TodayNewsBean bean = response.body();
                Log.d(TAG, "onResponse: "+bean.getStories().size());
                date = bean.getDate();

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
                        NewsDetailActivity.start(MainActivity.this,newsId);
                    }
                });

                items.add(bean.getDate()); // newsDetail date
                for (StoriesBean storiesBean : bean.getStories()) {
                    items.add(storiesBean); // newsDetail list item
                }
                items.add(new FooterBean());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TodayNewsBean> call, Throwable t) {
                Toast.makeText(MainActivity.this, "获取TodayNews出错...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadOldNews() {
        Call<OldNewsBean> call = api.getOldNews(date);
        call.enqueue(new Callback<OldNewsBean>() {
            @Override
            public void onResponse(Call<OldNewsBean> call, Response<OldNewsBean> response) {
                OldNewsBean bean = response.body();

                date = bean.getDate();

                items.remove(items.size()-1);
                items.add(bean.getDate());
                for (StoriesBean storiesBean : bean.getStories()) {
                    items.add(storiesBean);
                }
                items.add(new FooterBean());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<OldNewsBean> call, Throwable t) {
                Toast.makeText(MainActivity.this, "LoadMore failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
