package com.opensource.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 *
 * @author YX_user
 * @version 1.0.0 2017/6/27
 * @name com.opensource.proxy
 */
public class HNPerson implements InvocationHandler{
    private Person person ;
    public Object newProxyIntance(Person person){
        this.person = person;
        return Proxy.newProxyInstance(HNPerson.class.getClassLoader(),person.getClass().getInterfaces(),this);
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("HNPerson Start");
        //method.invoke(person,args);
        person.buyTicket();
        System.out.println("HNPerson End");
        return null;
    }
}
