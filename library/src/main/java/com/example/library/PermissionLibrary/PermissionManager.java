package com.example.library.PermissionLibrary;

import android.app.Activity;

import com.example.library.PermissionLibrary.Listener.RequestPermission;

public final class PermissionManager {

    public static void request(Activity activity, String[] permissions) {
        String className = activity.getClass().getName() + "$Permissions";

        try {
            Class<?> clazz = Class.forName(className);
            RequestPermission requestPermission = (RequestPermission) clazz.newInstance();
            requestPermission.requestPermission(activity, permissions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onRequestPermissionResults(Activity activity, int requestCode, int[] grantResults) {
        String className = activity.getClass().getName() + "$Permissions";

        try {
            Class<?> clazz = Class.forName(className);
            RequestPermission requestPermission = (RequestPermission) clazz.newInstance();
            requestPermission.onRequestPermissionResults(activity, requestCode, grantResults);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
