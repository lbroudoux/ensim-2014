package com.github.lbroudoux.ensim.meta.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Simple method annotation to indicate method result should be cached.
 * @author laurent
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable{
   
   String value() default "default";
   
   CacheCommand command() default CacheCommand.PUT;
}
