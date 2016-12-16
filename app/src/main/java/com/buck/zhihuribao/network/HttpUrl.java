package com.buck.zhihuribao.network;

/**
 * Created by CoderBuck on 2016/11/21
 * <p>
 * 数据接口
 */


public interface HttpUrl {

    String baseUrl = "http://news-at.zhihu.com/";
    String splashImage = "api/4/start-image/1080*1920"; // 1. 启动界面图像获取
    String todayNews = "api/4/news/latest"; // 3.首页数据
    String newsDetail = "api/4/news/{news_id}"; // 1. 启动界面图像获取
    String oldNews = "api/4/news/before/{date}"; // 过往news
    String otherNews = "api/4/theme/{id}"; // 主题日报




}
