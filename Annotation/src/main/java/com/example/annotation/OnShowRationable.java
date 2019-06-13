package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)       //在源码和Class文件中都存在，但是在运行时不存在。编译期注解方式
public @interface OnShowRationable {
}
