package com.github.lbroudoux.ensim.nosql.repository;

/**
 * A factory allowing to retrieve repository implementations.
 * @author laurent
 */
public class ArticleRepositoryFactory{

   /** Create an ArticleRepository for dealing with domain objects. */
   public static ArticleRepository createArticleRepository() throws Exception{
      return new ArticleRepositoryMongoImpl("ensim", "localhost", 27017);
   }
}
