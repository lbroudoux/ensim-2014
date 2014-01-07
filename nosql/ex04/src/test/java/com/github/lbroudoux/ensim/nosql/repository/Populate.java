package com.github.lbroudoux.ensim.nosql.repository;

import org.junit.Test;

import com.github.lbroudoux.ensim.nosql.domain.Article;
/**
 * This is a utility class implemented as a JUnit test for convenience that helps populate MongoDB.
 * @author laurent
 */
public class Populate{

   private final String[] authors = new String[]{"Laurent", "Arthur", "Fanette", "Felix"};
   
   @Test
   public void populateRepository() throws Exception{
      ArticleRepository repository = ArticleRepositoryFactory.createArticleRepository();
      
      for (int i=0; i<20; i++){
         repository.save(new Article("Article " + i, "Body of article " + i, authors[i % 4], i % 6));
      }
   }
}
