package com.example.think.citypass.fragment.zzh.naonao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.think.citypass.App;
import com.example.think.citypass.R;

public class SquareAdapter extends RecyclerView.Adapter<SquareAdapter.ViewHolder> {

//    private List<String> data;
//
//    public SquareAdapter(List<String> data) {
//        this.data = data;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(View.inflate(App.activity, R.layout.square_item1, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText("111");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.userNameText);
        }
    }

}
