package com.example.studyandroid.Refactoring;

import android.app.Application;

import com.example.studyandroid.Refactoring.Processor.HttpHelper;
import com.example.studyandroid.Refactoring.Processor.OKHttpProcessor;
import com.example.studyandroid.Refactoring.Processor.VolleyProcessor;
import com.example.studyandroid.Refactoring.Processor.XUtilsProcessor;

import org.xutils.BuildConfig;
import org.xutils.x;

public class NeRefactorApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);//初始化XUtils
//        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

//        HttpHelper.init(new VolleyProcessor(this));
//        HttpHelper.init(new OKHttpProcessor());
        HttpHelper.init(new XUtilsProcessor());

    }
}
