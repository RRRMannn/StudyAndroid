package com.example.studyandroid.Refactoring.Processor;

import android.util.Log;

import com.example.studyandroid.Refactoring.CallBack.ICallBack;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

public class XUtilsProcessor implements IHttpProcessor {
    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {
        RequestParams requestParams=new RequestParams(url);
        for(Map.Entry<String,Object> entry:params.entrySet()){
            requestParams.addBodyParameter(entry.getKey(),entry.getValue().toString());
        }
        x.http().request(HttpMethod.POST,requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
                Log.e("OKHttpProcessor >>>>","Success!");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onFailure();
                Log.e("OKHttpProcessor >>>>","Fail!");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("OKHttpProcessor >>>>","Cancel!");
            }

            @Override
            public void onFinished() {
                Log.e("OKHttpProcessor >>>>","Finish!");
            }
        });
    }
}
