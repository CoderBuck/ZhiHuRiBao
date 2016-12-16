package com.buck.zhihuribao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.activity.NewsDetailActivity;
import com.buck.zhihuribao.data.bean.StoriesBean;

public class NewsHolder extends RecyclerView.ViewHolder {

        StoriesBean bean;
        LinearLayout news_layout;
        ImageView news_image;
        TextView news_title;

        NewsHolder(View itemView) {
            super(itemView);
            news_layout = (LinearLayout) itemView.findViewById(R.id.news_layout);
            news_image= (ImageView) itemView.findViewById(R.id.image_item);
            news_title= (TextView) itemView.findViewById(R.id.title_item);

            news_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int newsId = bean.getId();
                    NewsDetailActivity.start(v.getContext(),newsId);
                }
            });
        }
    }