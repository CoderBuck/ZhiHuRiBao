package com.buck.zhihuribao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.activity.NewsActivity;
import com.buck.zhihuribao.data.bean.HomePageBean;
import com.bumptech.glide.Glide;

/**
 * Created by CoderBuck on 2016/11/22
 */


public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int DATE = 1;
    public static final int NEWS = 2;
    public HomePageBean mBean;
    private Context mContext;

    public HomeRecyclerAdapter(HomePageBean bean,Context context) {
        mBean = bean;
        mContext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == DATE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_date, parent, false);
            return new DateHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_news, parent, false);
            return new NewsHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DateHolder) {
            ((DateHolder) holder).date.setText(mBean.getDate());
        } else {
            ((NewsHolder)holder).news_title.setText(mBean.getStories().get(position).getTitle());
            Glide.with(mContext)
                    .load(mBean.getStories().get(position).getImages().get(0))
                    .centerCrop()
                    .crossFade()
                    .into(((NewsHolder) holder).news_image);
        }
    }

    @Override
    public int getItemCount() {
        return mBean==null?0:mBean.getStories().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return DATE;
        }
        return NEWS;
    }

    private class DateHolder extends RecyclerView.ViewHolder {
        TextView date;

        DateHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.time_item);
        }
    }

    private class NewsHolder extends RecyclerView.ViewHolder {
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
                    int newsId = mBean.getStories().get(getLayoutPosition()).getId();
                    NewsActivity.start(mContext,newsId);
                }
            });
        }
    }
}
