package com.example.think.citypass.fragment.zzh.naonao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.think.citypass.App;
import com.example.think.citypass.R;

/**
 * Created by ASUS on 2017/5/18.
 */

public class WangyouAdapter extends RecyclerView.Adapter<WangyouAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(View.inflate(App.activity, R.layout.list_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
