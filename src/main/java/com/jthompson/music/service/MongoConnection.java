package com.jthompson.music.service;

import java.net.UnknownHostException;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import lombok.extern.slf4j.Slf4j;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

@Singleton
@Slf4j
public class MongoConnection 
{
	@Produces
	public DB getDb()
	{
		try
		{
			
		
			Mongo mongo = new Mongo("localhost");
			
			DB db = mongo.getDB("planit");
			
			return db;
			
		} catch (Exception e) {
			log.error("Couldn't create connection", e);
		}
		
		return null;
	}
	
	@Produces
	public MongoOperations getMongoOps()
	{
		MongoTemplate mongoOps = null;
		try {
			MongoDbFactory dbFactory = new SimpleMongoDbFactory(new Mongo(), "planit");
			mongoOps = new MongoTemplate(dbFactory);
						
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return mongoOps;
	}

}
