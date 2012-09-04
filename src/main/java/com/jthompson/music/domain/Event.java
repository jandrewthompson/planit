package com.jthompson.music.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Schedulable> items = new ArrayList<Schedulable>();

	public void moveUp(Schedulable item)
	{

		int index = items.indexOf(item);
		
		if(index > 0)
			Collections.rotate(items.subList(index-1, (index+1)), -1);
		else 
			System.out.println("Can't move up");
	}
	
	public void moveDown(Schedulable item)
	{

		int index = items.indexOf(item);
		
		if(index >= 0 && index < items.size() -1 )
			Collections.rotate(items.subList(index, (index+2)), -1);
		else 
			System.out.println("Can't move down");
	}
	
	public void addItem(Schedulable item)
	{
		items.add(item);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Schedulable> getItems() {
		return items;
	}

	public void setItems(List<Schedulable> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
