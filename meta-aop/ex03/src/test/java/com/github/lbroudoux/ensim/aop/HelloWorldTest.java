package com.github.lbroudoux.ensim.aop;

import org.junit.Test;
/**
 * 
 * @author laurent
 */
public class HelloWorldTest{

   @Test
   public void testSayHelloTo(){
      HelloWorld hw = new HelloWorld();
      hw.sayHelloTo("Laurent");
   }
}
