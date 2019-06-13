package com.example.studyandroid.Refactoring.CallBack;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpCallBack<Result> implements ICallBack {
    @Override
    public void onSuccess(String result) {
        //将网络访问框架得到的数据转换成Json对象
        Gson gson=new Gson();
        //获取HttpCallBack后面的泛型
        Class<?> clazz=analysisClassInfo(this);

        JsonParser jsonParser=new JsonParser();
        JsonArray jsonElements=jsonParser.parse(result).getAsJsonArray();

        for (JsonElement jsonElement : jsonElements) {
            Log.e("HttpCallBack >>>>>>", String.valueOf(jsonElement));
            Result objResult= (Result) gson.fromJson(jsonElement, clazz);
            onSuccess(objResult);
        }
    }

    public abstract void onSuccess(Result objResult);

    private Class<?> analysisClassInfo(Object obj) {
        //相当于可以得到参数化类型，类型变量，基本类型
        Type genType=obj.getClass().getGenericSuperclass();
        Type[] actualType =((ParameterizedType)genType).getActualTypeArguments();
        return (Class<?>) actualType[0];
    }

    @Override
    public void onFailure() {
        Log.e("HttpCallBack >>>>>>","Fail!");
    }
}
