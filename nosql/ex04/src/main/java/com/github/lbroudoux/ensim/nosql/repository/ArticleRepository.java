package com.github.lbroudoux.ensim.nosql.repository;

import java.util.List;

import com.github.lbroudoux.ensim.nosql.domain.Article;
/**
 * A repository interface for Article domain objects.
 * @author laurent
 */
public interface ArticleRepository{

   /** Create or update given article. */
   public Article save(Article article);
   
   /** Retrieve all articles written by authors. */
   public List<Article> findByAuthor(String author);
}
