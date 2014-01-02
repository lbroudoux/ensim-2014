package com.github.lbroudoux.ensim.meta.repository;

import java.lang.reflect.Proxy;
import java.util.List;

import com.github.lbroudoux.ensim.meta.annotation.CacheableInvocationHandler;
/**
 * Simple factory for demonstrating usage of proxy with a chained invocation handler.
 * @author laurent
 */
public class UserRepositoryFactory {

   /** 
    * Create an return a new instance of UserRepository wrapping given users
    * (imaging passing by a database connection instead....)
    * @return An object implementing HelloWorld interface.
    */
   public static UserRepository createUserRepository(List<User> users){
      RepositoryInvocationHandler handler = new RepositoryInvocationHandler(users);
      UserRepository ur = (UserRepository)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
            new Class[]{UserRepository.class}, handler);
      CacheableInvocationHandler cHandler = new CacheableInvocationHandler(ur);
      return (UserRepository)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
            new Class[]{UserRepository.class}, cHandler);
   }
}
