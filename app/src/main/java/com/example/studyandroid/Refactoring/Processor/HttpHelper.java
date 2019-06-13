package com.example.studyandroid.Refactoring.Processor;


import com.example.studyandroid.Refactoring.CallBack.ICallBack;

import java.net.URLEncoder;
import java.util.Map;

//该类相当于房产公司业务员，负责替房产公司卖方。
public class HttpHelper implements IHttpProcessor {

    //单例模式
    private static HttpHelper instance;

    private HttpHelper() {
    }

    public static HttpHelper getInstance() {
        if (instance == null) {
            synchronized (HttpHelper.class) {
                if (instance == null) {
                    instance = new HttpHelper();
                }
            }
        }

        return instance;
    }

    //持有一个需要卖方的人。本例中，相当于持有第三方网络框架
    private static IHttpProcessor mIHttpProcessor;
    public static void init(IHttpProcessor httpProcessor){
        mIHttpProcessor=httpProcessor;
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
//        String fianlUrl=appendParams(url,params);
//        mIHttpProcessor.post(fianlUrl,params,callBack);
        mIHttpProcessor.post(url,params,callBack);
    }

    public static String appendParams(String url,Map<String,Object> params){
        if(params==null||params.isEmpty()){
            return url;
        }
        StringBuilder urlBuilder=new StringBuilder(url);
        if(urlBuilder.indexOf("?")<=0){
            urlBuilder.append("?");
        }else {
            if(!urlBuilder.toString().endsWith("?")){
                urlBuilder.append("&");
            }
        }
        for (Map.Entry<String,Object> entry:params.entrySet()){
            urlBuilder.append("&"+entry.getKey()).append("=").append(encode(entry.getValue().toString()));
        }
        return urlBuilder.toString();
    }

    private static String encode(String str){
        try{
            return URLEncoder.encode(str,"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
