package com.example.library.IOCLibrary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)        //作用在注解之上
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {

    //setOnClickListener
    String listenerSetter();

    //View.OnClickListener
    Class<?> listenerType();

    //onClick
    String callBackListener();

}
