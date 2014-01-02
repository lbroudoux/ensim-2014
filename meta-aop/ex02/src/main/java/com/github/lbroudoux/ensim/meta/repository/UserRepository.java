package com.github.lbroudoux.ensim.meta.repository;

import java.util.List;

import com.github.lbroudoux.ensim.meta.annotation.Cacheable;
/**
 * Repostiory interface.
 * @author laurent
 */
public interface UserRepository{

   /** Retrieve all users from repository. */
   public List<User> findAll();
   
   /** Retrieve only user having specified username. Null if none. */
   @Cacheable
   public User findByUsername(String username);
   
   /** Retrieve all users having specified lastname. */
   @Cacheable
   public List<User> findByLastname(String lastname);
}
