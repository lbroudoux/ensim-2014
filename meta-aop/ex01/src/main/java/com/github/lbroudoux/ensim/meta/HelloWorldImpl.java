package com.github.lbroudoux.ensim.meta;

/**
 * Dummy class that says hello.
 * @author laurent
 */
public class HelloWorldImpl implements HelloWorld{

   /**
    * Say "hello" to the name passed in arg.
    * @param name The name to greet
    * @return A greeting saying hello
    */
   public String sayHelloTo(String name){
      return "Hello World to " + name + " !";
   }
}
