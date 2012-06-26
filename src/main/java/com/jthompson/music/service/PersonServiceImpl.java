package com.jthompson.music.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.PathParam;

import com.jthompson.music.domain.Musician;

@Stateless
@Named("personService")
public class PersonServiceImpl {
	
	
	@PersistenceContext(unitName="planit") 
	private EntityManager em; 

	
	public List<Musician> getMusicians()
	{
		
		List<Musician> result = em.createQuery("select m from Musician m").getResultList();
		
		return result;
	}
	
	
	public Musician getMusician(@PathParam("id") Integer id)
	{
		
		Musician result = em.find(Musician.class, id);
		
		return result;
	}
	
	

	public Musician addMusician(Musician m)
	{
		em.persist(m);
		
		return m;
		
	}
	
	
	public Musician deleteMusician(Integer musicianId)
	{
		em.createQuery("delete from Musician m where m.id = :musicianId")
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
