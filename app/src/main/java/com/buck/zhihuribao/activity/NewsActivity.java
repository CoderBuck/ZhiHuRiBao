package com.buck.zhihuribao.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        mNews_Web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(NewsActivity.this, Uri.parse(url));
                return true;
            }
        });
    }

    public void loadData() {
        Call<NewsBean> call = api.getNews(newsId);
        call.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                NewsBean bean = response.body();
                mNews_Title.setText(bean.getTitle());
                Glide.with(NewsActivity.this)
                        .load(bean.getImage())
                        .centerCrop()
                        .crossFade()
                        .into(mNews_Img);

                String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
                String html = "<html><head>" + css + "</head><body>" + bean.getBody() + "</body></html>";
                html=html.replace("<div class=\"img-place-holder\">", "");
                mNews_Web.loadDataWithBaseURL("x-data://base",html,"text/html", "utf-8", null);
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "加载失败...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
