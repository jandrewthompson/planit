package com.jthompson.music.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.PathParam;

import com.jthompson.music.domain.Arrangement;
import com.jthompson.music.domain.Instrument;
import com.jthompson.music.domain.Musician;
import com.jthompson.music.domain.Song;

@Stateless
@Named("songService")
public class SongServiceImpl {
	
	
	@PersistenceContext(unitName="planit") 
	private EntityManager em; 

	public List<Song> getSongs()
	{
		return em.createQuery("Select s from Song s", Song.class).getResultList();
	}

	
	public Song saveSong(Song s)
	{
		return em.merge(s);
	}
	
	public Arrangement saveArrangement(Arrangement arr)
	{
		return em.merge(arr);
	}
	
	public void addArrangement(Song s, Arrangement arr)
	{
		em.persist(arr);
		
		s.getArrangements().add(arr);
		
		em.merge(s);
		
	}
	
	public void deleteArrangement(Arrangement arr)
	{
		List<Song> results = em.createQuery("select s from Song s where :arr member of s.arrangements", Song.class)
			.setParameter("arr", arr)
			.getResultList();
		
		if(null != results && results.size() == 1)
		{
			Song song = results.get(0);
			song.getArrangements().remove(arr);
			em.merge(song);
		}
	}
	
	public List<Instrument> getInstruments()
	{
		return em.createQuery("select i from Instrument i", Instrument.class)
				.getResultList();
	}
	
	public Instrument getInstrument(Integer id)
	{
		return em.createQuery("select i from Instrument i where i.id = :id", Instrument.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
