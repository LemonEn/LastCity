package com.example.think.citypass.activity.zxm;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseActivity;

/**
 * Created by 张萌 on 2017/5/16.
 */

public class CityChoiceActivity  extends BaseActivity {
    TextView  rightText,hometext2,liketext2,jobtext2;
    ImageView  homeimage,likeimage,jobimage;
    boolean  isSet=false;
    RelativeLayout  citylay;
    @Override
    protected int layoutId() {
        return R.layout.city_choice;
    }

    @Override
    protected void initView() {
             rightText= (TextView) findViewById(R.id.right_text);
             homeimage= (ImageView) findViewById(R.id.home_pen_image);
        likeimage= (ImageView) findViewById(R.id.like_pen_image);
        jobimage= (ImageView) findViewById(R.id.job_pen_image);
        hometext2= (TextView) findViewById(R.id.home_text2);
        liketext2= (TextView) findViewById(R.id.school_text2);
        jobtext2= (TextView) findViewById(R.id.job_text2);
        citylay= (RelativeLayout) findViewById(R.id.choice_relay);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
     rightText.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(isSet){
                 isSet=false;
                 citylay.setBackgroundResource(R.drawable.isset);
                 homeimage.setImageResource(R.drawable.city_choice_pen);
                 likeimage.setImageResource(R.drawable.city_choice_pen);
                 jobimage.setImageResource(R.drawable.city_choice_pen);


             }else{
                 isSet=true;
                 citylay.setBackgroundResource(R.drawable.city_choice_bg_shape2);
                 homeimage.setImageResource(R.drawable.task_mymeda_rightarrow2);
                 likeimage.setImageResource(R.drawable.task_mymeda_rightarrow2);
                 jobimage.setImageResource(R.drawable.task_mymeda_rightarrow2);
             }
         }
     });
    }

    @Override
    protected void loadData() {

    }
}
