package com.jthompson.music.web;

import java.beans.Beans;
import java.lang.reflect.Field;
import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.groups.Default;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.glassfish.security.common.PrincipalImpl;
import org.hibernate.validator.constraints.URL;

import com.google.common.base.Objects;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;
import com.google.common.reflect.Reflection;
import com.jthompson.music.domain.Musician;
import com.jthompson.music.service.PersonServiceImpl;
import com.sun.corba.ee.spi.orbutil.logex.Log;
import com.sun.jersey.server.impl.container.servlet.JerseyServletContainerInitializer;

@Slf4j
@Stateless
@Path("/musicians")
public class ResourceController 
{
	@Inject
	private PersonServiceImpl personService;
	
	@Inject
	private Validator validator;
	
	@GET
	@Path("/foo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response foo(@Context UriInfo uriInfo)
	{
		List<Musician> results = personService.getMusicians();

		Musician result = results.get(0);
		
		URI uri = uriInfo.getRequestUriBuilder().path(InstrumentController.class, "getInstrument").build(result.getInstruments().get(0).getId());
	
		Map<String, Object> link = Maps.newHashMap();
		link.put("rel", "self");
		link.put("uri", uri);
		
		Map<String, Map<String, Object>> urisToReplace = Maps.newHashMap();
		urisToReplace.put("instruments", link);
		
		//GenericEntity<List<Musician>> generic = new GenericEntity<List<Musician>>(result) {};
		JSONObject json = new JSONObject();
		//JSONObject instruments = new JSONObject(result.getInstruments(), ["instruments"]);
		
		Map<String, Object> m = Maps.newHashMap();
		
		for(Field f : result.getClass().getSuperclass().getDeclaredFields())
		{
			try {
				if(urisToReplace.containsKey(f.getName()))
					m.put(f.getName(), urisToReplace.get(f.getName()));
				else
					m.put(f.getName(), BeanUtils.getProperty(result, f.getName()) );
			} catch (NoSuchMethodException e) {
				log.error(e.getMessage());
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		
		for(Field f : result.getClass().getDeclaredFields())
		{
			try {
				if(urisToReplace.containsKey(f.getName()))
					m.put(f.getName(), urisToReplace.get(f.getName()));
				else
					m.put(f.getName(), BeanUtils.getProperty(result, f.getName()) );
			} catch (NoSuchMethodException e) {
				log.error(e.getMessage());
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	
		
		try {
			json.put("musician_id", result.getId());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.ok( m ).build();
		
	}
	
	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMusicians()
	{

		System.out.println("Inside /");
		List<Musician> result = personService.getMusicians();
		
		MusiciansWrapper wrap = new MusiciansWrapper(result);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.ok( wrap ).build();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMusician(@PathParam("id") Integer id)
	{
		
		Musician result = personService.getMusician(id);
		
		Set<ConstraintViolation<Musician>> violations =	validator.validate(result, Default.class);
		
		System.out.println("VIOLATIONS: " + violations.size());
		
		ResponseBuilder rb = Response.ok( result );
		
		for(ConstraintViolation<Musician> v : violations)
		{
			System.out.println(v.getMessage());
			rb.header("Violation-"+v.getPropertyPath(), v.getMessage());
		}
		
		return rb.build();
	}
	
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Musician saveMusician(Musician m)
	{
		
		return personService.saveMusician(m);
				
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Musician addMusician(@FormParam("name") String name,@FormParam("description") String description)
	{
		Musician m = new  Musician(); 
		m.setName(name);
		m.setDescription(description);
		
		return personService.saveMusician(m);
		
	}  
	
	@DELETE
	@Path("/{id}")
	public Musician deleteMusician(@PathParam("id") Integer musicianId)
	{
		return personService.deleteMusician(musicianId);
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
