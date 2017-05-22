package com.example.think.citypass.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.think.citypass.R;
import com.example.think.citypass.activity.home.CeLaUtils;
import com.example.think.citypass.activity.lxl.job.AllRecruitmentFragment;
import com.example.think.citypass.activity.lxl.job.MyMoneyActivity;
import com.example.think.citypass.activity.shezhi.SheZhiActivity;
import com.example.think.citypass.activity.zxm.CityChoiceActivity;
import com.example.think.citypass.activity.zxm.LoginActivity;
import com.example.think.citypass.activity.zxm.ShouyeNotice;
import com.example.think.citypass.common.base.BaseActivity;
import com.example.think.citypass.common.base.BaseFragment;
import com.example.think.citypass.common.config.FragmentBuilder;
import com.example.think.citypass.fragment.xzy.LoadFragment;
import com.example.think.citypass.fragment.zxm.ShouYeFragment;
import com.example.think.citypass.fragment.zzh.NaonaoFragment;
import com.example.think.citypass.myview.MyAnimalUtils;
import com.example.think.citypass.myview.MyGradeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/5/15.
 */

public class HomeActivity extends BaseActivity {
    private TextView mText,mCityMoney,mSheZhi;
    LinearLayout  top;
    private DrawerLayout mDrawerLayout;
    private MyGradeView linkPageGridview;
    private PopupWindow popupWindow;
    private Handler handler = new Handler();
    private List<String> mlist;
    private List<Integer> mDList;
    RelativeLayout  layout;
    ImageView  titleimage;
    ImageView  im1,im2,im3;
    private RadioGroup mRadioGroup;
    private static boolean isExit = false;

    private final int BACK = 0;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                isExit = false;
            }
        }
    };

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(HomeActivity.this, "再次点击退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(BACK, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.firstcehuamenu;
    }

    @Override
    protected void initView() {

        top= (LinearLayout) findViewById(R.id.top);
        View  vi=LayoutInflater.from(HomeActivity.this).inflate(R.layout.ppmb,null);
        layout= (RelativeLayout) vi.findViewById(R.id.layout);
        linkPageGridview = (MyGradeView) vi.findViewById(R.id.link_page_gridview);

        titleimage= (ImageView) findViewById(R.id.titleBar_imageView);
        im1= (ImageView) findViewById(R.id.title_choose1);
        im2= (ImageView) findViewById(R.id.title_choose2);
        im3= (ImageView) findViewById(R.id.title_choose3);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.mDrawerLayout);
        mRadioGroup = (RadioGroup) findViewById(R.id.Bottom_Group);
        mCityMoney= (TextView) findViewById(R.id.My_City_MoneyText);
        mSheZhi= (TextView) findViewById(R.id.SheZhi);


    }



    @Override
    protected void initData() {
        adddata();
        getPopup();

//        第一次进入显示的Fragment
        FragmentBuilder.getInstance().show(ShouYeFragment.class).builder();
    }
    @Override
    protected void initListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.TouTiaoBtn:
                         top.setVisibility(View.VISIBLE);
                        FragmentBuilder.getInstance().show(ShouYeFragment.class).builder();
                        break;
                    case R.id.NaoNaoBtn:
                        //这是首页的titlebar所以在别的fragment隐藏
                        top.setVisibility(View.GONE);
                        FragmentBuilder.getInstance().show(NaonaoFragment.class).builder();
                        break;
                    case R.id.SheQuBtn:
                        //这是首页的titlebar所以在别的fragment隐藏
                        top.setVisibility(View.GONE);
//                        FragmentBuilder.getInstance().show().builder();
                        break;
                    case R.id.ShengHuoBtn:
                        //这是首页的titlebar所以在别的fragment隐藏
                        top.setVisibility(View.GONE);
                        FragmentBuilder.getInstance().show(AllRecruitmentFragment.class).builder();
                        break;
                    case R.id.DiscoverBtn:
                        //这是首页的titlebar所以在别的fragment隐藏
                        top.setVisibility(View.GONE);
                        FragmentBuilder.getInstance().show(LoadFragment.class).builder();
                        break;


                }
            }
        });

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                CeLaUtils.slideAnim(mDrawerLayout, drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


//        mText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()){
//                    case R.id.SheZhi:
//                        Intent intent=new Intent(HomeActivity.this,SheZhiActivity.class);
//                        startActivity(intent);
//                        break;
//                    case R.id.My_City_Money:
//                        Toast.makeText(HomeActivity.this, "点击成功", Toast.LENGTH_SHORT).show();
//                        Intent intent1=new Intent(HomeActivity.this, MyMoneyActivity.class);
//                        startActivity(intent1);
//                        break;
//                }
//            }
//        });



        mSheZhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.SheZhi:
                        Intent intent=new Intent(HomeActivity.this,SheZhiActivity.class);
                        Toast.makeText(HomeActivity.this, "点击事件", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
//                    case R.id.My_City_MoneyText:
//                        Toast.makeText(HomeActivity.this, "点击成功", Toast.LENGTH_SHORT).show();
//                        Intent intent1=new Intent(HomeActivity.this, MyMoneyActivity.class);
//                        startActivity(intent1);
//                        break;

                }
            }
        });
        mCityMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "点击成功", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(HomeActivity.this, MyMoneyActivity.class);
                startActivity(intent1);

            }
        });



        titleimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent  intent1=new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });

        //点击选择城市
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(HomeActivity.this, CityChoiceActivity.class);
                startActivity(intent);
            }
        });
        //点击通知
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(HomeActivity.this, ShouyeNotice.class);
                startActivity(intent);
            }
        });

        //点击加号
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewClicked();
            }
        });

    }

    @Override
    protected void loadData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentBuilder.clean();
    }


//加号动画初始化数据
    private void adddata(){
        mlist=new ArrayList<>();
        mlist.add("发帖子");
        mlist.add("发照片");
        mlist.add("小视频");
        mlist.add("有奖爆料");
        mlist.add("分类信息");
        mlist.add("二维码");
        mDList=new ArrayList<>();
        mDList.add(R.drawable.ico_quanbu);
        mDList.add(R.drawable.baby_camera);
        mDList.add(R.drawable.ico_meishi);
        mDList.add(R.drawable.ico_qinzi);
        mDList.add(R.drawable.ic_jianli_icon);
        mDList.add(R.drawable.ic_liulan);

    }
    public void onViewClicked() {
        /*首先执行 加号的动画*/
        MyAnimalUtils.pictureAnimal(im3, HomeActivity.this);
        popupWindow.showAtLocation(findViewById(R.id.top), Gravity.BOTTOM, 0, 0);
        handler.postDelayed(new Runnable() {
            public void run() {
          /*执行popupwindow 出现的动画*/
                MyAnimalUtils.getGVDHB(linkPageGridview, HomeActivity.this, false);
            }
        }, 400);
    }

    private void getPopup() {
        View view = LayoutInflater.from(HomeActivity.this).inflate(R.layout.ppmb, null);
        view.getBackground().setAlpha(200);
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.layout);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(null);
        linkPageGridview = (MyGradeView) view.findViewById(R.id.link_page_gridview);
        GVAdapter  gvAdapter = new GVAdapter(mDList, mlist, this);
        linkPageGridview.setAdapter(gvAdapter);
        popupWindow.setAnimationStyle(R.style.Animation);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*让每一个item先消失*/
                MyAnimalUtils.getGVDHB(linkPageGridview, HomeActivity.this, true);
                /*得到一共有几个item 然后乘以每个item 消失的时间*/
                /*获得item 消失一共用了多长时间*/
                long i = (linkPageGridview.getChildCount() + 1) * 160;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /*在item 消失点后让popupWindow消失*/
                        popupWindow.dismiss();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                /*在PopupWindow消失后 让加号执行逆时针动作*/
                                MyAnimalUtils.pictureAnimala(im3, HomeActivity.this);
                            }
                        }, 100);
                    }
                }, i);
            }
        });
    }




    /**
     * 张晓萌
     * gridview的适配器
     */

    class GVAdapter extends BaseAdapter {

        private List<Integer> mDList;
        private List<String> mlist;
        private Context context;
        private  TextView textView;

        public GVAdapter(List<Integer> mDList, List<String> mlist, Context context) {
            this.mDList = mDList;
            this.mlist = mlist;
            this.context = context;
        }

        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public Object getItem(int position) {
            return mlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder h=new Holder();
            convertView= LayoutInflater.from(context).inflate(R.layout.gv_item,null);
            h.textView= (TextView) convertView.findViewById(R.id.textA);
            h.imageView= (ImageView) convertView.findViewById(R.id.imageA);
            h.textView.setText(mlist.get(position));
            h.imageView.setImageResource(mDList.get(position));
            convertView.setVisibility(View.INVISIBLE);
            return convertView;
        }
        class Holder{
            private TextView textView;
            private ImageView imageView;
        }
    }


}



