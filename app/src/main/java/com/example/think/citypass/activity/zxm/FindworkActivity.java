package com.example.think.citypass.activity.zxm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseActivity;
import com.example.think.citypass.model.bean.ModelOneBean;

import java.util.ArrayList;

/**
 * Created by 张萌 on 2017/5/17.
 */

public class FindworkActivity extends BaseActivity {
    LinearLayout  close_line,lookmore1,lookmore2;
    ImageView  more_image;
    ListviewAdapter  adapter;
    ArrayList<ModelOneBean>  list;
    TextView  quanzhi_text,jianzhi_text,qiuzhi_text,yewu_text,
    dianyuan_tetx,gongren_text,teacher_text,manager_text,ui_text,
    driver_text,qiantai_text,server_text;
    ListView  mlistview;
    @Override
    protected int layoutId() {
        return R.layout.shouye_findwork;
    }

    @Override
    protected void initView() {
    close_line= (LinearLayout) findViewById(R.id.findwork_close);
        lookmore1= (LinearLayout) findViewById(R.id.findwork_lookmore1);
        lookmore2= (LinearLayout) findViewById(R.id.findwork_lookmore2);



        more_image= (ImageView) findViewById(R.id.findwork_more);

        quanzhi_text= (TextView) findViewById(R.id.findwork_quanzhi);
        jianzhi_text= (TextView) findViewById(R.id.findwork_jianzhi);
        jianzhi_text= (TextView) findViewById(R.id.findwork_jianli);


        yewu_text= (TextView) findViewById(R.id.findwork_yewuxiaoshou);
        dianyuan_tetx= (TextView) findViewById(R.id.findwork_yingyeyuan);
        gongren_text= (TextView) findViewById(R.id.findwork_jigong);
        teacher_text= (TextView) findViewById(R.id.findwork_teacher);
        manager_text= (TextView) findViewById(R.id.findwork_manager);
        ui_text= (TextView) findViewById(R.id.findwork_ui);
        driver_text= (TextView) findViewById(R.id.findwork_driver);
        qiantai_text= (TextView) findViewById(R.id.findwork_qiantai);
        server_text= (TextView) findViewById(R.id.findwork_server);

        mlistview= (ListView) findViewById(R.id.findwork_listview);

    }

    @Override
    protected void initData() {
        list=new ArrayList<>();
        for(int i=0;i<4;i++){
        ModelOneBean  modelOneBean=new ModelOneBean("招聘手机下单员","5000-7999元","3年/不限/不限");
        list.add(modelOneBean);
    }
        adapter=new ListviewAdapter(getApplicationContext(),list);
        mlistview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }


    class   ListviewAdapter  extends BaseAdapter{
        Context  context;
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
            ViewHoler  holer=null;
            if(convertView==null){
                holer=new ViewHoler();
                convertView= LayoutInflater.from(context).inflate(R.layout.findwork_item,null);
                holer.title= (TextView) convertView.findViewById(R.id.findwork_title);
                holer.gongzi= (TextView) convertView.findViewById(R.id.findwork_gongzi);
                holer.tiaojian= (TextView) convertView.findViewById(R.id.findwork_tiaojian);
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
}
