package com.buck.zhihuribao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.data.bean.EditorsBean;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Buck on 2016/12/8.
 */

public class EditorAdapter extends RecyclerView.Adapter<EditorAdapter.ViewHolder> {

    private List<EditorsBean> items;

    public EditorAdapter(List<EditorsBean> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.image.getContext())
                .load(items.get(position).getAvatar())
                .centerCrop()
                .crossFade()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (CircleImageView) itemView.findViewById(R.id.image);
        }
    }
}
