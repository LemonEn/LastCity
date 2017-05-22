package com.example.think.citypass.fragment.zzh.naonao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.think.citypass.App;
import com.example.think.citypass.R;

import java.util.List;

/**
 * Created by ASUS on 2017/5/18.
 */

public class ShaituAdapter extends RecyclerView.Adapter<ShaituAdapter.ViewHolder> {
    private List<String> data;

    public ShaituAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(View.inflate(App.activity, R.layout.shaitu_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText("大黄蜂");
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.shaitu_name);
        }
    }
}
