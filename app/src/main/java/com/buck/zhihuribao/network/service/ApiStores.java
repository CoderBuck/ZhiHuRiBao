package com.buck.zhihuribao.network.service;

import com.buck.zhihuribao.data.bean.OldNewsBean;
import com.buck.zhihuribao.data.bean.OtherNewsBean;
import com.buck.zhihuribao.data.bean.TodayNewsBean;
import com.buck.zhihuribao.data.bean.NewsDetailBean;
import com.buck.zhihuribao.data.bean.SplashImageBean;
import com.buck.zhihuribao.network.HttpUrl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by CoderBuck on 2016/11/21
 */


public interface ApiStores {
    @GET(HttpUrl.splashImage)
    Call<SplashImageBean> getSplashImage();

    @GET(HttpUrl.todayNews)
    Call<TodayNewsBean> getHomePage();

   @GET(HttpUrl.newsDetail)
    Call<NewsDetailBean> getNewsDetail(@Path("news_id") int news_id);

    @GET(HttpUrl.oldNews)
    Call<OldNewsBean> getOldNews(@Path("date") String date);

    @GET(HttpUrl.otherNews)
    Call<OtherNewsBean> getOtherNews(@Path("id") String id);
}
