package com.jthompson.music.service;

import java.util.List;

import javax.ws.rs.PathParam;

import com.jthompson.music.domain.Instrument;
import com.jthompson.music.domain.Musician;

public interface PersonService {

	public List<Musician> getMusicians();

	public Musician getMusician(String id);

	public List<Musician> getMusiciansByInstrument( Instrument instrument );

	public Musician saveMusician(Musician m);

	public Musician deleteMusician(String musicianId);

	public Musician deleteMusician(Musician musician);

}