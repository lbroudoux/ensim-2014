package com.github.lbroudoux.ensim.meta;

import org.junit.Test;
/**
 * Test case for HelloWorld sample.
 * @author laurent
 */
public class HelloWorldTest{

   @Test
   public void testSimple(){
      HelloWorld hw = new HelloWorldImpl();
      String greeting = hw.sayHelloTo("Laurent");
   }
   
   @Test
   public void testWithInvocationHandler(){
      HelloWorld hw = HelloWorldFactory.createHelloWorld();
      String greeting = hw.sayHelloTo("Laurent");
   }
}
