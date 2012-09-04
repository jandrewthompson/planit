package com.jthompson.music.web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import com.jthompson.music.domain.Event;
import com.jthompson.music.domain.Schedulable;

@Stateless
@Path("/events")
public class EventController 
{

	@PersistenceContext(unitName="planit") 
	private EntityManager em; 

	 
	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvents()
	{
		
		List<Event> result = em.createQuery("select e from Event e").getResultList();
		
		EventWrapper wrap = new EventWrapper(result);
		
		return Response.ok( wrap ).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvent(@PathParam("id") Integer id)
	{
		
		Event result = em.find(Event.class, id);
		
		return Response.ok( result ).build();
	}
	
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Event addEvent(Event e)
	{
		em.persist(e);
		
		return e;
		
	}
	
	
	@DELETE
	@Path("/delete/{id}")
	public Event deleteEvent(@PathParam("id") Integer eventId)
	{
		em.createQuery("delete from Event e where e.id = :eventId")
				.setParameter("eventId", eventId)
				.executeUpdate();
		
		return null;
	}
	
	@GET
	@Path("/schedulables")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSchedulables()
	{
		
		List<Schedulable> result = em.createQuery("select s from Schedulable s", Schedulable.class).getResultList();
		
		return Response.ok( result.get(0) ).build();
		
	}
	
//	@POST
//	@Path("/{eventId}/arrangement/add")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Arrangement addEventArrangement(
//			@PathParam("EventId") Integer EventId,
//			Arrangement arr)
//	{
//		Event Event = em.find(Event.class, EventId);
//		
//		Event.getArrangements().add(arr);
//		
//		em.persist(Event);
//		
//		return arr;
//	}
//	
//	@DELETE
//	@Path("/{EventId}/delete/{arrId}")
//	public Event deleteArrangement(
//				@PathParam("EventId") Integer EventId,
//				@PathParam("arrId") Integer arrangementId)
//	{
//		
//		Event Event = em.find(Event.class, EventId);
//		Arrangement arrangement = em.find(Arrangement.class, arrangementId);
//		
//		
//		if(null != Event)
//		{
//			Event.getArrangements().remove(arrangement);			
//		}
//		em.merge(Event);
////		em.createQuery("delete from Arrangement arr where arr.id = :arrId")
////				.setParameter("arrId", arrangementId)
////				.executeUpdate();
////		
//		return null;
//	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@XmlRootElement()
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class EventWrapper
	{
		public EventWrapper() {}
		
		public EventWrapper(List<Event> event)
		{
			this.event = event;
		}
		
		@XmlElement(name="events")
		private List<Event> event = new ArrayList<Event>();
		
		public List<Event> getEvents()
		{
			return event;
		}
	}
	
	
	
}
