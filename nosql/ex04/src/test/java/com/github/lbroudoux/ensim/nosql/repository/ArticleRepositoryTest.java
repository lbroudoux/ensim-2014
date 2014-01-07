package com.github.lbroudoux.ensim.nosql.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.github.lbroudoux.ensim.nosql.domain.Article;
/**
 * This is a test case for ArticleRepository class.
 * @author laurent
 */
public class ArticleRepositoryTest{

   @Test
   public void testSave() throws Exception{
      ArticleRepository repository = ArticleRepositoryFactory.createArticleRepository();
      
      // Save a new article.
      Article article = repository.save(new Article("Title", "Body", "Author"));
      
      // Ensure a id has been set.
      assertNotNull(article.getId());
      String oldId = article.getId();
      
      // Update given article and ensure id has been kept.
      article.setBody("My new body");
      article = repository.save(article);
      
      assertEquals(oldId, article.getId());
   }
   
   @Test
   public void testFindByAuthor() throws Exception{
      ArticleRepository repository = ArticleRepositoryFactory.createArticleRepository();
      
      List<Article> articles = repository.findByAuthor("Laurent");
      System.err.println("Found " + articles.size() + " articles authored by Laurent");
   }
}
