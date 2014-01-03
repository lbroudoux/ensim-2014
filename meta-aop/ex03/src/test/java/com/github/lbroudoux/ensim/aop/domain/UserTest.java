package com.github.lbroudoux.ensim.aop.domain;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Test;
/**
 * Test case for User domain class.
 * @author laurent
 */
public class UserTest{

   @Test
   public void testSerializable(){
      User user = new User("lbroudoux", "Laurent", "Broudoux");
      assertTrue(user instanceof Serializable);
   }
   
   @Test
   public void testToXml(){
      User user = new User("lbroudoux", "Laurent", "Broudoux");
      String result = user.toXml();
      assertTrue(result.contains("<user>"));
      assertTrue(result.contains("<username>lbroudoux</username>"));
      assertTrue(result.contains("<firstname>Laurent</firstname>"));
      assertTrue(result.contains("<lastname>Broudoux</lastname>"));
      assertTrue(result.contains("</user>"));
   }
}
