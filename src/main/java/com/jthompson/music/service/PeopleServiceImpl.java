package com.jthompson.music.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.jthompson.music.domain.Musician;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Stateless
@Named("peopleService")
public class PeopleServiceImpl 
{

	@Inject
	private DB db;
	
	public List<Musician> getMusicians()
	{
		DBCursor cur = db.getCollection("musicians").find();
		
		MongoRowMapper<Musician> mapper = new MongoRowMapper<Musician>(cur, new RecordMapper<Musician>() {
			@Override
			public Musician mapRecord(DBObject obj) {
				Musician m = new Musician();
				m.setName( (String)obj.get("name") );
				m.setDescription( (String)obj.get("description") );
				
				
				return m;
				
			}
		});

		List<Musician> result = mapper.mapRecords();
		
		System.out.println(result.get(0).toString());
		
		return result;
	}
	
	
	
}
