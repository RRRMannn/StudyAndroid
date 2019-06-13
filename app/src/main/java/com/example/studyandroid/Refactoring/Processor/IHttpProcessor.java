package com.example.studyandroid.Refactoring.Processor;

import com.example.studyandroid.Refactoring.CallBack.ICallBack;

import java.util.Map;

//拥有卖方的能力的类的抽象。对应网络请求，具有访问网络的能力。
public interface IHttpProcessor {


    void post(String url, Map<String,Object> params, ICallBack callBack);

}
