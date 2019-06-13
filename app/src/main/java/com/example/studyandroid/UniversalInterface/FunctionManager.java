package com.example.studyandroid.UniversalInterface;

import android.text.TextUtils;
import android.util.Log;

import com.example.studyandroid.bean.User;

import java.util.HashMap;
import java.util.Map;

public class FunctionManager {

    //单例模式
    private static FunctionManager instance;

    private Map<String, FunctionHasParamHasResult> mFunctionHasParamHasResult;
    private Map<String, FunctionHasParamNoResult> mFunctionHasParamNoResult;
    private Map<String, FunctionNoParamHasResult> mFunctionNoParamHasResult;
    private Map<String, FunctionNoParamNoResult> mFunctionNoParamNoResult;

    private FunctionManager() {
        mFunctionHasParamHasResult = new HashMap<>();
        mFunctionHasParamNoResult = new HashMap<>();
        mFunctionNoParamHasResult = new HashMap<>();
        mFunctionNoParamNoResult = new HashMap<>();
    }

    public static FunctionManager getInstance() {
        if (instance == null) {
            instance = new FunctionManager();
        }
        return instance;
    }

    //==============================================================================================将需要存储的方法添加

    //添加没有返回值也没有参数的方法
    public void addFunction(FunctionNoParamNoResult function) {
        mFunctionNoParamNoResult.put(function.functionName, function);
    }

    //执行没有返回值也没有参数的方法
    public void invokeFunction(String functionName) {
        if (TextUtils.isEmpty(functionName)) {
            return;
        }
        if (mFunctionNoParamNoResult != null) {
            FunctionNoParamNoResult f = mFunctionNoParamNoResult.get(functionName);
            if (f != null) {
                f.function();
            } else {
                Log.e("FunctionManager", "没有找到该方法！");
            }
        }
    }

    //添加有返回值没有参数的方法
    public void addFunction(FunctionNoParamHasResult function) {
        mFunctionNoParamHasResult.put(function.functionName, function);
    }

    //执行有返回值没有参数的方法
    public <T> T invokeFunction(String functionName, Class<T> t) {
        if (TextUtils.isEmpty(functionName)) {
            return null;
        }
        if (mFunctionNoParamHasResult != null) {
            FunctionNoParamHasResult f = mFunctionNoParamHasResult.get(functionName);
            if (f != null) {
                if (t != null) {
                    return t.cast(f.function());
                }
            } else {
                Log.e("FunctionManager", "没有找到该方法！");

            }
        }
        return null;
    }

    //添加没有返回值有参数的方法
    public void addFunction(FunctionHasParamNoResult function){
        mFunctionHasParamNoResult.put(function.functionName,function);
    }

    //执行没有返回值有参数的方法
    public <P> void invokeFunction(String functionName,P p){
        if(TextUtils.isEmpty(functionName)){
            return ;
        }
        if(mFunctionHasParamNoResult!=null){
            FunctionHasParamNoResult f=mFunctionHasParamNoResult.get(functionName);
            if(p!=null){
                f.funciotn(p);
            }else{
                Log.e("FunctionManager", "没有找到该方法！");
            }
        }
    }

    //添加有返回值有参数的方法
    public void addFunction(FunctionHasParamHasResult function){
        mFunctionHasParamHasResult.put(function.functionName,function);
    }

    //执行有返回值有参数的方法
    public <T,P> T invokeFunction(String functionName,P p ,Class<T> t){
        if (TextUtils.isEmpty(functionName)) {
            return null;
        }
        if (mFunctionHasParamHasResult != null) {
            FunctionHasParamHasResult f = mFunctionHasParamHasResult.get(functionName);
            if (f != null) {
                if (t != null) {
                    return t.cast(f.function(p));
                }
            } else {
                Log.e("FunctionManager", "没有找到该方法！");

            }
        }
        return null;
    }

}
