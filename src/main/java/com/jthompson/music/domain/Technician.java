package com.jthompson.music.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Technician")
public class Technician extends Resource
{
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
