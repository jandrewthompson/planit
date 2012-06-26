package com.jthompson.music.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EventItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="eventId")
	private Integer id;
	
	private Integer itemOrder;
	
	@ManyToOne
	private Schedulable item;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrder() {
		return itemOrder;
	}

	public void setOrder(Integer order) {
		this.itemOrder = order;
	}

	public Schedulable getItem() {
		return item;
	}

	public void setItem(Schedulable item) {
		this.item = item;
	}
	
	
	
}
