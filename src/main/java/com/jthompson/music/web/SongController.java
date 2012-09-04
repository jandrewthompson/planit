package com.jthompson.music.web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
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

import com.jthompson.music.domain.Arrangement;
import com.jthompson.music.domain.Musician;
import com.jthompson.music.domain.Song;

@Stateless
@Path("/songs")
public class SongController 
{

	@PersistenceContext(unitName="planit") 
	private EntityManager em; 

	 
	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSongs()
	{
		
		List<Song> result = em.createQuery(
						"select distinct s from Song s left join fetch s.arrangements arr", 
						Song.class)
				.getResultList();
		
		SongWrapper wrap = new SongWrapper(result);
		
		return Response.ok( wrap ).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSong(@PathParam("id") Integer id)
	{
		
		Song result = em.find(Song.class, id);
		
		return Response.ok( result ).build();
	}
	
	
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Song addSong(Song s)
	{
		em.merge(s);
		
		return s;
		
	}
	
	
	@DELETE
	@Path("/delete/{id}")
	public Song deleteSong(@PathParam("id") Integer songId)
	{
		em.createQuery("delete from Song s where s.id = :songId")
				.setParameter("songId", songId)
				.executeUpdate();
		
		return null;
	}
	
	@POST
	@Path("/{songId}/arrangement/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Arrangement saveSongArrangement(
			@PathParam("songId") Integer songId,
			Arrangement arr)
	{
		Song song = em.find(Song.class, songId);
		
		song.getArrangements().add(arr);
		
		em.persist(song);
		
		return arr;
	}
	
	@GET
	@Path("/{songId}/arrangement/{arrangementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Arrangement findArrangement(
			@PathParam("arrangementId") Integer arrangementId)
	{
		Arrangement arr = em.find(Arrangement.class, arrangementId);
		
		return arr;
	}
	
	@DELETE
	@Path("/{songId}/delete/{arrId}")
	public Song deleteArrangement(
				@PathParam("songId") Integer songId,
				@PathParam("arrId") Integer arrangementId)
	{
		
		Song song = em.find(Song.class, songId);
		Arrangement arrangement = em.find(Arrangement.class, arrangementId);
		
		
		if(null != song)
		{
			song.getArrangements().remove(arrangement);			
		}
		em.merge(song);
//		em.createQuery("delete from Arrangement arr where arr.id = :arrId")
//				.setParameter("arrId", arrangementId)
//				.executeUpdate();
//		
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
	public static class SongWrapper
	{
		public SongWrapper() {}
		
		public SongWrapper(List<Song> songs)
		{
			this.songs = songs;
		}
		
		@XmlElement(name="songs")
		private List<Song> songs = new ArrayList<Song>();
		
		public List<Song> getSongs()
		{
			return songs;
		}
	}
	
	
	
}
