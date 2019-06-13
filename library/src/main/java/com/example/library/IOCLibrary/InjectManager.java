package com.example.library.IOCLibrary;

import android.app.Activity;
import android.view.View;

import com.example.library.IOCLibrary.annotation.ContentView;
import com.example.library.IOCLibrary.annotation.EventBase;
import com.example.library.IOCLibrary.annotation.InjectView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjectManager {
    public static void inject(Activity activity){
        //布局的注入
        injectLayout(activity);

        //控件的注入
        injectViews(activity);

        //事件的注入
        try {
            injectEvent(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //布局的注入==========================================================================================================================
    private static void injectLayout(Activity activity) {

        //获取类
        Class<? extends Activity> clazz = activity.getClass();

        //获取这个类上的注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);

        if (contentView != null) {
            int layoutId = contentView.value();

            //执行方法    setContentView(R.layout.activity);

            //第一种方法
            //activity.setContentView(layoutId);

            //第二种方法
            try {
                Method method = clazz.getMethod("setContentView", int.class);

                //执行方法
                method.invoke(activity, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //控件的注入==========================================================================================================================
    private static void injectViews(Activity activity) {

        //获取类
        Class<? extends Activity> clazz = activity.getClass();

        //获取类中所有属性的值
        Field[] fields = clazz.getDeclaredFields();

        //遍历所有属性找出需要注入的属性
        for (Field field : fields) {

            //获取每个属性上的注解
            InjectView injectView = field.getAnnotation(InjectView.class);
            if (injectView != null) {

                //获取注解的值
                int layoutId = injectView.value();

                //执行findViewById(xxxid)
                try {
                    //第一种方式
                    //Object view =activity.findViewById(layoutId)

                    //第二种方式
                    Method method = clazz.getMethod("findViewById", int.class);
                    Object view = method.invoke(activity, layoutId);

                    //设置私有属性访问权
                    field.setAccessible(true);

                    //赋值给相应需要注入的View
                    field.set(activity, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //事件的注入==========================================================================================================================
    private static void injectEvent(final Activity activity) throws Exception {
        //获取类
        final Class<? extends Activity> clazz = activity.getClass();

        //获取每个方法
        Method[] methods = clazz.getDeclaredMethods();

        //循环遍历方法
        for (final Method method : methods) {
            //获取方法注解
            Annotation[] annotations = method.getAnnotations();

            //遍历每个方法的多个注解
            for (Annotation annotation:annotations){

                //获取OnClick注解上的注解类型
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if(annotationType!=null){
                    //通过EventBase获取三个重要的规律
                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);

                    //事件的三个规律
                    String listenerSetter = eventBase.listenerSetter();
                    Class<?> listenerType = eventBase.listenerType();
                    String callBackListener = eventBase.callBackListener();

                    //注解的值
                    Method valueMethod = annotationType.getDeclaredMethod("value");
                    int[] viewIds=(int[])valueMethod.invoke(annotation);

                    //配置拦截信息
                    ListenerInvocationHandler handler=new ListenerInvocationHandler(activity);
                    handler.addMethodToMap(callBackListener,method);

                    //打包之后用代理处理后续工作
                    Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, handler);

                    //R.id.ta   R.id.btn
                    for(int viewId:viewIds){
                        //控件赋值过程
                        View view = activity.findViewById(viewId);
                        //获取方法
                        Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                        //执行方法
                        setter.invoke(view,listener);
                    }
                }
            }
        }
    }


}
