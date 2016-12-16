package com.buck.zhihuribao.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.buck.zhihuribao.R;
import com.buck.zhihuribao.activity.MainActivity;
import com.buck.zhihuribao.adapter.OtherNewsAdapter;
import com.buck.zhihuribao.data.bean.FooterBean;
import com.buck.zhihuribao.data.bean.OtherNewsBean;
import com.buck.zhihuribao.network.ApiStoresGenerator;
import com.buck.zhihuribao.network.service.ApiStores;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Buck on 2016/12/8.
 */

public class OtherFragment extends Fragment {

    private static final String TAG = OtherFragment.class.getSimpleName();
    private static String ID = "id";
    private static String TITLE = "title";
    private ImageView mImage;
    private TextView mDescription;
    private Toolbar mToolbar;
    private RecyclerView mRecycle;

    private String id;
    private String title;
    private ApiStores api;
    private List<Object> items;
    private OtherNewsAdapter adapter;

    public static OtherFragment newInstance(String id,String title) {
        Bundle args = new Bundle();
        args.putString(ID,id);
        args.putString(TITLE,title);
        OtherFragment fragment = new OtherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View root = inflater.inflate(R.layout.fragment_other, container, false);
        api = ApiStoresGenerator.getInstance();
        id = getArguments().getString(ID);
        title = getArguments().getString(TITLE);
        findViews(root);
        loadData();

        return root;
    }


    private void findViews(View root) {

        Log.d(TAG, "findViews: ");
        mImage = (ImageView) root.findViewById(R.id.image);
        mDescription = (TextView) root.findViewById(R.id.description);
        mToolbar = (Toolbar) root.findViewById(R.id.toolbar);
        mRecycle = (RecyclerView) root.findViewById(R.id.recycle);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openDrawer();
            }
        });
        mToolbar.setTitle(title);

        items = new ArrayList<>();
        adapter = new OtherNewsAdapter(items);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecycle.setLayoutManager(manager);
        mRecycle.setAdapter(adapter);
    }

    public void loadData() {

        Log.d(TAG, "loadData: ");
        Call<OtherNewsBean> call = api.getOtherNews(id);
        call.enqueue(new Callback<OtherNewsBean>() {
            @Override
            public void onResponse(Call<OtherNewsBean> call, Response<OtherNewsBean> response) {
                Log.d(TAG, "onResponse: ");
                OtherNewsBean bean = response.body();

                if (TextUtils.isEmpty(bean.getImage())) {
                    mImage.setVisibility(View.GONE);
                    mDescription.setVisibility(View.GONE);
                } else {
                    Glide.with(getContext())
                            .load(bean.getImage())
                            .centerCrop()
                            .into(mImage);
                    mDescription.setText(bean.getDescription());
                }

                items.add(bean.getEditors());
                for (int i = 0; i < bean.getStories().size(); i++) {
                    items.add(bean.getStories().get(i));
                }
                items.add(new FooterBean());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<OtherNewsBean> call, Throwable t) {
                Toast.makeText(getContext(), "获取 theme news 失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
