package com.github.lbroudoux.ensim.meta.repository;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Very simplistic invocation handler implementing dynamic finders features.
 * Only findAll() and findBy() method with 1 arguments are implemented.
 * @author laurent
 */
public class RepositoryInvocationHandler implements InvocationHandler{

   /** */
   public static final String FINDER_PREFIX = "findBy";
   
   /** The wrapped accessed objects (imagine a database connection instead...). */
   public List<?> objects;
   
   public RepositoryInvocationHandler(List<?> objects){
      this.objects = objects;
   }
   
   @Override
   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
      // Check simplest case first, findAll...
      if ("findAll".equals(method.getName())){
         return objects;
      }
      // Then look if we've got a finder...
      if (method.getName().startsWith(FINDER_PREFIX)){
         // We should have one argument corresponding to criterion value.
         assert args.length == 1;
         String criterion = extractCriterion(method.getName());
         List<?> results = selectMatchingObjects(criterion, args[0]);
         
         // If we do not expect a collection as return...
         if (!Collection.class.isAssignableFrom(method.getReturnType())){
            if (results.size() > 1){
               throw new Exception("More than one result found but expecting only one...");
            } else if (results.isEmpty()){
               return null;
            } else {
               return results.get(0);
            }
         }
         return results;
      }
      
      return null;
   }
   
   /** Extract criterion from finder name. */
   private String extractCriterion(String methodName){
      return methodName.substring(FINDER_PREFIX.length());
   }
   
   /** Select only the object corresponding to given criterion. */
   private List<Object> selectMatchingObjects(String criterion, Object value){
      // Imagin here having to compose a specific SQL where clause in case
      // of wrapping a database connection.
      List<Object> results = new ArrayList<Object>();
      String getterName = "get" + criterion;
      
      for (Object object : objects){
         try{
            Method getter = object.getClass().getDeclaredMethod(getterName);
            Object objValue = getter.invoke(object);
            if (value.equals(objValue)){
               results.add(object);
            }
         } catch (NoSuchMethodException nsme){
            System.err.println("NoSuchMethodException: " + nsme);
         } catch (InvocationTargetException ite){
            System.err.println("InvocationTargetException: " + ite);
         } catch (IllegalAccessException iae){
            System.err.println("IllegalAccessException: " + iae);
         }
      }
      return results;
   }
}
