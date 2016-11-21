package com.buck.zhihuribao.network;

import com.buck.zhihuribao.network.service.ApiStores;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiStoresGenerator {

    private static ApiStores INSTANCE;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(HttpUrl.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create());

    public static ApiStores getInstance() {
        if (INSTANCE == null) {
            Retrofit retrofit = builder.client(httpClient.build()).build();
            INSTANCE = retrofit.create(ApiStores.class);
        }
        return INSTANCE;
    }
}