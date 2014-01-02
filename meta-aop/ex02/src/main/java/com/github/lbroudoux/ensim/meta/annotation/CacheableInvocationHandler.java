package com.github.lbroudoux.ensim.meta.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Hashtable;
/**
 * An invocation handler that reacts on the presence of @Cacheable annotation.
 * Warning : this is the dumbest implementation ever ! Caches are not constrained
 * and may grow infinitely ! Do not use on any serious project.
 * @author laurent
 */
public class CacheableInvocationHandler implements InvocationHandler{

   private Object target;
   
   /** Dummy cache manager and caches ... Map of Map without constraints ! */
   private Hashtable<String, Hashtable<String, Object>> cacheHash = new Hashtable<String, Hashtable<String, Object>>();
   
   public CacheableInvocationHandler(Object target){
      this.target = target;
   }
   
   @Override
   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
      // Retrieve Cacheable annotation parameters. 
      Cacheable annotation = method.getAnnotation(Cacheable.class);
      
      if (annotation != null){
         // Build a new cache key and check previous result.
         String key = buildKeyFromInvokation(method.getName(), args);
         Object result = getCacheValue(annotation.value(), key);
         
         switch (annotation.command()){
            case PUT:
               if (result == null){
                  System.err.println("Result not present in cache, invoking method...");
                  result = method.invoke(target, args);
                  storeCacheValue(annotation.value(), key, result);
               }
               break;
            case EVICT:
               result = method.invoke(target, args);
               evictCacheValues(annotation.value());
               break;
            case NO_PUT:
               if (result == null){
                  result = method.invoke(target, args);
               }
               break;
         }
         return result;
      }
      return method.invoke(target, args);
   }
   
   /** Build a cache key from method name and invokation arguments. */
   private String buildKeyFromInvokation(String methodName, Object[] args){
      StringBuilder builder = new StringBuilder(methodName);
      for (Object arg : args){
         builder.append("-").append(arg.toString());
      }
      return builder.toString();
   }
   
   /** Retrieve a cached value using cache name and a key. Returns null if no value. */
   private Object getCacheValue(String cacheName, String key){
      Hashtable<String, Object> cache = cacheHash.get(cacheName);
      if (cache != null){
         Object value = cache.get(key);
         return value;
      }
      return null;
   }
   
   /** Store a value using specified key within designated cache. */
   private void storeCacheValue(String cacheName, String key, Object value){
      if (value != null){
         Hashtable<String, Object> cache = cacheHash.get(cacheName);
         // Create a new cache and store it if necessary.
         if (cache == null){
            cache = new Hashtable<String, Object>();
            cacheHash.put(cacheName, cache);
         }
         // Put value into cache.
         cache.put(key, value);
      }
   }
   
   /** Evict all the values within the specified cache. */
   private void evictCacheValues(String cacheName){
      Hashtable<String, Object> cache = cacheHash.get(cacheName);
      if (cache != null){
         cache.clear();
      }
   }
}
