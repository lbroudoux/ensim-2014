package com.github.lbroudoux.ensim.nosql.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import com.github.lbroudoux.ensim.nosql.domain.Article;
/**
 * This is an implementation of ArticleRepository that uses MongoDB as a storage backend.
 * @author laurent
 */
public class ArticleRepositoryMongoImpl implements ArticleRepository{

   private String dbName;
   
   private MongoClient client;
   
   /**
    * Create a ArticleRepositoryMongoImpl with connection information
    * @param dbName Name of the mongodb base to use
    * @param host Host of mongodb
    * @param port Port of mongodb service
    * @throws Exception if connection to mongo cannot be setup
    */
   public ArticleRepositoryMongoImpl(String dbName, String host, int port) throws Exception{
      this.dbName = dbName;
      this.client = new MongoClient(host, port);
   }
   
   /** Create or update given article. */
   public Article save(Article article){
      // Retrieve DB and appropriate collection.
      DB db = client.getDB(dbName);
      DBCollection articles = db.getCollection("articles");
      
      // Prepare an object containing data to save.
      BasicDBObject object = new BasicDBObject("title", article.getTitle())
            .append("body", article.getBody())
            .append("author", article.getAuthor())
            .append("note", article.getNote());
      
      if (article.getId() != null){
         // Update only article having specified id.
         articles.update(new BasicDBObject("_id", new ObjectId(article.getId())), object);
      } else {
         // Insert and complete article id.
         articles.insert(object);
         article.setId(object.getObjectId("_id").toString());
      }
      
      return article;
   }
   
   /** Retrieve all articles written by authors. */
   public List<Article> findByAuthor(String author){
      // Retrieve DB and appropriate collection.
      DB db = client.getDB(dbName);
      DBCollection articles = db.getCollection("articles");
      
      List<Article> result = new ArrayList<Article>();
      DBCursor cursor = articles.find(new BasicDBObject("author", author));
      while (cursor.hasNext()){
         DBObject object = cursor.next();
         if (object != null){
            result.add(buildArticle(object));
         }
      }
      cursor.close();
      
      return result;
   }
   
   private Article buildArticle(DBObject object){
      Article article = new Article((String)object.get("title"), (String)object.get("body"), (String)object.get("author"));
      article.setId(((ObjectId)object.get("_id")).toString());
      if (object.containsField("note")){
         article.setNote((Integer)object.get("note"));
      }
      return article;
   }
}
