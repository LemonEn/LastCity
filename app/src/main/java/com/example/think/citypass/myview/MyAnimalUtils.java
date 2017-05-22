package com.example.think.citypass.myview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.think.citypass.R;


/**
 *
 * zhangxiaomeng
 */

public class MyAnimalUtils {

    private static boolean boo =false;
    private static GridView mGridView;
    private static Context mContext;


    public static void pictureAnimal(ImageView imageView,Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.startdonghua);
        imageView.startAnimation(animation);
        /*动画停留在结束的位置*/
        animation.setFillAfter(true);
        animation.start();
    }
    public static void pictureAnimala(ImageView imageView,Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.stopdonghua);
        imageView.startAnimation(animation);
        animation.setFillAfter(true);
        animation.start();
    }

    public static void getGVDHB(GridView gridView, Context context) {
        mGridView=gridView;
        mContext=context;
        /*判断 mGridView 里面有多少个视图*/
        if (mGridView.getChildCount() > 0) {

            /*默认是开始动画*/
            if (!boo) {
                /*m GridView 每个 item 开始动画*/
                AnimalStart();
                boo=true;
            } else {
                /*m GridView 每个 item 结束动画*/
                AnimalStop();

                boo=false;
            }

        } else {
            Toast.makeText(context, "+++为空+", Toast.LENGTH_SHORT).show();
        }
    }

    public static void getGVDHB(GridView gridView, Context context,boolean boo) {
        mGridView=gridView;
        mContext=context;
        if (mGridView.getChildCount() > 0) {

            if (!boo) {
                AnimalStart();
                boo=true;
            } else {
                AnimalStop();
                boo=false;
            }

        } else {
            Toast.makeText(context, "+++为空+", Toast.LENGTH_SHORT).show();
        }
    }

    /*开始的动画*/
    private static void AnimalStart() {
        final int shu=1;
        for (int i = 0; i < mGridView.getChildCount(); i++) {

            final int finaA = i;
            final int finalB = (i+1) * 150;
            final Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    startAnim(mGridView.getChildAt(finaA),shu);
                }
            };
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(finalB);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = handler.obtainMessage();
                    handler.sendMessage(msg);
                }
            }).start();

        }
    }

    /*结束的动画*/
    private static void AnimalStop() {
        final int shu=0;
        for (int i = mGridView.getChildCount() - 1; i >= 0; i--) {
            final int finalA = i;
            final int a = mGridView.getChildCount() - i;
            final int finalB = a * 150;
            Log.d("DongHuaActivity", "finals:" + finalB);
            final Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    startAnim(mGridView.getChildAt(finalA),shu);
                }
            };

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(finalB);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = handler.obtainMessage();
                    handler.sendMessage(msg);
                }
            }).start();

        }
    }


    /*执行动画*/
    private static void startAnim(final View view, final int shu) {

        switch (shu){
            case 0:/*结束的动画*/
                view.startAnimation(AnimationUtils.loadAnimation(mContext,
                        R.anim.stopanimal));
                view.setVisibility(View.INVISIBLE);
                break;

            case 1:/*开始的动画*/
                view.startAnimation(AnimationUtils.loadAnimation(mContext,
                        R.anim.startanimal));
                view.setVisibility(View.VISIBLE);
//                final Handler handler = new Handler() {
//                    @Override
//                    public void handleMessage(Message msg) {
//                        super.handleMessage(msg);
//
//                        view.startAnimation(AnimationUtils.loadAnimation(mContext,
//                                R.anim.startanimaer));
//                    }
//                };
//                new Thread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(150);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        handler.sendEmptyMessage(1);
//                    }
//                }).start();
                break;
        }



    }


}
