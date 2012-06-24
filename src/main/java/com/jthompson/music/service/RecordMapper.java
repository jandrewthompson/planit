package com.jthompson.music.service;

import com.mongodb.DBObject;

public interface RecordMapper<T> 
{
	T mapRecord(DBObject obj);
}
