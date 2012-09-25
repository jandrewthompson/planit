package com.jthompson.music.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name="Technician")
public class Technician
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id;
	
	protected String description;

	private String name;
	
	private String type;

	
	
}
