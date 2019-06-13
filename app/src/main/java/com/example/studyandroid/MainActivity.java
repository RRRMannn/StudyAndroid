package com.example.studyandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.studyandroid.IOC.ThirdActivity;
import com.example.studyandroid.Refactoring.FourthActivity;
import com.example.studyandroid.UniversalInterface.FunctionHasParamHasResult;
import com.example.studyandroid.UniversalInterface.FunctionHasParamNoResult;
import com.example.studyandroid.UniversalInterface.FunctionManager;
import com.example.studyandroid.UniversalInterface.FunctionNoParamHasResult;
import com.example.studyandroid.UniversalInterface.FunctionNoParamNoResult;
import com.example.studyandroid.bean.User;

import com.example.library.PermissionLibrary.Listener.RequestPermission;
import com.example.library.PermissionLibrary.Listener.RequestPermission;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FunctionManager.getInstance().addFunction(new FunctionNoParamNoResult("NoParamNoResult") {
            @Override
            public void function() {
                Log.e("MainActivity","Hello form SecondActivity");
            }
        });

        FunctionManager.getInstance().addFunction(new FunctionNoParamHasResult<User>("NoParamHasResult") {
            @Override
            public User function() {
                User user =new User("林爸爸","088088088");
                return user;
            }
        });

        FunctionManager.getInstance().addFunction(new FunctionHasParamNoResult<User>("HasParamNoResult") {
            @Override
            public void funciotn(User user) {
                Log.e("MainActivity","打印传过来的User对象："+user.getName()+user.getPassword());
            }

        });

        FunctionManager.getInstance().addFunction(new FunctionHasParamHasResult<User,User>("HasParamHasResult") {
            @Override
            public User function(User o) {
                Log.e("MainActivity","打印传过来的User对象： "+o.getName()+o.getPassword());
                User user=new User("爸爸林","880880880");
                return user;
            }
        });

        /*万能接口测试*/
//        Intent intent=new Intent();
//        intent.setClass(this,SecondActivity.class);
//        startActivity(intent);

        /*注解开发，反射技术测试*/
//        Intent intent=new Intent();
//        intent.setClass(this, ThirdActivity.class);
//        startActivity(intent);


        Intent intent=new Intent();
        intent.setClass(this, FourthActivity.class);
        startActivity(intent);
    }

//    public interface Listener{
//        public void aa(String str);

        //      方法名
        //      方法体
        //      //      返回值
        //      //      参数
//    }
}
