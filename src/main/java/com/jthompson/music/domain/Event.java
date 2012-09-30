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
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Event 
{
	
	private String id;
	
	private Date dateTime;
	
	private String description;

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

	
}
