package com.oa.yk.ykoa;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.squareup.leakcanary.LeakCanary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 78560 on 2017/7/31.
 */

public class OaApplication extends Application{
    public static Context applicationContext;
    private static OaApplication instance;

    public static Context getContext(){
        return applicationContext;
    }
    public static OaApplication getInstance() {
        return instance;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext=this;
        instance=this;

        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
    }

    //用于存放我们所有activity的数组
    public static List<Activity> activities;

    //向集合中添加一个activity
    public static void addActivity(Activity activity){
        if(activities == null){
            //如果集合为空  则初始化
            activities = new ArrayList<>();
        }
        //将activity加入到集合中
        activities.add(activity);
    }

    //结束掉所有的Activity
    public static void finishAll(){
        //循环集合,  将所有的activity finish
        for(Activity activity : activities){
            if(! activity.isFinishing()){
                activity.finish();
            }
        }
    }
    public static void removeActivity(Activity activity){
        //移除已经销毁的Activity
        activities.remove(activity);
    }

    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 初始化LeakCanary
     */
    private void initLeakCanary() {
        if (true) {
            LeakCanary.install(this);
        }
    }
}
