package com.jthompson.music.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidatorContext;
import javax.validation.spi.ConfigurationState;
import javax.ws.rs.PathParam;

import org.hibernate.Hibernate;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.engine.ConfigurationImpl;
import org.hibernate.validator.engine.ValidatorContextImpl;

import com.jthompson.music.domain.Instrument;
import com.jthompson.music.domain.Musician;

@Stateless
@Named("personService")
public class PersonServiceImpl {
	
	
	@PersistenceContext(unitName="planit") 
	private EntityManager em; 

	
	public List<Musician> getMusicians()
	{
		
		List<Musician> result = em.createNamedQuery(
				Musician.FIND_ALL, Musician.class)
				.getResultList();
		
	
		return result;
	}
	
	
	public Musician getMusician(@PathParam("id") Integer id)
	{
		
		Musician result = em.createNamedQuery(Musician.FIND_BY_ID, Musician.class)
							.setParameter("musicianId", id)
							.getSingleResult();
		
		return result;
	}
	
	public List<Musician> getMusiciansByInstrument(Instrument instrument)
	{
		
		List<Musician> result = em.createNamedQuery(Musician.FIND_BY_INSTRUMENT,
				Musician.class)
				.setParameter("instrument", instrument.getName())
				.getResultList();
		
		return result;
	}
	
	

	public Musician saveMusician(Musician m)
	{
		//em.persist(m);
		
		System.out.println("Adding musician: " + m.toString());
		em.merge(m);
		
		return m;
		
	}
	
	
	public Musician deleteMusician(Integer musicianId)
	{
		em.createNamedQuery(Musician.DELETE_BY_ID)
				.setParameter("musicianId", musicianId)
				.executeUpdate();
		
		return null;
	}
	
	public Musician deleteMusician(Musician musician)
	{
		return deleteMusician(musician.getId());
		
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	

	
}
