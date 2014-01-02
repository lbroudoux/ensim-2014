package com.github.lbroudoux.ensim.meta.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Test case for UserRepository sample.
 * @author laurent
 */
public class UserRepositoryTest{

   List<User> users = new ArrayList<User>();
   
   @Before
   public void setUp(){
      // Create some demonstration users for later assertions.
      users.add(new User("lbroudoux", "Laurent", "Broudoux"));
      users.add(new User("abroudoux", "Arthur", "Broudoux"));
      users.add(new User("fabroudoux", "Fanette", "Broudoux"));
      users.add(new User("febroudoux", "Felix", "Broudoux"));
      users.add(new User("jdoe", "John", "Doe"));
      users.add(new User("fbar", "Foo", "Bar"));
   }
   
   @Test
   public void testFinders(){
      UserRepository repository = UserRepositoryFactory.createUserRepository(users);
      
      // Expect 4 "Broudoux" users into repository.
      List<User> results = null;
      long start = System.nanoTime();
      results = repository.findByLastname("Broudoux");
      System.err.println("Duration of 1st call: " + (System.nanoTime() - start) + " ns");
      assertEquals(4, results.size());
      
      start = System.nanoTime();
      results = repository.findByLastname("Broudoux");
      System.err.println("Duration of 2nd call: " + (System.nanoTime() - start) + " ns");
      assertEquals(4, results.size());
      
      // Expect only one user having username "lbroudoux" into repository.
      User result = repository.findByUsername("lbroudoux");
      assertNotNull(result);
      
      // Expect no user having username "foobar" into repository.
      result = repository.findByUsername("foobar");
      assertNull(result);
   }
}
