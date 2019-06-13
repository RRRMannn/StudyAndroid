package com.example.studyandroid;

import android.support.annotation.NonNull;
import com.example.library.PermissionLibrary.Listener.RequestPermission;

public class test implements RequestPermission<MainActivity> {
    @Override
    public void requestPermission(MainActivity target, String[] permissions) {

    }

    @Override
    public void onRequestPermissionResults(MainActivity target, int requestCode, @NonNull int[] grantResult) {

    }
}
