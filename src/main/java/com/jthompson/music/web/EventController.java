package com.jthompson.music.web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
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

import org.springframework.data.mongodb.core.MongoOperations;

import com.jthompson.music.domain.Event;
import com.jthompson.music.domain.Schedulable;
import com.jthompson.music.domain.Song;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Stateless
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventController 
{
	
	@Inject
	private MongoOperations mongoOps;
 
	@GET
	@Path("/") 
	public Response getEvents()
	{
		
		List<Event> result = mongoOps.findAll(Event.class);
		
		EventWrapper wrap = new EventWrapper(result);
		
		return Response.ok( wrap ).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getEvent(@PathParam("id") String id)
	{
		
		
		Event result = mongoOps.findOne( query( where( "_id" ).is( id ) ), Event.class); 
		
		return Response.ok( result ).build();
	}
	
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Event addEvent(Event e)
	{
		mongoOps.save(e);
		
		return e;
		
	}
	
	
	@DELETE
	@Path("/{id}")
	public Event deleteEvent(@PathParam("id") String eventId)
	{
		
		mongoOps.remove( mongoOps.findOne( query( where( "_id" ).is( eventId ) ), Event.class) );
		
		return null;
	}
	

	@POST
	@Path("/{id}/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEventItem(@PathParam("id") String eventId, Song e)
	{
		Event event = mongoOps.findOne( query( where( "_id" ).is( eventId ) ), Event.class);
		
		event.getSchedulables().add(e);
		
		mongoOps.save(event);
		
		return Response.ok(event).build();
		
	}
	
//	@GET
//	@Path("/schedulables")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getSchedulables()
//	{
//		
//		List<Schedulable> result = em.createQuery("select s from Schedulable s", Schedulable.class).getResultList();
//		
//		return Response.ok( result.get(0) ).build();
//		
//	}
//	
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
