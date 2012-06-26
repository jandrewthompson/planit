package com.jthompson.music.repository;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jthompson.music.domain.Event;
import com.jthompson.music.domain.EventItem;
import com.jthompson.music.domain.Musician;
import com.jthompson.music.domain.Song;
import com.jthompson.music.service.PersonServiceImpl;

public class PersonControllerTest extends AbstractRollbackJpaRepository
{

	private PersonServiceImpl service;
	
	@Override
	void createFixtureAndTestData() 
	{
	
		service = new PersonServiceImpl();
		service.setEm(em);
				
		Musician m = new Musician();
		m.setName("andrew");
		m.setDescription("Worship Leader");
		
		Event event = new Event();
		event.setDateTime(new Date());
		event.setDescription("Test Event");
		
		Song song = new Song();
		song.setName("Test Song");
		song.setDescription("This is a test song");
		
		em.persist(song);
		
		EventItem item = new EventItem();
		item.setOrder(1);
		item.setItem(song);
		
		em.persist(item);
		
		
		em.persist(m);
		
	}
	
	@Test
	public void musiciansList()
	{
		
		Musician result = 
				em.createQuery("select m from Musician m", Musician.class).getResultList().get(0);
		
		Assert.assertEquals(result.getName(), "andrew");
		
	}
	
}
