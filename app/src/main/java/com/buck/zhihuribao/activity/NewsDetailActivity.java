package com.buck.zhihuribao.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.data.bean.NewsDetailBean;
import com.buck.zhihuribao.network.ApiStoresGenerator;
import com.buck.zhihuribao.network.service.ApiStores;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String NEWS_ID = "NEWS ID";
    private static final String TAG = "NewsDetailActivity";
    private ApiStores api;

    private ImageView mMenu_back;
    private ImageButton mMenu_share;
    private ImageButton mMenu_love;
    private TextView mMenu_comment;
    private TextView mMenu_zan;

    private ImageView mNews_Img;
    private TextView mNews_Title;
    private WebView mNews_Web;

    private int newsId;
    
    public static void start(Context context,int newsId) {
        Intent starter = new Intent(context, NewsDetailActivity.class);
        starter.putExtra(NEWS_ID,newsId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        api= ApiStoresGenerator.getInstance();

        getNewsId();
        bindViews();
        loadData();
    }


    private void getNewsId() {
        newsId=getIntent().getIntExtra(NEWS_ID,0);
        Log.d(TAG, "getNewsId: "+newsId);
    }

    private void bindViews() {
        mMenu_back = (ImageView) findViewById(R.id.menu_back);
        mMenu_share = (ImageButton) findViewById(R.id.menu_share);
        mMenu_love = (ImageButton) findViewById(R.id.menu_love);
        mMenu_comment = (TextView) findViewById(R.id.menu_comment);
        mMenu_zan = (TextView) findViewById(R.id.menu_zan);
        mNews_Img = (ImageView) findViewById(R.id.news_img);
        mNews_Title = (TextView) findViewById(R.id.news_title);
        mNews_Web = (WebView) findViewById(R.id.news_web);

        mMenu_back.setOnClickListener(this);
        mMenu_share.setOnClickListener(this);
        mMenu_love.setOnClickListener(this);
        mMenu_comment.setOnClickListener(this);
        mMenu_zan.setOnClickListener(this);

        mNews_Web.getSettings().setJavaScriptEnabled(true);
        mNews_Web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(NewsDetailActivity.this, Uri.parse(url));
                return true;
            }
        });
    }

    public void loadData() {
        Call<NewsDetailBean> call = api.getNewsDetail(newsId);
        call.enqueue(new Callback<NewsDetailBean>() {
            @Override
            public void onResponse(Call<NewsDetailBean> call, Response<NewsDetailBean> response) {
                NewsDetailBean bean = response.body();
                mNews_Title.setText(bean.getTitle());
                Glide.with(NewsDetailActivity.this)
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
            public void onFailure(Call<NewsDetailBean> call, Throwable t) {
                Toast.makeText(NewsDetailActivity.this, "加载失败...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_back:
                onBackPressed();
                break;
            case R.id.menu_share:
            case R.id.menu_love:
            case R.id.menu_comment:
            case R.id.menu_zan:
                break;
        }
    }
}
