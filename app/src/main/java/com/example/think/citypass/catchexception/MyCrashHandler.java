package com.example.think.citypass.catchexception;

        import java.lang.Thread.UncaughtExceptionHandler;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.util.Log;
        import android.widget.Toast;

/**
 * 异常处理类
 * @author zhangxiaomeng
 *
 */
public class MyCrashHandler implements UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    private static MyCrashHandler INSTANCE = new MyCrashHandler();
    private Context mContext;
    ProgressDialog loadingDialog;

    private MyCrashHandler() {
    }

    public static MyCrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context ctx) {
        mContext = ctx;
        Thread.setDefaultUncaughtExceptionHandler(this);

        //初始化异常处理类
    }

    @Override
    public void uncaughtException(Thread arg0, Throwable arg1) {

        //捕获到异常后在这里做处理
        Log.i("---catch---","已捕获到异常,将在2秒后处理");
    }


}
