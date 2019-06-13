package com.example.library.PermissionLibrary.Listener;

import android.support.annotation.NonNull;

public interface RequestPermission<T> {
    //请求权限组
    void requestPermission(T target,String[] permissions);

    //授权返回结果
    void onRequestPermissionResults(T target,int requestCode,@NonNull int[] grantResult);
}

