package com.github.lbroudoux.ensim.meta;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * A simple invocation handler that logs method invokation and passed time. 
 * @author laurent
 */
public class LoggerInvocationHandler implements InvocationHandler{

   private Object target;
   
   public LoggerInvocationHandler(Object target){
      this.target = target; 
   }
   
   @Override
   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
      // Log and register start time.
      long startTime = System.nanoTime();
      System.err.println("LoggerInvocationHandler: invoking " + method.getName());
      
      // Invoke method on target object and store result.
      Object result = method.invoke(target, args);
      
      // Log duration.
      long duration = System.nanoTime() - startTime;
      System.err.println("duration: " + duration + " ns");
      
      return result;
   }
}
