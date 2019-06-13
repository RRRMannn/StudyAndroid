package com.example.studyandroid.Refactoring.Processor;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.studyandroid.Refactoring.CallBack.ICallBack;

import java.util.HashMap;
import java.util.Map;

public class VolleyProcessor implements IHttpProcessor {

    private static RequestQueue mQueue = null;

    public VolleyProcessor(Context context){
        mQueue= Volley.newRequestQueue(context);
    }

    @Override
    public void post(String url, final Map<String, Object> params, final ICallBack callBack) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("VolleyProcessor >>>>","Success!");
                callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyProcessor >>>>","Fail!");
                callBack.onFailure();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                for(Map.Entry entry:params.entrySet()){
                    map.put(entry.getKey().toString(),entry.getValue().toString());
                }
                return map;
            }
        };
        mQueue.add(stringRequest);
    }

}
