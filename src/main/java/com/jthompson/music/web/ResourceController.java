package com.jthompson.music.web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;

import com.jthompson.music.domain.Musician;
import com.jthompson.music.service.PeopleServiceImpl;
import com.mongodb.DB;

@Stateless
@Path("/musicians")
public class ResourceController 
{

	@PersistenceContext(unitName="planit") 
	private EntityManager em; 

	@Inject
	private PeopleServiceImpl service;
	 
	@GET
	@Path("/test") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTest()
	{
		List<Musician> result = service.getMusicians();
		
		MusiciansWrapper wrap = new MusiciansWrapper(result);
		
		return Response.ok(wrap).build();
		
	}
	
	
	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMusicians()
	{
		
		List<Musician> result = em.createQuery("select m from Musician m").getResultList();
		
		MusiciansWrapper wrap = new MusiciansWrapper(result);
		
		return Response.ok( wrap ).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMusician(@PathParam("id") Integer id)
	{
		
		Musician result = em.find(Musician.class, id);
		
		return Response.ok( result ).build();
	}
	
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Musician addMusician(Musician m)
	{
		em.persist(m);
		
		return m;
		
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Musician addMusician(@FormParam("name") String name,@FormParam("description") String description)
	{
		Musician m = new  Musician(); 
		m.setName(name);
		m.setDescription(description);
		
		em.persist(m);
		
		return m;
		
	}  
	
	@DELETE
	@Path("/delete/{id}")
	public Musician deleteMusician(@PathParam("id") Integer musicianId)
	{
		em.createQuery("delete from Musician m where m.id = :musicianId")
				.setParameter("musicianId", musicianId)
				.executeUpdate();
		
		return null;
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@XmlRootElement()
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class MusiciansWrapper
	{
		public MusiciansWrapper() {}
		
		public MusiciansWrapper(List<Musician> musicians)
		{
			this.musicians = musicians;
		}
		
		@XmlElement(name="musicians")
		private List<Musician> musicians = new ArrayList<Musician>();
		
		public List<Musician> getMusicians() {
			return musicians;
		}
	}
	
	
	
}
