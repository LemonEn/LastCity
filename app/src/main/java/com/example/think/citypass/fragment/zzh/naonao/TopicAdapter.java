package com.example.think.citypass.fragment.zzh.naonao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.think.citypass.App;
import com.example.think.citypass.R;

/**
 * Created by ASUS on 2017/5/18.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(View.inflate(App.activity, R.layout.topic_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText("人气11");
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.topic_title_renqu);
        }
    }
}
