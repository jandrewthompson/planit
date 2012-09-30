package com.jthompson.music.service;

import java.net.UnknownHostException;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoOperations;


import lombok.extern.slf4j.Slf4j;

import com.google.common.collect.Lists;
import com.jthompson.music.domain.Instrument;
import com.jthompson.music.domain.Musician;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Stateless
@Alternative
@Slf4j
public class PersonServiceMongoImpl implements PersonService {

	@Inject
	private MongoOperations mongoOps;
	
	@Override
	public List<Musician> getMusicians() {
		
		return mongoOps.findAll(Musician.class);

	}

	@Override
	public Musician getMusician(String id) {
		return mongoOps.findOne(query( where("_id").is( id ) ), Musician.class);
	}

	@Override
	public List<Musician> getMusiciansByInstrument(Instrument instrument) {
		
		return mongoOps.find(query( where("instruments.name").is(instrument.getName() ) ), Musician.class);
		
	}

	@Override
	public Musician saveMusician(Musician m) {
		mongoOps.save(m);
		return m;
	}

	@Override
	public Musician deleteMusician(String musicianId) {
		mongoOps.remove( getMusician(musicianId) );
		return null;
	}

	@Override
	public Musician deleteMusician(Musician musician) {
		mongoOps.remove(musician);
		return null;
	}

}
