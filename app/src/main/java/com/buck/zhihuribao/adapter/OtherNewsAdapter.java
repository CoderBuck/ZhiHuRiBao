package com.buck.zhihuribao.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.data.bean.EditorsBean;
import com.buck.zhihuribao.data.bean.StoriesBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Buck on 2016/12/8.
 */
public class OtherNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int EDITOR = 1;
    private static final int NEWS = 2;
    private static final int FOOTER = 3;

    private List<Object> items;

    public OtherNewsAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = items.get(position);
        if (item instanceof List) {
            return EDITOR;
        }
        else if (item instanceof StoriesBean)
            return NEWS;
        else
            return FOOTER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == EDITOR) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_editor,parent,false);
            return new EditorHolder(view);
        } else if (viewType == NEWS) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_news, parent, false);
            return new NewsHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_footer,parent,false);
            return new FooterHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EditorHolder) {
            EditorHolder editorHolder = (EditorHolder) holder;
            List<EditorsBean> beans = (List<EditorsBean>) items.get(position);
            LinearLayoutManager manager = new LinearLayoutManager(editorHolder.recycler.getContext(), LinearLayoutManager.HORIZONTAL, false);
            editorHolder.recycler.setLayoutManager(manager);
            editorHolder.recycler.setAdapter(new EditorAdapter(beans));

        } else if (holder instanceof NewsHolder) {
            NewsHolder newsHolder = (NewsHolder) holder;
            StoriesBean bean = (StoriesBean) items.get(position);

            newsHolder.bean = bean;
            if (bean.getImages() == null || bean.getImages().size() == 0) {
                newsHolder.news_image.setVisibility(View.GONE);
            } else {
                Glide.with(newsHolder.news_image.getContext())
                        .load(bean.getImages().get(0))
                        .centerCrop()
                        .crossFade()
                        .into(newsHolder.news_image);
            }
            newsHolder.news_title.setText(bean.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private static class EditorHolder extends RecyclerView.ViewHolder {
        RecyclerView recycler;
        public EditorHolder(View itemView) {
            super(itemView);
            recycler = (RecyclerView) itemView.findViewById(R.id.recycle);
        }
    }


}
