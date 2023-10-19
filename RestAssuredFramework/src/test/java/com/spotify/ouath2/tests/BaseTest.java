package com.spotify.ouath2.tests;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeMethod
    public void beforeMethod(Method method){
        System.out.println("STARTING TEST" + method.getName());
        System.out.println("THREAD ID:" + Thread.currentThread().getId());
    }
}
