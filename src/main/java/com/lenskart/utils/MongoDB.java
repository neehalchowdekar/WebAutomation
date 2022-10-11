package com.lenskart.utils;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class MongoDB {

	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database =  mongoClient.getDatabase("demoDB");
		MongoCollection<Document>  collection = database.getCollection("demoCollection");
		
		
		
		// Create new Document in mongoDB
		Document doc = new Document("password", "123456").
				append("username", "moataznabil");
		collection.insertOne(doc);
		Object id = doc.getObjectId("_id");
		System.out.println(id);
		
		collection.updateOne(
                eq("_id", new ObjectId(id.toString())),
                combine(set("username", "tomsmith")
                        , set("password", "SuperSecretPassword!"),
                        currentDate("lastModified")),
                new UpdateOptions()
                        .upsert(true)
                        .bypassDocumentValidation(true));
		
		doc = collection.find(eq("_id", new ObjectId(id.toString()))).first();
		assert doc !=null;
		System.out.println(doc.get("username"));
		System.out.println(doc.get("password"));
		
		
		//delete collection
				collection.deleteOne(eq("_id",new ObjectId(id.toString())));
		
		//retrive all
		try(MongoCursor<Document> cursor = collection.find().iterator()){
			while(cursor.hasNext()) {
				System.out.println(cursor.next().toJson());
			}
		}
		
		
		
		System.out.println(collection.countDocuments());
	}
	

}
