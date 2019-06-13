package com.example.library.PermissionLibrary.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionUtils {

    private PermissionUtils() {
    }

    /**
     * 检查所有权限是否已经被允许
     *
     * @param grantResults 授权结果
     * @return 如果所有权限都被允许返回True, 否则返回False
     */
    public static boolean verifyPermission(int... grantResults) {
        if (grantResults.length == 0) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 查询权限组是否都被授权
     *
     * @param context     上下文
     * @param permissions 权限集合
     * @return 如果所有权限已允许返回True, 否则返回False
     */
    public static boolean hasSelfPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (!hasSelfPermission(context, permission)) return false;
        }
        return true;
    }

    /**
     * 查询是否授权某权限
     *
     * @param context    上下文
     * @param permission 权限
     * @return 如果权限通过申请则返回True, 否则返回False。（android 6.0一下直接返回True）
     */
    private static boolean hasSelfPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT < 23) return true;
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 检查权限组中是否有点击了"不在询问"的权限
     * <p></p>
     * 第一次打开app————false
     * 上次弹出权限请求是点击了"拒绝"，但没有勾选"不在询问"————true
     * 上次弹出权限请求是点击了"拒绝"，勾选"不在询问"————false
     * 点击了拒绝，但没有勾选"不在询问"————ture
     * 点击了拒绝，勾选"不在询问"————false
     *
     * @param activity    上下文
     * @param permissions 被拒绝的权限组
     * @return 如果有任一"不在询问"的权限返回True,否则返回False
     */
    public static boolean shouldShowRequestPermissionRationable(Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }
}
