package com.jthompson.music.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.PathParam;

import org.springframework.data.mongodb.core.MongoOperations;

import com.jthompson.music.domain.Arrangement;
import com.jthompson.music.domain.Instrument;
import com.jthompson.music.domain.Musician;
import com.jthompson.music.domain.Song;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Stateless
@Named("songService")
public class SongServiceImpl {
	
	@Inject
	private MongoOperations mongoOps;

	public List<Song> getSongs()
	{
		return mongoOps.findAll(Song.class);
	}

	
	public Song saveSong(Song s)
	{
		mongoOps.save(s);
		return s;
	}
	
	public Arrangement saveArrangement(Arrangement arr)
	{
		mongoOps.save(arr);
		return arr;
	}
	
	public void addArrangement(Song s, Arrangement arr)
	{
		
		s.getArrangements().add(arr);
		
		saveSong(s);
		
	}
	
	public void deleteArrangement(Arrangement arr)
	{
		mongoOps.remove(arr);
		
	}
	
	public List<Instrument> getInstruments()
	{
		return mongoOps.findAll(Instrument.class);
	}
	
	public Instrument getInstrument(String id)
	{
		return mongoOps.findOne(query(where("_id").is(id)), Instrument.class);
	}
	
	
	
}
