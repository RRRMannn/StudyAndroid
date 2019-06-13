package com.example.studyandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.studyandroid.UniversalInterface.FunctionManager;
import com.example.studyandroid.bean.User;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.sa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FunctionManager.getInstance().invokeFunction("NoParamNoResult");
//                User user = FunctionManager.getInstance().invokeFunction("NoParamHasResult", User.class);
//                Log.e("SecondActivity", "user from MainActivity "+user.getName()+user.getPassword());
                User user=new User("昵称","密码");
                FunctionManager.getInstance().invokeFunction("HasParamNoResult",user);
//                User user1=new User("林爸爸","088088088");
//                User user2=FunctionManager.getInstance().invokeFunction("HasParamHasResult",user1,User.class);
//                Log.e("SecondActivity","打印传回来的User对象： "+user2.getName()+user2.getPassword());

            }
        });

    }
}
