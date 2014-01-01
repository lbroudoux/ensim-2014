package com.github.lbroudoux.ensim.meta.repository;

import java.util.List;
/**
 * Repostiory interface.
 * @author laurent
 */
public interface UserRepository{

   /** Retrieve all users from repository. */
   public List<User> findAll();
   
   /** Retrieve only user having specified username. Null if none. */
   public User findByUsername(String username);
   
   /** Retrieve all users having specified lastname. */
   public List<User> findByLastname(String lastname);
}
