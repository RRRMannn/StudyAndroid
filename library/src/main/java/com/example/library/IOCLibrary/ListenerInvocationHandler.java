package com.example.library.IOCLibrary;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ListenerInvocationHandler implements InvocationHandler {

    //需要拦截的对象
    private Object targe;

    //需要拦截的方法集合
    private HashMap<String,Method> methodMap=new HashMap<>();


    public ListenerInvocationHandler(Object targe) {
        this.targe = targe;
    }

    /**
     * 将需要拦截的方法加入集合
     * @param methodName    需要拦截的方法，例如：OnClick()
     * @param method    执行自定义的方法，例如：show()
     */
    public void addMethodToMap(String methodName,Method method){
        methodMap.put(methodName,method);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(targe!=null){
            //获取需要拦截的方法名
            String methodName=method.getName();

            //将被拦截的方法取代为自定义方法
            method=methodMap.get(methodName);       //从集合中判断是否拦截
            if(method!=null){       //确实找到了需要拦截，才执行自定义方法
                if(method.getGenericParameterTypes().length==0)return method.invoke(targe);
                return method.invoke(targe,args);
            }
        }
        return null;
    }
}
