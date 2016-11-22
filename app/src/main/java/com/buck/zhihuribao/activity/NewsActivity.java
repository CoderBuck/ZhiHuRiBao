package com.buck.zhihuribao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.data.bean.NewsBean;
import com.buck.zhihuribao.network.ApiStoresGenerator;
import com.buck.zhihuribao.network.service.ApiStores;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    public static final String NEWS_ID = "NEWS ID";
    private static final String TAG = "NewsActivity";
    private ApiStores api;

    private Toolbar mToolbar;
    private ImageView mNews_Img;
    private TextView mNews_Title;
    private WebView mNews_Web;

    private int newsId;
    
    public static void start(Context context,int newsId) {
        Intent starter = new Intent(context, NewsActivity.class);
        starter.putExtra(NEWS_ID,newsId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        api= ApiStoresGenerator.getInstance();

        setStatusBarTranslucent();
        getNewsId();
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

    private void getNewsId() {
        newsId=getIntent().getIntExtra(NEWS_ID,0);
        Log.d(TAG, "getNewsId: "+newsId);
    }

    private void bindViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNews_Img = (ImageView) findViewById(R.id.news_img);
        mNews_Title = (TextView) findViewById(R.id.news_title);
        mNews_Web = (WebView) findViewById(R.id.news_web);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNews_Web.getSettings().setJavaScriptEnabled(true);
    }

    public void loadData() {
        Call<NewsBean> call = api.getNews(newsId);
        call.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                Log.d(TAG, "onResponse: ");
                NewsBean bean = response.body();
                if (bean == null) {
                    Log.d(TAG, "onResponse: bean == null");
                }
                mNews_Title.setText(bean.getTitle());
                mNews_Web.loadDataWithBaseURL("about:blank",bean.getBody(),"text/html", "utf-8", null);
                Glide.with(NewsActivity.this)
                        .load(bean.getImage())
                        .centerCrop()
                        .crossFade()
                        .into(mNews_Img);
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {

            }
        });
    }
}
