package com.example.studyandroid.Refactoring.Processor;

import android.util.Log;

import com.example.studyandroid.Refactoring.CallBack.ICallBack;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpProcessor implements IHttpProcessor {
    @Override
    public void post(final String url, final Map<String, Object> params, final ICallBack callBack) {
        OkHttpClient okHttpClient=new OkHttpClient();
        final RequestBody requestBody = appendBody(params);
        final Request request=new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(">>>>>>",params.toString());
                Log.e("OKHttpProcessor >>>>","Fail!");
                callBack.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("OKHttpProcessor >>>>","Success!");
                callBack.onSuccess(response.body().string());
            }
        });
    }

    private RequestBody appendBody(Map<String, Object> params) {
        FormBody.Builder body=new FormBody.Builder();
        if(params==null||params.isEmpty()){
            return body.build();
        }
        for(Map.Entry<String,Object> entry:params.entrySet()){
            body.add(entry.getKey(),entry.getValue().toString());
        }
        return body.build();
    }
}
