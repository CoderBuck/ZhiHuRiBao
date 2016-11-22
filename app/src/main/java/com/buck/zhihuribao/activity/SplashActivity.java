package com.buck.zhihuribao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.data.bean.SplashImageBean;
import com.buck.zhihuribao.network.ApiStoresGenerator;
import com.buck.zhihuribao.network.service.ApiStores;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by CoderBuck on 2016/11/21
 */


public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImage;
    private TextView mSkip_text;
    private TextView mName_text;

    private ApiStores api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        api = ApiStoresGenerator.getInstance();

        bindViews();
        getData();
        autoSkip();

    }

    private void bindViews() {
        mImage = (ImageView) findViewById(R.id.image);
        mSkip_text = (TextView) findViewById(R.id.skip_text);
        mName_text = (TextView) findViewById(R.id.name_text);

        mSkip_text.setOnClickListener(this);
    }

    private void getData() {
        Call<SplashImageBean> call = api.getSplashImage();
        call.enqueue(new Callback<SplashImageBean>() {
            @Override
            public void onResponse(Call<SplashImageBean> call, Response<SplashImageBean> response) {
                SplashImageBean bean = response.body();
                Glide.with(SplashActivity.this)
                        .load(bean.getImg())
                        .centerCrop()
                        .crossFade()
                        .into(mImage);
                mName_text.setText(bean.getText());
            }

            @Override
            public void onFailure(Call<SplashImageBean> call, Throwable t) {
                Toast.makeText(SplashActivity.this, "获取图片出错...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void autoSkip() {
        mSkip_text.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        },3000);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
