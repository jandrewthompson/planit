package com.jthompson.music.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoRowMapper<T> 
{

	private DBCursor cursor;
	private RecordMapper<T> mapper;
	
	public MongoRowMapper(DBCursor cursor, RecordMapper<T> mapper)
	{
		this.cursor = cursor;
		this.mapper = mapper;
	}
	
	public List<T> mapRecords()
	{
		List<T> c = new ArrayList<T>();
		while(cursor.hasNext())
		{
			DBObject obj = cursor.next();
			T mapped = mapper.mapRecord(obj);
			c.add(mapped);
		}
		return c;
	}
	
	public void setCursor(DBCursor cursor) {
		this.cursor = cursor;
	}
}
