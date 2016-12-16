package com.buck.zhihuribao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.activity.MainActivity;
import com.buck.zhihuribao.activity.NewsDetailActivity;
import com.buck.zhihuribao.adapter.HomeRecyclerAdapter;
import com.buck.zhihuribao.data.bean.FooterBean;
import com.buck.zhihuribao.data.bean.OldNewsBean;
import com.buck.zhihuribao.data.bean.StoriesBean;
import com.buck.zhihuribao.data.bean.TodayNewsBean;
import com.buck.zhihuribao.network.ApiStoresGenerator;
import com.buck.zhihuribao.network.service.ApiStores;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Buck on 2016/12/8.
 */

public class MainFragment extends Fragment {

    private static final String TAG =  MainFragment.class.getSimpleName();
    private BGABanner mBanner;
    private Toolbar mToolbar;
    private RecyclerView mRecycle;

    private ApiStores api;
    private String date;
    private List<Object> items;
    private HomeRecyclerAdapter mAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main,container,false);
        api = ApiStoresGenerator.getInstance();
        findViews(root);
        loadData();
        return root;
    }


    private void findViews(View root) {
        mBanner = (BGABanner) root.findViewById(R.id.banner);
        mToolbar = (Toolbar) root.findViewById(R.id.toolbar);
        mRecycle = (RecyclerView) root.findViewById(R.id.recycle);

        mToolbar.setNavigationOnClickListener(new NavigationIconOnClickListener());

        mBanner.setAdapter(new MyBannerAdapter());

        items = new ArrayList<>();
        mAdapter = new HomeRecyclerAdapter(items);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecycle.setLayoutManager(layoutManager);
        mRecycle.setAdapter(mAdapter);
        mRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {

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


    }

    private void loadData() {
        Call<TodayNewsBean> call = api.getHomePage();
        call.enqueue(new Callback<TodayNewsBean>() {
            @Override
            public void onResponse(Call<TodayNewsBean> call, Response<TodayNewsBean> response) {
                final TodayNewsBean bean = response.body();
                Log.d(TAG, "onResponse: "+bean.getStories().size());
                date = bean.getDate();

                List<String> imgs = new ArrayList<>();
                List<String> tips = new ArrayList<>();
                for (int i = 0; i < bean.getTop_stories().size(); i++) {
                    imgs.add(bean.getTop_stories().get(i).getImage());
                    tips.add(bean.getTop_stories().get(i).getTitle());
                }
                mBanner.setData(imgs,tips);
                mBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
                    @Override
                    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
                        int newsId = bean.getTop_stories().get(position).getId();
                        NewsDetailActivity.start(getContext(),newsId);
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
                Toast.makeText(getContext(), "获取TodayNews出错...", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "LoadMore failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class NavigationIconOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            ((MainActivity)getActivity()).openDrawer();
        }
    }

    private class MyBannerAdapter implements BGABanner.Adapter {
        @Override
        public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
            Glide.with(banner.getContext())
                    .load(model)
                    .centerCrop()
                    .crossFade()
                    .into((ImageView)view);
        }
    }

}
