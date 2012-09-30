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

import org.springframework.data.mongodb.core.MongoOperations;

import com.jthompson.music.domain.Arrangement;
import com.jthompson.music.domain.Musician;
import com.jthompson.music.domain.Song;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Stateless
@Path("/songs")
@Produces(MediaType.APPLICATION_JSON)
public class SongController 
{

	@Inject
	private MongoOperations mongoOps;

	 
	@GET
	@Path("/") 
	public Response getSongs()
	{
		
		List<Song> result = mongoOps.findAll(Song.class);
		
		SongWrapper wrap = new SongWrapper(result);
		
		return Response.ok( wrap ).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSong(@PathParam("id") String id)
	{
		
		Song result = mongoOps.findOne(query(where("_id").is(id)), Song.class);
		
		return Response.ok( result ).build();
	}
	
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Song addSong(Song s)
	{
		mongoOps.save(s);
		
		return s;
		
	}
	
	
	@DELETE
	@Path("/{id}")
	public Song deleteSong(@PathParam("id") String songId)
	{
		mongoOps.remove(getSong(songId));
		
		return null;
	}
	
	@POST
	@Path("/{songId}/arrangement/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Arrangement saveSongArrangement(
			@PathParam("songId") String songId,
			Arrangement arr)
	{
		Song song = mongoOps.findOne(query(where("_id").is(songId)), Song.class);
		
		song.getArrangements().add(arr);
		
		mongoOps.save(song);
		
		return arr;
	}
	
	@GET
	@Path("/{songId}/arrangement/{arrangementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Arrangement findArrangement(
			@PathParam("arrangementId") String arrangementId)
	{
		Arrangement arr = mongoOps.findOne(query(where("_id").is(arrangementId)), Arrangement.class);
		
		return arr;
	}
	
	@DELETE
	@Path("/{songId}/delete/{arrId}")
	public Song deleteArrangement(
				@PathParam("songId") String songId,
				@PathParam("arrId") String arrangementId)
	{
		
		Song song = mongoOps.findOne(query(where("_id").is(songId)), Song.class);
		Arrangement arrangement = mongoOps.findOne(query(where("_id").is(arrangementId)), Arrangement.class);
		
		
		if(null != song)
		{
			song.getArrangements().remove(arrangement);			
		}
		mongoOps.save(song);
		
		return null;
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
