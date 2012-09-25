package com.jthompson.music.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Schedulable 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Song song;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Text text;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Speaker speaker;
	
}
