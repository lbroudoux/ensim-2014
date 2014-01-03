package com.github.lbroudoux.ensim.aop.domain;

/**
 * Sample bean class for demonstrating dynamic repositories. 
 * @author laurent
 */
public class User{

   private String username;
   
   private String firstname;
   
   private String lastname;

   /**
    * User base constructor.
    * @param username Its username (should be unique)
    * @param firstname Its first name
    * @param lastname Its last name
    */
   public User(String username, String firstname, String lastname){
      this.username = username;
      this.firstname = firstname;
      this.lastname = lastname;
   }
   
   public String getUsername() {
      return username;
   }
   public void setUsername(String username) {
      this.username = username;
   }

   public String getFirstname() {
      return firstname;
   }
   public void setFirstname(String firstname) {
      this.firstname = firstname;
   }

   public String getLastname() {
      return lastname;
   }
   public void setLastname(String lastname) {
      this.lastname = lastname;
   }
}
