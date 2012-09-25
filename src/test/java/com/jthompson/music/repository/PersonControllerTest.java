package com.jthompson.music.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jthompson.music.domain.Event;
import com.jthompson.music.domain.Instrument;
import com.jthompson.music.domain.Musician;
import com.jthompson.music.domain.Schedulable;
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
		song.setName("Test Song 1");
		song.setDescription("This is a test song");
		
		Song song2 = new Song();
		song2.setName("Test Song 2");
		song2.setDescription("This is another test song");
		
		Song song3 = new Song();
		song3.setName("Test Song 3");
		song3.setDescription("Yet another test song");
		
		em.persist(song);
		
		
		event.addItem(song);
		event.addItem(song2);
		event.addItem(song3);
		
		em.persist(event);
		
		Instrument i = em.createQuery("select i from Instrument i where i.name='Guitar'", Instrument.class)
		.getSingleResult();
		
		Instrument i2 = em.createQuery("select i from Instrument i where i.name='Vocal 1'", Instrument.class)
				.getSingleResult();
		
		m.getInstruments().add(i);
		m.getInstruments().add(i2);
		
		em.persist(m);
		
	}
	
	@Test
	public void musiciansList()
	{
		
		Musician result = 
				em.createQuery("select m from Musician m", Musician.class).getResultList().get(0);
		
		Assert.assertEquals(result.getName(), "andrew");
		
		Event event = 
				em.createQuery("select e from Event e", Event.class).getResultList().get(0);
		
		System.out.println(event);
		
		Schedulable theItem = event.getSchedulables().get(1);
		
		event.moveUp(theItem);
		
		System.out.println(event);
		
		
	}
	
	@Test
	public void musiciansInstruements()
	{
		
		Instrument i = new Instrument();
		
		i.setName("Guitar");
		
		Musician result = 
				service.getMusiciansByInstrument(i).get(0);
		
		Assert.assertEquals(result.getInstruments().size(), 2);
		
	}
	
}












