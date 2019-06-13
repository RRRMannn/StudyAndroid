package com.example.studyandroid.Refactoring;

import android.os.Bundle;
import android.util.Log;

import com.example.library.IOCLibrary.annotation.ContentView;
import com.example.studyandroid.IOC.BaseActivity;
import com.example.studyandroid.R;
import com.example.studyandroid.Refactoring.CallBack.HttpCallBack;
import com.example.studyandroid.Refactoring.Processor.HttpHelper;
import com.example.studyandroid.bean.Blog;

import java.util.HashMap;
import java.util.Map;


@ContentView(R.layout.activity_fourth)
public class FourthActivity extends BaseActivity {

    String url="http://120.78.148.54/servlet/androidGetInfoServlet";

//    @InjectView(R.id.wv)
//    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        webView.loadUrl("http://www.baidu.com");

        Map<String,Object> params=new HashMap<>();
        params.put("type","blog");

        HttpHelper.getInstance().post(url,params, new HttpCallBack<Blog>() {
            @Override
            public void onSuccess(Blog objResult) {
                Log.e("FourthActivity >>>>>",objResult.getTitle()+"___________"+objResult.getContext());
            }

        });

    }
}
