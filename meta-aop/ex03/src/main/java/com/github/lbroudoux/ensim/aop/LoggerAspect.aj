package com.github.lbroudoux.ensim.aop;

/**
 * A simple aspect with around advice that logs method invokation and passed time.
 * @author laurent
 */
public aspect LoggerAspect{

   pointcut logger() : execution(public * *.*(..)) 
         && target(com.github.lbroudoux.ensim.aop.HelloWorld);
   
   Object around() : logger(){
      // Log and register start time.
      long startTime = System.nanoTime();
      System.err.println("Invoking join point " + thisJoinPoint.toString());
      
      Object result = proceed();
      
      // Log duration.
      long duration = System.nanoTime() - startTime;
      System.err.println("duration: " + duration + " ns");
      
      return result;
   }
}
