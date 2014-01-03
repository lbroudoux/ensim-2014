package com.github.lbroudoux.ensim.aop.domain;

import java.io.Serializable;
/**
 * 
 */
public aspect SerializableAspect {

   declare parents : com.github.lbroudoux.ensim.aop.domain..* implements Serializable;
   
   public String User.toXml(){
      StringBuilder builder = new StringBuilder();
      builder.append("<user>");
      builder.append("<username>").append(getUsername()).append("</username>");
      builder.append("<firstname>").append(getFirstname()).append("</firstname>");
      builder.append("<lastname>").append(getLastname()).append("</lastname>");
      builder.append("</user>");
      return builder.toString();
   }
}
