package com.example.think.citypass.fragment.zzh.naonao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.think.citypass.App;
import com.example.think.citypass.R;

import java.util.List;

public class NaonaoAdapter extends RecyclerView.Adapter<NaonaoAdapter.ViewHolder> {
    private List<String> data;

    public NaonaoAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(View.inflate(App.activity, R.layout.naonaodi_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText("大黄蜂");
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.userNameText);
        }
    }
}
