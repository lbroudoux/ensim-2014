package com.github.lbroudoux.ensim.nosql.repository;

import org.junit.Test;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;
/**
 * This is a test for experimenting with MongoDB MapReduce features.
 * @author laurent
 */
public class MongoMapReduceTest{

   @Test
   public void testMapReduce() throws Exception{
      MongoClient client = new MongoClient("localhost", 27017);
      DBCollection articles = client.getDB("ensim").getCollection("articles");
      
      // Write map function as Javascript.
      String map = "function() { " +
            "    emit('note', this.note);" +
            "}";
      // Write reduc function as Javascript.
      String reduce = "function(key, values) { " +
            "    return Array.sum(values) / values.length; " +
            "}";
      
      // Execute map/reduce job without finalize function and filter query.
      MapReduceOutput out = articles.mapReduce(map, reduce, null, MapReduceCommand.OutputType.INLINE, null);
      
      try{
         for (DBObject o : out.results()){
            System.err.println(o.toString());
         }
      } catch (Exception e){
         e.printStackTrace();
      }
   }
}
