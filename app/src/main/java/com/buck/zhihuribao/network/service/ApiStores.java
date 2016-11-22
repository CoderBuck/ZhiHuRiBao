package com.buck.zhihuribao.network.service;

import com.buck.zhihuribao.data.bean.HomePageBean;
import com.buck.zhihuribao.data.bean.NewsBean;
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

    @GET(HttpUrl.homePage)
    Call<HomePageBean> getHomePage();

   @GET(HttpUrl.news)
    Call<NewsBean> getNews(@Path("news_id") int news_id);
}
