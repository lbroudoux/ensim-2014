package com.github.lbroudoux.ensim.nosql.domain;

/**
 * Sample Article domain object.
 * @author laurent
 */
public class Article{

   private String id;
   private String title;
   private String body;
   private String author;
   private int note = 0;
   

   public Article(String title, String body, String author){
      this.title = title;
      this.body = body;
      this.author = author;
   }
   
   public Article(String title, String body, String author, int note){
      this.title = title;
      this.body = body;
      this.author = author;
      this.note = note;
   }
   
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }

   public String getBody() {
      return body;
   }
   public void setBody(String body) {
      this.body = body;
   }

   public String getAuthor() {
      return author;
   }
   public void setAuthor(String author) {
      this.author = author;
   }

   public int getNote() {
      return note;
   }
   public void setNote(int note) {
      this.note = note;
   }
}
