package com.jthompson.music.web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.internal.jaxb.cfg.JaxbHibernateConfiguration.JaxbSessionFactory.JaxbMapping;

import com.jthompson.music.domain.Instrument;
import com.jthompson.music.service.SongServiceImpl;
import com.sun.tools.ws.processor.model.jaxb.JAXBMapping;

@Stateless
@Path("/instruments")
public class InstrumentController 
{

	@Inject
	private SongServiceImpl songService;
	
	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInstruments()
	{

		System.out.println("Inside /");
		List<Instrument> result = songService.getInstruments();
		
		InstrumentsWrapper wrap = new InstrumentsWrapper(result);
		
		return Response.ok( wrap ).build();
	}
	
	@GET
	@Path("/{id}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInstrument(@PathParam("id") Integer id)
	{

		System.out.println("Inside /");
		Instrument result = songService.getInstrument(id);
		
		return Response.ok( result ).build();
	}
	
	@XmlRootElement()
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class InstrumentsWrapper
	{
		public InstrumentsWrapper() {}
		
		public InstrumentsWrapper(List<Instrument> instruments)
		{
			this.instruments = instruments;
		}
		
		@XmlElement(name="instruments")
		private List<Instrument> instruments = new ArrayList<Instrument>();
		
		public List<Instrument> getInstruments() {
			return instruments;
		}
	}
	
}
