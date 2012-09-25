package com.jthompson.music.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Event 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="eventId")
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	private String description;
//	
//	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	private List<Song> items = new ArrayList<Song>();

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Schedulable> schedulables = new ArrayList<Schedulable>();

	
	public void moveUp(Schedulable item)
	{

		int index = schedulables.indexOf(item);
		
		if(index > 0)
			Collections.rotate(schedulables.subList(index-1, (index+1)), -1);
		else 
			System.out.println("Can't move up");
	}
	
	public void moveDown(Schedulable item)
	{

		int index = schedulables.indexOf(item);
		
		if(index >= 0 && index < schedulables.size() -1 )
			Collections.rotate(schedulables.subList(index, (index+2)), -1);
		else 
			System.out.println("Can't move down");
	}

	public void addItem(Song item) 
	{
		Schedulable s = new Schedulable();

		s.setSong((Song) item);
		getSchedulables().add(s);
	}

	public void addItem(Text item) 
	{
		Schedulable s = new Schedulable();

		s.setText((Text) item);
		getSchedulables().add(s);
	}

	public void addItem(Speaker item) 
	{
		Schedulable s = new Schedulable();

		s.setSpeaker((Speaker) item);
		getSchedulables().add(s);
		
	}
	

	
}
