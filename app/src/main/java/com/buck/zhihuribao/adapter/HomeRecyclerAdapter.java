package com.buck.zhihuribao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.activity.NewsDetailActivity;
import com.buck.zhihuribao.data.bean.StoriesBean;
import com.buck.zhihuribao.utils.DateUtils;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by CoderBuck on 2016/11/22
 */


public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "HomeRecyclerAdapter";
    private static final int DATE = 1;
    private static final int NEWS = 2;
    private static final int FOOTER = 3;
    private List<Object> items;

    public HomeRecyclerAdapter(List<Object> items) {
        this.items=items;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = items.get(position);
        if (item instanceof String)
            return DATE;
        else if (item instanceof StoriesBean)
            return NEWS;
        else
            return FOOTER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == DATE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_date, parent, false);
            return new DateHolder(view);
        } else if (viewType == NEWS) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_news, parent, false);
            return new NewsHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_footer, parent, false);
            return new FooterHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object item = items.get(position);
        if (holder instanceof DateHolder) {
            ((DateHolder) holder).date.setText(DateUtils.parseDate((String)item));

        } else if (holder instanceof NewsHolder) {
            StoriesBean bean = (StoriesBean) item;
            ((NewsHolder) holder).bean = bean;
            ((NewsHolder) holder).news_title.setText(bean.getTitle());
            Glide.with(((NewsHolder) holder).news_image.getContext())
                    .load(bean.getImages().get(0))
                    .centerCrop()
                    .crossFade()
                    .into(((NewsHolder) holder).news_image);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private static class DateHolder extends RecyclerView.ViewHolder {
        TextView date;

        DateHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.time_item);
        }
    }





}
