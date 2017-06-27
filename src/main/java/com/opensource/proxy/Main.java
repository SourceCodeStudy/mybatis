package com.opensource.proxy;

/**
 * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 *
 * @author YX_user
 * @version 1.0.0 2017/6/27
 * @name com.opensource.proxy
 */
public class Main {
    public static void main(String[] args) {
        Person person = (Person) new  HNPerson().newProxyIntance(new ZLZPerson());
        person.buyTicket();
    }
}
