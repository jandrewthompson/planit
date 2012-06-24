package com.jthompson.music.service;

import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

@Singleton
@Named("mongoDb")
public class MongoConnection 
{

	private Mongo mongo;
	private DB db;
	
	@PostConstruct
	public void init()
	{
		try {
			mongo = new Mongo("localhost");
			db = mongo.getDB("planit");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Produces
	public DB getDB()
	{
		return db;
	}
}
