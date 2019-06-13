package com.example.library.IOCLibrary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)                 //该注解作用在 [类] 之上
@Retention(RetentionPolicy.RUNTIME)       //JVM在运行时通过放射获取注解的值
public @interface ContentView {

    int value();
}
