package com.github.lbroudoux.ensim.meta;

import java.lang.reflect.Proxy;
/**
 * Simple factory for demonstrating usage of Proxy features.
 * @author laurent
 */
public class HelloWorldFactory{

   /**
    * Create an return a new instance of HelloWorld.
    * @return An object implementing HelloWorld interface.
    */
   public static HelloWorld createHelloWorld(){
      LoggerInvocationHandler handler = new LoggerInvocationHandler(new HelloWorldImpl());
      return (HelloWorld)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
            new Class[]{HelloWorld.class}, handler);
   }
}
