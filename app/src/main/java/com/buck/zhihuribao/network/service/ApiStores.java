package com.buck.zhihuribao.network.service;

import com.buck.zhihuribao.data.bean.SplashImageBean;
import com.buck.zhihuribao.network.HttpUrl;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by CoderBuck on 2016/11/21
 */


public interface ApiStores {
    @GET(HttpUrl.splashImage)
    Call<SplashImageBean> getSplashImage();
}
