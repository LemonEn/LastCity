package com.example.think.citypass.activity.zxm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseActivity;
import com.example.think.citypass.model.bean.ModelOneBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张萌 on 2017/5/18.
 */

public class FindhouseActivity  extends BaseActivity {
    ListView  likelistview,fangchanlistview;
    ArrayList<ModelOneBean>  likelist,fangchanlist;
    ListviewAdapter  listviewAdapter;
    FangchanAdapter fangchanAdapter;
    @Override
    protected int layoutId() {
        return R.layout.shouye_findhouse;
    }

    @Override
    protected void initView() {
     likelistview= (ListView) findViewById(R.id.findhouse_like_listview);
        fangchanlistview= (ListView) findViewById(R.id.findhouse_fangchan_listview);
    }

    @Override
    protected void initData() {
        likelist=new ArrayList<>();
        fangchanlist=new ArrayList<>();
        for(int i=0;i<4;i++){
            ModelOneBean modelOneBean=new ModelOneBean("我叫路飞是要成为海贼王的男人","2020.05.20  20;13:13","");
            fangchanlist.add(modelOneBean);
        }
        fangchanAdapter=new FangchanAdapter(getApplication(),fangchanlist);
        fangchanlistview.setAdapter(fangchanAdapter);
        fangchanAdapter.notifyDataSetChanged();


        for(int i=0;i<4;i++){
            ModelOneBean modelOneBean=new ModelOneBean("我要吃肉 我叫路飞","67天/不限/不限","36万");
            likelist.add(modelOneBean);
        }
        listviewAdapter=new ListviewAdapter(getApplication(),likelist);
        likelistview.setAdapter(listviewAdapter);
        listviewAdapter.notifyDataSetChanged();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }


    class   ListviewAdapter  extends BaseAdapter {
        Context context;
        ArrayList<ModelOneBean>  mlist;

        public ListviewAdapter(Context context, ArrayList<ModelOneBean> mlist) {
            this.context = context;
            this.mlist = mlist;
        }

        @Override
        public int getCount() {
            return mlist.isEmpty()?0:mlist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler holer=null;
            if(convertView==null){
                holer=new ViewHoler();
                convertView= LayoutInflater.from(context).inflate(R.layout.findhouse_likeitem,null);
                holer.title= (TextView) convertView.findViewById(R.id.findhouse_likeitem_title);
                holer.gongzi= (TextView) convertView.findViewById(R.id.findhouse_likeitem_jiage);
                holer.tiaojian= (TextView) convertView.findViewById(R.id.findhouse_likeitem_tiaojian);
                convertView.setTag(holer);
            }else {
                holer = (ViewHoler) convertView.getTag();
            }
            ModelOneBean  bean=mlist.get(position);
            holer.title.setText(bean.getTitle()+"");
            holer.tiaojian.setText(bean.getAuthor()+"");
            holer.gongzi.setText(bean.getCount()+"");
            return convertView;
        }

        class ViewHoler{
            TextView  title,tiaojian,gongzi;
        }
    }


    /***
     * 房产listview的适配器
     */
    class   FangchanAdapter  extends BaseAdapter{
        Context  context;
        ArrayList<ModelOneBean>  mlist;

        public FangchanAdapter(Context context, ArrayList<ModelOneBean> mlist) {
            this.context = context;
            this.mlist = mlist;
        }

        @Override
        public int getCount() {
            return mlist.isEmpty()?0:mlist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
          ViewHoler holer=null;
            if(convertView==null){
                holer=new ViewHoler();
                convertView= LayoutInflater.from(context).inflate(R.layout.findhouse_fangchan_item,null);
                holer.title= (TextView) convertView.findViewById(R.id.findhouse_item_title);
                holer.gongzi= (TextView) convertView.findViewById(R.id.findhouse_item_time);
                convertView.setTag(holer);
            }else {
                holer = (ViewHoler) convertView.getTag();
            }
            ModelOneBean  bean=mlist.get(position);
            holer.title.setText(bean.getTitle()+"");
            holer.gongzi.setText(bean.getCount()+"");
            return convertView;
        }

        class ViewHoler{
            TextView  title,gongzi;
        }
    }
}
